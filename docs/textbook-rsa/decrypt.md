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

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
