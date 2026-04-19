package com.rsa.textbook;

public class Constants {

    // key generation — primes and public exponent (hex, given by SEED Labs)
    public static final String PRIME_P         = "F7E75FDC469067FFDC4E847C51F452DF";
    public static final String PRIME_Q         = "E85CED54AF57E53E092113E62F436F4F";
    public static final String KEYGEN_EXPONENT = "0D88C3";

    // plaintext message for encryption / decryption
    public static final String PLAINTEXT = "A top secret!";

    // two messages for signing — differ only in the dollar amount
    public static final String MSG_OWE_2000 = "I owe you $2000.";
    public static final String MSG_OWE_3000 = "I owe you $3000.";

    // Alice's public key and signature for verification (hex, given by SEED Labs)
    public static final String ALICE_MODULUS   = "AE1CD4DC432798D933779FBD46C6E1247F0CF1233595113AA51B450F18116115";
    public static final String ALICE_EXPONENT  = "010001";
    public static final String ALICE_MESSAGE   = "Launch a missile.";
    public static final String ALICE_SIGNATURE = "643D6F34902D9C7EC90CB0B2BCA36C47FA37165C0005CAB026C0542CBDB6802F";
}
