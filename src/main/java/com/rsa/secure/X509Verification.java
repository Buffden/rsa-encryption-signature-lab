package com.rsa.secure;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Date;
import java.util.HexFormat;
import java.util.List;

public class X509Verification {

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        String host = (args.length > 0) ? args[0] : "www.amazon.com";
        System.out.println("Target: https://" + host);
        System.out.println();

        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, null, null);

        HttpsURLConnection conn = (HttpsURLConnection) new URL("https://" + host).openConnection();
        conn.setSSLSocketFactory(ctx.getSocketFactory());
        conn.connect();
        X509Certificate[] chain = (X509Certificate[]) conn.getServerCertificates();
        conn.disconnect();

        System.out.println("Chain length: " + chain.length);
        System.out.println();

        // 1. expiry check — all certs in chain
        System.out.println("=== Expiry Check ===");
        Date now = new Date();
        boolean chainValid = true;
        for (int i = 0; i < chain.length; i++) {
            try {
                chain[i].checkValidity(now);
                System.out.println("Cert [" + i + "] " + chain[i].getSubjectX500Principal().getName() + " : VALID");
            } catch (Exception ex) {
                System.out.println("Cert [" + i + "] " + chain[i].getSubjectX500Principal().getName() + " : EXPIRED — " + ex.getMessage());
                chainValid = false;
            }
        }
        System.out.println();

        // 2. hostname verification — check SANs then fall back to CN
        System.out.println("=== Hostname Verification ===");
        X509Certificate serverCertForHostname = chain[0];
        boolean hostnameValid = false;
        Collection<List<?>> sans = serverCertForHostname.getSubjectAlternativeNames();
        if (sans != null) {
            for (List<?> san : sans) {
                if (san.size() >= 2 && san.get(0) instanceof Integer && (Integer) san.get(0) == 2) {
                    String dnsName = (String) san.get(1);
                    if (dnsName.equalsIgnoreCase(host) ||
                        (dnsName.startsWith("*.") && host.endsWith(dnsName.substring(1)))) {
                        hostnameValid = true;
                        break;
                    }
                }
            }
        }
        if (!hostnameValid) {
            String cn = serverCertForHostname.getSubjectX500Principal().getName();
            hostnameValid = cn.contains("CN=" + host);
        }
        System.out.println("Hostname '" + host + "' matches cert: " + (hostnameValid ? "YES" : "NO"));
        if (!hostnameValid) chainValid = false;
        System.out.println();

        // 3. chain issuer-subject link validation
        System.out.println("=== Chain Issuer-Subject Validation ===");
        for (int i = 0; i < chain.length - 1; i++) {
            String subject = chain[i].getIssuerX500Principal().getName();
            String issuer = chain[i + 1].getSubjectX500Principal().getName();
            boolean linked = subject.equals(issuer);
            System.out.println("Cert [" + i + "] issuer == Cert [" + (i + 1) + "] subject: " + (linked ? "YES" : "NO"));
            if (!linked) chainValid = false;
        }
        System.out.println();

        // 4. RSA signature verification (same as Task 6 — retained)
        System.out.println("=== RSA Signature Verification ===");
        X509Certificate caCert = chain[1];

        System.out.println("Server : " + serverCertForHostname.getSubjectX500Principal());
        System.out.println("CA     : " + caCert.getSubjectX500Principal());

        byte[] tbsCert = serverCertForHostname.getTBSCertificate();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] computedHash = md.digest(tbsCert);

        try {
            serverCertForHostname.verify(caCert.getPublicKey());
            System.out.println("RSA signature verification : VALID");
        } catch (Exception ex) {
            System.out.println("RSA signature verification : INVALID — " + ex.getMessage());
            chainValid = false;
        }

        printHex("Computed TBS hash : ", computedHash);
        System.out.println();

        // 5. OCSP / revocation note
        System.out.println("=== Revocation ===");
        String ocspUrl = null;
        byte[] aiaExt = serverCertForHostname.getExtensionValue("1.3.6.1.5.5.7.1.1");
        if (aiaExt != null) {
            String aiaStr = new String(aiaExt);
            int idx = aiaStr.indexOf("http");
            if (idx >= 0) {
                ocspUrl = aiaStr.substring(idx).split("\0")[0].trim();
            }
        }
        if (ocspUrl != null) {
            System.out.println("OCSP URL found : " + ocspUrl);
            System.out.println("Note: Full OCSP request requires bouncycastle or manual ASN.1 — URL extracted for reference.");
        } else {
            System.out.println("No OCSP URL found in AIA extension.");
        }
        System.out.println();

        System.out.println("=== Overall Result ===");
        System.out.println(chainValid ? "VALID" : "INVALID");
    }
}
