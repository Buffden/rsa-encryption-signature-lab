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
| Ciphertext (C) | Output of Task 2 — produced by encrypting `"A top secret!"` |

> The public/private keys used here are the same as in the Encryption section.

---

## What Needs to Be Done

- Decrypt the ciphertext `C` using the private key: `m = c^d mod n`
- Convert the recovered hex value back to a plain ASCII string
- The ciphertext `C` is the output of Task 2 — encrypting `"A top secret!"` with the public key `(e, n)`
- Decrypting it with the private key should recover `"A top secret!"` — confirming the full encrypt-decrypt round trip

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
