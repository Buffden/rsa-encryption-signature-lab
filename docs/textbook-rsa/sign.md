# RSA Digital Signature — Signing a Message

## Objective

Generate RSA digital signatures for two messages using the private key `(d, n)` and observe how even a small change in the message produces a completely different signature.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | E103ABD94892E3E74AFD724BF28E78366D9676BCCC70118BD0AA1968DBB143D1 |
| e | 0D88C3 (decimal: 895,171) |
| d | 3587A24598E5F2A21DB007D89D18CC50ABA5075BA19A33890FE7C28A9B496AEB |
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
