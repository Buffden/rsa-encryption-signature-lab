package com.rsa;

import java.math.BigInteger;

public class Signing {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) {

        BigInteger n = new BigInteger(Constants.PUBLIC_MODULUS, 16);
        BigInteger d = new BigInteger(Constants.PRIVATE_EXPONENT, 16);
        BigInteger m1 = new BigInteger(Constants.MSG_OWE_2000, 16);
        BigInteger m2 = new BigInteger(Constants.MSG_OWE_3000, 16);

        printBN("M1 = ", m1);
        printBN("M2 = ", m2);

        BigInteger s1 = m1.modPow(d, n);
        BigInteger s2 = m2.modPow(d, n);

        printBN("S1 = ", s1);
        printBN("S2 = ", s2);

        if (s1.equals(s2)) {
            System.out.println("Observation: signatures are identical — unexpected.");
        } else {
            System.out.println("Observation: signatures differ completely even for a small message change.");
        }
    }
}
