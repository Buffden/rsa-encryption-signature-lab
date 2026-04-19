# RSA Digital Signature — Signing a Message

## Objective

Generate RSA digital signatures for two messages using the private key `(d, n)` and observe how even a small change in the message produces a completely different signature.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | DCBFFE3E51F62E09CE7032E2677A78946A849DC4CDDE3A4D0CB81629242FB1A5 |
| e | 010001 (decimal: 65537) |
| d | 74D806F9F3A62BAE331FFE3F0A68AFE35B3D2E4794148AACBC26AA381CD7D30D |
| Message 1 | I owe you $2000. |
| Message 2 | I owe you $3000. |

> The public/private keys used here are the same as in the Encryption and Decryption sections.

---

## What Needs to Be Done

- Sign Message 1 directly using the private key: `s = m^d mod n`
- Make a slight change to the message — change `$2000` to `$3000`
- Sign Message 2 using the same private key
- Compare both signatures and observe the difference

> Note: Sign the messages directly without hashing.

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
