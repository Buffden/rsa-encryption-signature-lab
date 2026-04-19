package com.rsa.secure;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.spec.MGF1ParameterSpec;
import java.util.HexFormat;

public class Decryption {

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        Encryption enc = new Encryption();
        enc.encrypt();

        OAEPParameterSpec oaepSpec = new OAEPParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT
        );

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, enc.getKeyGeneration().getPrivateKey(), oaepSpec);
        byte[] decrypted = cipher.doFinal(enc.getCiphertext());

        System.out.println("Original  : " + Encryption.PLAINTEXT);
        printHex("Encrypted : ", enc.getCiphertext());
        System.out.println("Decrypted : " + new String(decrypted, "UTF-8"));
        System.out.println();
        System.out.println("Match: " + Encryption.PLAINTEXT.equals(new String(decrypted, "UTF-8")));
    }
}
