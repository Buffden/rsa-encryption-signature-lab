# RSA Digital Signature — Secure Implementation

## Objective

Sign messages using SHA-256 hashing and RSA-PSS — the standard used in real-world systems.

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| What gets signed | Raw message bytes | SHA-256 hash of the message |
| Padding | None | RSA-PSS with random salt |
| Forgery risk | Vulnerable to signature forgery | Resistant |
| Determinism | Same message always produces same signature | Probabilistic — salt changes each run |

---

## Planned Approach

- Use `Signature.getInstance("RSASSA-PSS")`
- Set explicit `PSSParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, 32, 1)` — without this, Java defaults to SHA-1, which is not production safe
- Sign the same two messages from Task 4 (`"I owe you $2000."` and `"I owe you $3000."`)
- Show signatures still differ completely between the two messages
- Show that signing the same message twice also produces different signatures due to random salt

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing" > outputs/secure-rsa/signing.txt
```

---

## Expected Output

```text
M1 : I owe you $2000.
M2 : I owe you $3000.

S1       : 2925295850AD4B86F65D490602A7B3EA...
S2       : AD31D65AD2B081F15B9668FD13BB57F9...
S1 again : 34FA9ACBE98FBDA50EFE977E9F41AAA3...

S1 == S2?        false
S1 == S1 again?  false
Observation: signatures differ between messages AND between runs — RSA-PSS random salt.
```

> All signature values will differ on every run.

---

## Observations

- `S1 == S2? false` — different messages produce different signatures, same as textbook
- `S1 == S1 again? false` — this is new and unique to PSS: signing the same message twice produces different signatures due to the random salt embedded in the padding
- In the textbook version, signing the same message twice always gave the same signature — an attacker could use this to correlate signing operations. PSS eliminates this.

---

## Planned Class

`com.rsa.secure.Signing`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
