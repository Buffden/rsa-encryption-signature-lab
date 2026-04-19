package com.rsa.secure;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;
import java.util.HexFormat;

public class Encryption {

    private KeyGeneration keyGen;
    private byte[] ciphertext;
    private byte[] ciphertext2;

    public KeyGeneration getKeyGeneration() { return keyGen; }
    public byte[] getCiphertext()           { return ciphertext; }

    static final String PLAINTEXT = "A top secret!";

    public void encrypt() throws Exception {
        keyGen = new KeyGeneration();
        keyGen.generate();

        byte[] plain = PLAINTEXT.getBytes("UTF-8");

        OAEPParameterSpec oaepSpec = new OAEPParameterSpec(
            "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT
        );

        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

        cipher.init(Cipher.ENCRYPT_MODE, keyGen.getPublicKey(), oaepSpec);
        ciphertext = cipher.doFinal(plain);

        cipher.init(Cipher.ENCRYPT_MODE, keyGen.getPublicKey(), oaepSpec);
        ciphertext2 = cipher.doFinal(plain);
    }

    static void printHex(String label, byte[] bytes) {
        System.out.println(label + HexFormat.of().formatHex(bytes).toUpperCase());
    }

    public static void main(String[] args) throws Exception {
        Encryption enc = new Encryption();
        enc.encrypt();

        System.out.println("Plaintext : " + PLAINTEXT);
        System.out.println();
        printHex("Ciphertext (run 1) : ", enc.ciphertext);
        System.out.println();
        printHex("Ciphertext (run 2) : ", enc.ciphertext2);
        System.out.println();
        System.out.println("Same ciphertext? " + Arrays.equals(enc.ciphertext, enc.ciphertext2));
        System.out.println("Observation: RSA-OAEP is probabilistic — same plaintext produces different ciphertext each time.");
    }
}
