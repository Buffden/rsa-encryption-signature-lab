package com.rsa;

import java.math.BigInteger;

public class Encryption {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) {

        BigInteger n = new BigInteger(Constants.PUBLIC_MODULUS, 16);
        BigInteger e = new BigInteger(Constants.PUBLIC_EXPONENT, 16);
        BigInteger m = new BigInteger(Constants.PLAINTEXT, 16);

        printBN("n = ", n);
        printBN("e = ", e);
        printBN("m = ", m);

        BigInteger c = m.modPow(e, n);

        printBN("c = ", c);
    }
}
