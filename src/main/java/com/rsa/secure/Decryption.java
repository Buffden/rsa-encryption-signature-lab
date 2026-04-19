package com.rsa.secure;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.MGF1ParameterSpec;
import java.util.HexFormat;

public class Decryption {

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
        byte[] ciphertext = cipher.doFinal(plaintext);

        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate(), oaepSpec);
        byte[] decrypted = cipher.doFinal(ciphertext);

        System.out.println("Original  : " + message);
        printHex("Encrypted : ", ciphertext);
        System.out.println("Decrypted : " + new String(decrypted, "UTF-8"));
        System.out.println();
        System.out.println("Match: " + message.equals(new String(decrypted, "UTF-8")));
    }
}
