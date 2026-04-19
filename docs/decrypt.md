# RSA Decryption

## Objective

Decrypt a ciphertext message using the RSA private key `(d, n)` and recover the original plaintext.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | DCBFFE3E51F62E09CE7032E2677A78946A849DC4CDDE3A4D0CB81629242FB1A5 |
| e | 010001 (decimal: 65537) |
| d | 74D806F9F3A62BAE331FFE3F0A68AFE35B3D2E4794148AACBC26AA381CD7D30D |
| Ciphertext (C) | 8C0F971DF2F3672B28811407E2DABBE1DA0FEBBBDFC7DCB67396567EA1E2493F |

> The public/private keys used here are the same as in the Encryption section.

---

## What Needs to Be Done

- Decrypt the ciphertext `C` using the private key: `m = c^d mod n`
- Convert the recovered hex value back to a plain ASCII string
- The SEED Labs PDF provides the ciphertext without revealing the expected plaintext — decrypting it and discovering the message is the objective

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Decryption"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Decryption" > outputs/decryption.txt
```

---

## Expected Output

```text
n = DCBFFE3E51F62E09CE7032E2677A78946A849DC4CDDE3A4D0CB81629242FB1A5
d = 74D806F9F3A62BAE331FFE3F0A68AFE35B3D2E4794148AACBC26AA381CD7D30D
c = 8C0F971DF2F3672B28811407E2DABBE1DA0FEBBBDFC7DCB67396567EA1E2493F
m = 50617373776F72642069732064656573
ASCII = Password is dees
```

---

## Observations

- The recovered hex `m` converts back to the ASCII string `"Password is dees"`
- This confirms that `c^d mod n` correctly reverses the encryption operation `m^e mod n`
- RSA encryption and decryption are mathematical inverses when using the matching key pair

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
