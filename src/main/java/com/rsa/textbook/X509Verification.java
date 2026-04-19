package com.rsa.textbook;

import javax.net.ssl.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.HexFormat;

public class X509Verification {

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        String host = (args.length > 0) ? args[0] : "www.amazon.com";

        System.out.println("Target: https://" + host + "\n");

        // fetch the certificate chain from the server
        SSLContext ctx = SSLContext.getInstance("TLS");
        ctx.init(null, null, null);

        HttpsURLConnection conn = (HttpsURLConnection) new URL("https://" + host).openConnection();
        conn.setSSLSocketFactory(ctx.getSocketFactory());
        conn.connect();
        X509Certificate[] chain = (X509Certificate[]) conn.getServerCertificates();
        conn.disconnect();

        if (chain.length < 2) {
            System.out.println("Chain too short — CA certificate not included.");
            return;
        }

        X509Certificate serverCert = chain[0];
        X509Certificate caCert = chain[1];

        System.out.println("Server cert subject : " + serverCert.getSubjectX500Principal());
        System.out.println("CA cert subject     : " + caCert.getSubjectX500Principal());
        System.out.println();

        // make sure the CA cert uses RSA, not ECDSA
        if (!(caCert.getPublicKey() instanceof RSAPublicKey)) {
            System.out.println("CA uses " + caCert.getPublicKey().getAlgorithm()
                + " — not RSA. Try a different host (e.g. www.amazon.com).");
            return;
        }

        // extract n and e from the CA's public key
        RSAPublicKey caKey = (RSAPublicKey) caCert.getPublicKey();
        BigInteger n = caKey.getModulus();
        BigInteger e = caKey.getPublicExponent();
        System.out.println("CA n = " + n.toString(16).toUpperCase());
        System.out.println("CA e = " + e.toString(16).toUpperCase());
        System.out.println();

        // read the raw signature bytes from the server certificate
        byte[] sigBytes = serverCert.getSignature();
        BigInteger sig = new BigInteger(1, sigBytes);
        System.out.println("Signature length    : " + sigBytes.length + " bytes");
        printHex("Signature (hex)     : ", sigBytes);
        System.out.println();

        // manually decrypt the signature: sig^e mod n
        BigInteger decrypted = sig.modPow(e, n);
        byte[] decryptedBytes = decrypted.toByteArray();

        // BigInteger may add a leading 0x00 for sign — strip it
        if (decryptedBytes[0] == 0x00) {
            decryptedBytes = Arrays.copyOfRange(decryptedBytes, 1, decryptedBytes.length);
        }

        printHex("Decrypted block     : ", decryptedBytes);
        System.out.println();

        // the decrypted block is PKCS#1 v1.5 padded: 0x01 [0xFF...] 0x00 [DigestInfo] [hash]
        // find the 0x00 separator that marks the end of the padding
        // DigestInfo prefix for SHA-256 (ASN.1 DER): 30 31 30 0d 06 09 60 86 48 01 65 03 04 02 01 05 00 04 20
        byte[] sha256DigestInfoPrefix = {
            0x30, 0x31, 0x30, 0x0d, 0x06, 0x09,
            0x60, (byte)0x86, 0x48, 0x01, 0x65, 0x03, 0x04, 0x02, 0x01,
            0x05, 0x00, 0x04, 0x20
        };

        int separatorIdx = -1;
        for (int i = 1; i < decryptedBytes.length; i++) {
            if (decryptedBytes[i] == 0x00) {
                separatorIdx = i;
                break;
            }
        }

        if (separatorIdx < 0) {
            System.out.println("Could not parse PKCS#1 padding.");
            return;
        }

        byte[] digestInfoAndHash = Arrays.copyOfRange(decryptedBytes, separatorIdx + 1, decryptedBytes.length);

        if (digestInfoAndHash.length < sha256DigestInfoPrefix.length + 32) {
            System.out.println("Unexpected DigestInfo length: " + digestInfoAndHash.length);
            return;
        }

        // skip past the DigestInfo header to get the embedded hash
        byte[] embeddedHash = Arrays.copyOfRange(
            digestInfoAndHash,
            sha256DigestInfoPrefix.length,
            sha256DigestInfoPrefix.length + 32
        );

        printHex("Embedded hash (M')  : ", embeddedHash);

        // independently compute SHA-256 over the TBSCertificate bytes
        byte[] tbsCert = serverCert.getTBSCertificate();
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] computedHash = md.digest(tbsCert);

        printHex("Computed hash (M)   : ", computedHash);
        System.out.println();

        boolean valid = Arrays.equals(embeddedHash, computedHash);
        System.out.println("Verification result : " + (valid ? "VALID" : "INVALID"));
    }
}
