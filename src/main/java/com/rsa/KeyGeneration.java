package com.rsa;

import java.math.BigInteger;

public class KeyGeneration {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) {

        BigInteger p = new BigInteger(Constants.PRIME_P, 16);
        BigInteger q = new BigInteger(Constants.PRIME_Q, 16);
        BigInteger e = new BigInteger(Constants.KEYGEN_EXPONENT, 16);

        BigInteger n   = p.multiply(q);
        BigInteger p1  = p.subtract(BigInteger.ONE);
        BigInteger q1  = q.subtract(BigInteger.ONE);
        BigInteger phi = p1.multiply(q1);
        BigInteger d   = e.modInverse(phi);

        printBN("p   = ", p);
        printBN("q   = ", q);
        printBN("e   = ", e);
        printBN("n   = ", n);
        printBN("phi = ", phi);
        printBN("d   = ", d);
    }
}
