# RSA Encryption

## Objective

Encrypt a plaintext message using the RSA public key `(e, n)`.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | E103ABD94892E3E74AFD724BF28E78366D9676BCCC70118BD0AA1968DBB143D1 |
| e | 0D88C3 (decimal: 895,171) |
| Message (M) | A top secret! |

> The private key `d` is also provided to help verify the encryption result:
> d = 74D806F9F3A62BAE331FFE3F0A68AFE35B3D2E4794148AACBC26AA381CD7D30D

---

## What Needs to Be Done

- Convert the plaintext message `"A top secret!"` to its hex representation
- Encrypt using the public key: `c = m^e mod n`
- The hex representation of `"A top secret!"` is:

```text
4120746f702073656372657421
```

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
