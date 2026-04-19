package com.rsa.textbook;

import java.math.BigInteger;

public class Encryption {

    private BigInteger ciphertext;

    public BigInteger getCiphertext() {
        return ciphertext;
    }

    public void encrypt() {
        BigInteger n = new BigInteger(Constants.PUBLIC_MODULUS, 16);
        BigInteger e = new BigInteger(Constants.PUBLIC_EXPONENT, 16);
        BigInteger m = new BigInteger(Constants.PLAINTEXT, 16);

        printBN("n = ", n);
        printBN("e = ", e);
        printBN("m = ", m);

        ciphertext = m.modPow(e, n);

        printBN("c = ", ciphertext);
    }

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) {
        new Encryption().encrypt();
    }
}
