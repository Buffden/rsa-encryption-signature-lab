package com.rsa.textbook;

import java.math.BigInteger;

public class KeyGeneration {

    private BigInteger n;
    private BigInteger e;
    private BigInteger d;

    public BigInteger getN() { return n; }
    public BigInteger getE() { return e; }
    public BigInteger getD() { return d; }

    public void generate() {
        BigInteger p   = new BigInteger(Constants.PRIME_P, 16);
        BigInteger q   = new BigInteger(Constants.PRIME_Q, 16);
        BigInteger p1  = p.subtract(BigInteger.ONE);
        BigInteger q1  = q.subtract(BigInteger.ONE);
        BigInteger phi = p1.multiply(q1);

        e = new BigInteger(Constants.KEYGEN_EXPONENT, 16);
        n = p.multiply(q);
        d = e.modInverse(phi);

        this.p   = p;
        this.q   = q;
        this.phi = phi;
    }

    private BigInteger p, q, phi;

    public BigInteger getP()   { return p; }
    public BigInteger getQ()   { return q; }
    public BigInteger getPhi() { return phi; }

    static void printBN(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) {
        KeyGeneration kg = new KeyGeneration();
        kg.generate();
        printBN("p   = ", kg.getP());
        printBN("q   = ", kg.getQ());
        printBN("e   = ", kg.getE());
        printBN("n   = ", kg.getN());
        printBN("phi = ", kg.getPhi());
        printBN("d   = ", kg.getD());
    }
}
