package com.rsa.secure;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.HexFormat;

public class Signing {

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        PSSParameterSpec pssSpec = new PSSParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1
        );

        String msg1 = "I owe you $2000.";
        String msg2 = "I owe you $3000.";

        Signature signer = Signature.getInstance("RSASSA-PSS");
        signer.setParameter(pssSpec);

        signer.initSign(keyPair.getPrivate());
        signer.update(msg1.getBytes("UTF-8"));
        byte[] sig1 = signer.sign();

        signer.initSign(keyPair.getPrivate());
        signer.update(msg2.getBytes("UTF-8"));
        byte[] sig2 = signer.sign();

        signer.initSign(keyPair.getPrivate());
        signer.update(msg1.getBytes("UTF-8"));
        byte[] sig1Again = signer.sign();

        System.out.println("M1 : " + msg1);
        System.out.println("M2 : " + msg2);
        System.out.println();
        printHex("S1       : ", sig1);
        printHex("S2       : ", sig2);
        printHex("S1 again : ", sig1Again);
        System.out.println();
        System.out.println("S1 == S2?        " + java.util.Arrays.equals(sig1, sig2));
        System.out.println("S1 == S1 again?  " + java.util.Arrays.equals(sig1, sig1Again));
        System.out.println("Observation: signatures differ between messages AND between runs — RSA-PSS random salt.");
    }
}
