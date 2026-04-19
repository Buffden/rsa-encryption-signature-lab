package com.rsa.secure;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;

public class Verification {

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        PSSParameterSpec pssSpec = new PSSParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1
        );

        String message = "Launch a missile.";
        byte[] msgBytes = message.getBytes("UTF-8");

        Signature signer = Signature.getInstance("RSASSA-PSS");
        signer.setParameter(pssSpec);
        signer.initSign(keyPair.getPrivate());
        signer.update(msgBytes);
        byte[] signature = signer.sign();

        Signature verifier = Signature.getInstance("RSASSA-PSS");
        verifier.setParameter(pssSpec);

        verifier.initVerify(keyPair.getPublic());
        verifier.update(msgBytes);
        boolean validResult = verifier.verify(signature);

        byte[] corruptedSig = Arrays.copyOf(signature, signature.length);
        corruptedSig[corruptedSig.length - 1] ^= 0x01;

        verifier.initVerify(keyPair.getPublic());
        verifier.update(msgBytes);
        boolean corruptedResult = verifier.verify(corruptedSig);

        System.out.println("Message : " + message);
        System.out.println();
        System.out.println("Verification (valid signature)     : " + (validResult ? "VALID" : "INVALID"));
        System.out.println("Verification (corrupted signature) : " + (corruptedResult ? "VALID" : "INVALID"));
    }
}
