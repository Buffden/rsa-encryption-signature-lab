# RSA Decryption

## Objective

Decrypt a ciphertext message using the RSA private key `(d, n)` and recover the original plaintext.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | E103ABD94892E3E74AFD724BF28E78366D9676BCCC70118BD0AA1968DBB143D1 |
| e | 0D88C3 (decimal: 895,171) |
| d | 3587A24598E5F2A21DB007D89D18CC50ABA5075BA19A33890FE7C28A9B496AEB |
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
