# RSA Key Generation — Deriving the Private Key

## Objective

Given three numbers `p`, `q`, and `e`, compute the RSA private key `d`.

`n = p * q` is the RSA modulus. The pair `(e, n)` forms the public key. The goal is to derive the corresponding private key `d`.

---

## Given Values

| Parameter | Hex Value |
| --- | --- |
| p | F7E75FDC469067FFDC4E847C51F452DF |
| q | E85CED54AF57E53E092113E62F436F4F |
| e | 0D88C3 |

> Note: Although `p` and `q` are large numbers, they are intentionally small for demonstration purposes (128-bit). In practice, these should be at least 512 bits long to be secure.

---

## What Needs to Be Done

- Use `p`, `q`, and `e` to calculate the private key `d`
- All computations use large integer arithmetic (Java `BigInteger`)

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
