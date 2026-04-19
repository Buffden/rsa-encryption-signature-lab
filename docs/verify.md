# RSA Digital Signature — Verification

## Objective

Verify an RSA digital signature using the sender's public key `(e, n)` and demonstrate how any modification to the signature causes verification to fail.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | AE1CD4DC432798D933779FBD46C6E1247F0CF1233595113AA51B450F18116115 |
| e | 010001 (decimal: 65537) |
| Message (M) | Launch a missile. |
| Signature (S) | 643D6F34902D9C7EC90CB0B2BCA36C47FA37165C0005CAB026C0542CBDB6802F |

---

## What Needs to Be Done

- Verify the signature using the sender's public key: `m' = s^e mod n`
- If the recovered `m'` matches the original message `M`, the signature is valid
- Corrupt the signature by changing the last byte from `2F` to `3F`
- Repeat verification with the corrupted signature and observe the result

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Verification"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Verification" > outputs/verification.txt
```

---

## Expected Output

```text
n = AE1CD4DC432798D933779FBD46C6E1247F0CF1233595113AA51B450F18116115
e = 10001
M = 4C61756E63682061206D697373696C652E

M' from valid S   = 4C61756E63682061206D697373696C652E
Verification (valid S)     : VALID

M' from corrupted S = 91471927C80DF1E42C154FB4638CE8BC726D3D66C83A4EB6B7BE0203B41AC294
Verification (corrupted S) : INVALID
```

---

## Observations

- The recovered `M'` from the valid signature exactly matches the original message `M` — verification passes
- Changing just the last byte of the signature (`2F` → `3F`) produces a completely different `M'` — verification fails
- A single bit change in the signature is enough to make it invalid — this is by design
- This demonstrates that RSA signatures cannot be tampered with without access to the private key

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
