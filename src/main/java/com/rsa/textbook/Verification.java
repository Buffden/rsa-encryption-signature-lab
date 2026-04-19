package com.rsa.textbook;

import java.math.BigInteger;

public class Verification {

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) throws Exception {

        BigInteger n = new BigInteger(Constants.ALICE_MODULUS, 16);
        BigInteger e = new BigInteger(Constants.ALICE_EXPONENT, 16);
        BigInteger m = new BigInteger(1, Constants.ALICE_MESSAGE.getBytes("UTF-8"));
        BigInteger sGood = new BigInteger(Constants.ALICE_SIGNATURE, 16);
        BigInteger sBad = new BigInteger(Constants.ALICE_SIGNATURE.substring(0, 62) + "3F", 16);

        printBN("n = ", n);
        printBN("e = ", e);
        printBN("M = ", m);

        // Verify valid signature
        BigInteger recovered = sGood.modPow(e, n);
        printBN("\nM' from valid S   = ", recovered);
        System.out.println("Verification (valid S)     : " + (recovered.equals(m) ? "VALID" : "INVALID"));

        // Verify corrupted signature
        BigInteger recoveredBad = sBad.modPow(e, n);
        printBN("\nM' from corrupted S = ", recoveredBad);
        System.out.println("Verification (corrupted S) : " + (recoveredBad.equals(m) ? "VALID" : "INVALID"));
    }
}
