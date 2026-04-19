package com.rsa.textbook;

import java.math.BigInteger;

public class Decryption {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        Encryption enc = new Encryption();
        enc.encrypt();

        BigInteger n = enc.getKeyGeneration().getN();
        BigInteger d = enc.getKeyGeneration().getD();
        BigInteger c = enc.getCiphertext();

        BigInteger m = c.modPow(d, n);

        printBN("d = ", d);
        printBN("c = ", c);
        printBN("m = ", m);
        System.out.println("ASCII = " + new String(m.toByteArray()));
    }
}
