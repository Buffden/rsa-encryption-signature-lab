package com.rsa.textbook;

import java.math.BigInteger;

public class Encryption {

    private BigInteger ciphertext;
    private KeyGeneration keyGen;

    public BigInteger getCiphertext() { return ciphertext; }
    public KeyGeneration getKeyGeneration() { return keyGen; }

    public void encrypt() throws Exception {
        keyGen = new KeyGeneration();
        keyGen.generate();

        BigInteger m = new BigInteger(1, Constants.PLAINTEXT.getBytes("UTF-8"));
        ciphertext = m.modPow(keyGen.getE(), keyGen.getN());
        this.m = m;
    }

    private BigInteger m;
    public BigInteger getM() { return m; }

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) throws Exception {
        Encryption enc = new Encryption();
        enc.encrypt();
        printBN("m = ", enc.getM());
        printBN("c = ", enc.getCiphertext());
    }
}
