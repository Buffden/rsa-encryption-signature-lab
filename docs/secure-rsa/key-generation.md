# RSA Key Generation — Secure Implementation

## Objective

Generate a 2048-bit RSA key pair using cryptographically secure randomness instead of hardcoded primes.

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| Key size | 128-bit | 2048-bit |
| Primes | Hardcoded in Constants.java | Generated at runtime using SecureRandom |
| Predictability | Fixed — same keys every run | Random — different keys every run |

---

## Planned Approach

- Use `KeyPairGenerator.getInstance("RSA")`
- Initialize with `KeySize 2048` and `SecureRandom`
- Extract and print `n`, `e`, `d` from the generated key pair

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration" > outputs/secure-rsa/key-generation.txt
```

---

## Expected Output

```text
Key size : 2048-bit
Algorithm: RSA

e = 10001
n = C0C25AC31DDF44F5535659DC751D62FC...
d = 738B8D45C1D5A0CBC07AD5E98A8D0FAA...
```

> `n` and `d` will be different on every run — keys are generated fresh each time using SecureRandom.

---

## Observations

- `e = 10001` (65537) every run — this is the standard production public exponent, hardcoded by the JCA provider
- `n` is 512 hex characters = 2048 bits, confirmed
- `d` changes every run because `p` and `q` are randomly generated — unlike the textbook version where all values were fixed

---

## Planned Class

`com.rsa.secure.KeyGeneration`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
