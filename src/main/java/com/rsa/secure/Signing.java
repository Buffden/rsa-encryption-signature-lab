package com.rsa.secure;

import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;
import java.util.HexFormat;

public class Signing {

    private KeyGeneration keyGen;
    private byte[] sig1;
    private byte[] sig2;
    private byte[] sig1Again;

    public KeyGeneration getKeyGeneration() { return keyGen; }
    public byte[] getSig1()                 { return sig1; }

    static final String MSG1 = "I owe you $2000.";
    static final String MSG2 = "I owe you $3000.";

    public void sign() throws Exception {
        keyGen = new KeyGeneration();
        keyGen.generate();

        PSSParameterSpec pssSpec = new PSSParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1
        );

        Signature signer = Signature.getInstance("RSASSA-PSS");
        signer.setParameter(pssSpec);

        signer.initSign(keyGen.getPrivateKey());
        signer.update(MSG1.getBytes("UTF-8"));
        sig1 = signer.sign();

        signer.initSign(keyGen.getPrivateKey());
        signer.update(MSG2.getBytes("UTF-8"));
        sig2 = signer.sign();

        signer.initSign(keyGen.getPrivateKey());
        signer.update(MSG1.getBytes("UTF-8"));
        sig1Again = signer.sign();
    }

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {
        Signing signing = new Signing();
        signing.sign();

        System.out.println("M1 : " + MSG1);
        System.out.println("M2 : " + MSG2);
        System.out.println();
        printHex("S1       : ", signing.sig1);
        printHex("S2       : ", signing.sig2);
        printHex("S1 again : ", signing.sig1Again);
        System.out.println();
        System.out.println("S1 == S2?        " + Arrays.equals(signing.sig1, signing.sig2));
        System.out.println("S1 == S1 again?  " + Arrays.equals(signing.sig1, signing.sig1Again));
        System.out.println("Observation: signatures differ between messages AND between runs — RSA-PSS random salt.");
    }
}
