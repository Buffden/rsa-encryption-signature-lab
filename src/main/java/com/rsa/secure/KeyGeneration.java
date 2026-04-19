package com.rsa.secure;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateCrtKey;
import java.security.interfaces.RSAPublicKey;

public class KeyGeneration {

    private KeyPair keyPair;

    public PublicKey getPublicKey()   { return keyPair.getPublic(); }
    public PrivateKey getPrivateKey() { return keyPair.getPrivate(); }

    public void generate() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048, new SecureRandom());
        keyPair = kg.generateKeyPair();
    }

    static void printHex(String label, BigInteger value) {
        System.out.println(label + value.toString(16).toUpperCase());
    }

    public static void main(String[] args) throws Exception {
        KeyGeneration kg = new KeyGeneration();
        kg.generate();

        RSAPublicKey pub   = (RSAPublicKey) kg.getPublicKey();
        RSAPrivateCrtKey priv = (RSAPrivateCrtKey) kg.getPrivateKey();

        System.out.println("Key size : 2048-bit");
        System.out.println("Algorithm: " + pub.getAlgorithm());
        System.out.println();
        printHex("e = ", pub.getPublicExponent());
        printHex("n = ", pub.getModulus());
        printHex("d = ", priv.getPrivateExponent());
    }
}
