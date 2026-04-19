package com.rsa;

public class Constants {

    // key generation — primes and public exponent
    public static final String PRIME_P          = "F7E75FDC469067FFDC4E847C51F452DF";
    public static final String PRIME_Q          = "E85CED54AF57E53E092113E62F436F4F";
    public static final String KEYGEN_EXPONENT  = "0D88C3";

    // shared RSA key pair used for encryption, decryption, and signing
    public static final String PUBLIC_MODULUS   = "DCBFFE3E51F62E09CE7032E2677A78946A849DC4CDDE3A4D0CB81629242FB1A5";
    public static final String PUBLIC_EXPONENT  = "010001";
    public static final String PRIVATE_EXPONENT = "74D806F9F3A62BAE331FFE3F0A68AFE35B3D2E4794148AACBC26AA381CD7D30D";
    public static final String PLAINTEXT        = "4120746f702073656372657421"; // "A top secret!"
    public static final String CIPHERTEXT       = "8C0F971DF2F3672B28811407E2DABBE1DA0FEBBBDFC7DCB67396567EA1E2493F";

    // two messages for signing — differ only in the dollar amount
    public static final String MSG_OWE_2000     = "49206F776520796F752024323030302E"; // "I owe you $2000."
    public static final String MSG_OWE_3000     = "49206F776520796F752024333030302E"; // "I owe you $3000."

    // Alice's public key, message, and signature for verification
    public static final String ALICE_MODULUS    = "AE1CD4DC432798D933779FBD46C6E1247F0CF1233595113AA51B450F18116115";
    public static final String ALICE_EXPONENT   = "010001";
    public static final String ALICE_MESSAGE    = "4C61756E63682061206D697373696C652E"; // "Launch a missile."
    public static final String ALICE_SIGNATURE  = "643D6F34902D9C7EC90CB0B2BCA36C47FA37165C0005CAB026C0542CBDB6802F";
}
