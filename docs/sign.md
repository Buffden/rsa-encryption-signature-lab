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

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Signing"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Signing" > outputs/signing.txt
```

---

## Expected Output

```text
M1 = 49206F776520796F752024323030302E
M2 = 49206F776520796F752024333030302E
S1 = 55A4E7F17F04CCFE2766E1EB32ADDBA890BBE92A6FBE2D785ED6E73CCB35E4CB
S2 = BCC20FB7568E5D48E434C387C06A6025E90D29D848AF9C3EBAC0135D99305822
Observation: signatures differ completely even for a small message change.
```

---

## Observations

- M1 and M2 differ by only one character (`2` vs `3` in the dollar amount)
- S1 and S2 are completely different — they share no visible pattern
- This is the **avalanche effect**: a tiny change in input causes a completely different output
- This property is essential for digital signature security — an attacker cannot predict or forge a signature by slightly modifying a known one

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
