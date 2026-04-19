package com.rsa.secure;

import java.security.Signature;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;

public class Verification {

    public static void main(String[] args) throws Exception {

        Signing signing = new Signing();
        signing.sign();

        PSSParameterSpec pssSpec = new PSSParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1
        );

        byte[] msgBytes = Signing.MSG1.getBytes("UTF-8");

        // Valid signature: S1 was signed over MSG1
        Signature verifier = Signature.getInstance("RSASSA-PSS");
        verifier.setParameter(pssSpec);
        verifier.initVerify(signing.getKeyGeneration().getPublicKey());
        verifier.update(msgBytes);
        boolean validResult = verifier.verify(signing.getSig1());

        // Corrupted signature: flip last bit of S1
        byte[] corruptedSig = Arrays.copyOf(signing.getSig1(), signing.getSig1().length);
        corruptedSig[corruptedSig.length - 1] ^= 0x01;

        verifier.initVerify(signing.getKeyGeneration().getPublicKey());
        verifier.update(msgBytes);
        boolean corruptedResult = verifier.verify(corruptedSig);

        System.out.println("Message : " + Signing.MSG1);
        System.out.println();
        System.out.println("Verification (valid signature)     : " + (validResult ? "VALID" : "INVALID"));
        System.out.println("Verification (corrupted signature) : " + (corruptedResult ? "VALID" : "INVALID"));
    }
}
