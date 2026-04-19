package com.rsa.secure;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.HexFormat;

public class Encryption {

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();

        String message = "A top secret!";
        byte[] plaintext = message.getBytes("UTF-8");

        OAEPParameterSpec oaepSpec = new OAEPParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT
        );

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic(), oaepSpec);
        byte[] ciphertext1 = cipher.doFinal(plaintext);

        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic(), oaepSpec);
        byte[] ciphertext2 = cipher.doFinal(plaintext);

        System.out.println("Plaintext : " + message);
        System.out.println();
        printHex("Ciphertext (run 1) : ", ciphertext1);
        System.out.println();
        printHex("Ciphertext (run 2) : ", ciphertext2);
        System.out.println();
        System.out.println("Same ciphertext? " + java.util.Arrays.equals(ciphertext1, ciphertext2));
        System.out.println("Observation: RSA-OAEP is probabilistic — same plaintext produces different ciphertext each time.");
    }
}
