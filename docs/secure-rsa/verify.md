# RSA Digital Signature — Secure Verification

## Objective

Verify an RSA-PSS signature and demonstrate that a corrupted signature fails — using constant-time comparison to prevent timing attacks.

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| Verification method | Manual `s^e mod n` and compare | `Signature.verify()` with RSA-PSS |
| Timing attack risk | `BigInteger.equals()` — not constant-time | `Signature.verify()` — constant-time internally |
| Parameter safety | N/A | Explicit `PSSParameterSpec` required — no SHA-1 defaults |

---

## Planned Approach

- Use `Signature.getInstance("RSASSA-PSS")` in verify mode
- Set the same explicit `PSSParameterSpec` used during signing — parameters must match or verification fails
- Verify a valid PSS signature — expect `true`
- Corrupt the signature by modifying one byte
- Verify the corrupted signature — expect `false`
- `Signature.verify()` handles constant-time comparison internally — no manual byte comparison needed

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification" > outputs/secure-rsa/verification.txt
```

---

## Expected Output

```text
Message : Launch a missile.

Verification (valid signature)     : VALID
Verification (corrupted signature) : INVALID
```

---

## Observations

- Valid PSS signature verifies correctly — `VALID`
- A single bit flip in the signature causes verification to fail — `INVALID`
- No manual hash comparison is needed — `Signature.verify()` handles the full PSS verification and comparison internally
- The result is identical to the textbook version in terms of outcome, but the underlying mechanism is fundamentally more secure

---

## Planned Class

`com.rsa.secure.Verification`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
