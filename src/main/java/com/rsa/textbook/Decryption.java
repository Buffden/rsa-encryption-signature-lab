package com.rsa.textbook;

import java.math.BigInteger;

public class Decryption {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) {

        BigInteger n = new BigInteger(Constants.PUBLIC_MODULUS, 16);
        BigInteger d = new BigInteger(Constants.PRIVATE_EXPONENT, 16);
        BigInteger c = new BigInteger(Constants.CIPHERTEXT, 16);

        printBN("n = ", n);
        printBN("d = ", d);
        printBN("c = ", c);

        BigInteger m = c.modPow(d, n);

        printBN("m = ", m);
        System.out.println("ASCII = " + new String(m.toByteArray()));
    }
}
