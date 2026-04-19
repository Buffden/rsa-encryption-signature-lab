package com.rsa.textbook;

import java.math.BigInteger;

public class Signing {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        KeyGeneration keyGen = new KeyGeneration();
        keyGen.generate();

        BigInteger m1 = new BigInteger(1, Constants.MSG_OWE_2000.getBytes("UTF-8"));
        BigInteger m2 = new BigInteger(1, Constants.MSG_OWE_3000.getBytes("UTF-8"));

        BigInteger s1 = m1.modPow(keyGen.getD(), keyGen.getN());
        BigInteger s2 = m2.modPow(keyGen.getD(), keyGen.getN());

        printBN("M1 = ", m1);
        printBN("M2 = ", m2);
        printBN("S1 = ", s1);
        printBN("S2 = ", s2);

        if (s1.equals(s2)) {
            System.out.println("Observation: signatures are identical — unexpected.");
        } else {
            System.out.println("Observation: signatures differ completely even for a small message change.");
        }
    }
}
