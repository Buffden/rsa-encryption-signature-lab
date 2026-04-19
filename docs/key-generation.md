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

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.KeyGeneration"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.KeyGeneration" > outputs/key-generation.txt
```

---

## Expected Output

```text
p   = F7E75FDC469067FFDC4E847C51F452DF
q   = E85CED54AF57E53E092113E62F436F4F
e   = D88C3
n   = E103ABD94892E3E74AFD724BF28E78366D9676BCCC70118BD0AA1968DBB143D1
phi = E103ABD94892E3E74AFD724BF28E78348D52298BD687C44DEB3A81065A7981A4
d   = 3587A24598E5F2A21DB007D89D18CC50ABA5075BA19A33890FE7C28A9B496AEB
```

---

## Observations

- `n` is the product of `p` and `q` — the RSA modulus
- `phi` is Euler's Totient: `(p-1) * (q-1)` — slightly smaller than `n`
- `d` is the modular inverse of `e` with respect to `phi` — this is the private key
- Knowing `d` allows decryption; keeping it secret is the entire basis of RSA security

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
