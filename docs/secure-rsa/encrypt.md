# RSA Encryption — Secure Implementation

## Objective

Encrypt a plaintext message using RSA-OAEP padding instead of raw textbook RSA.

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| Padding | None | RSA-OAEP with SHA-256 for both hash and MGF1 |
| Determinism | Same input always produces same ciphertext | Probabilistic — same input produces different ciphertext each run |
| Vulnerability | Chosen-plaintext attacks | Resistant to chosen-plaintext attacks |

---

## Planned Approach

- Use `Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")`
- Pass an explicit `OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, ...)` — without this, Java defaults MGF1 to SHA-1, which is not production safe
- Encrypt the same plaintext as Task 2 (`"A top secret!"`)
- Run twice and show the ciphertext differs each time — demonstrating probabilistic encryption

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption" > outputs/secure-rsa/encryption.txt
```

---

## Expected Output

```text
Plaintext : A top secret!

Ciphertext (run 1) : 34FF816DDD5D6FF70FE7D1D89DEAD929...
Ciphertext (run 2) : 03D1EEA079437D49AFCE49757126D0DA...

Same ciphertext? false
Observation: RSA-OAEP is probabilistic — same plaintext produces different ciphertext each time.
```

> Both ciphertexts will differ from the values above on every run.

---

## Observations

- Run 1 and Run 2 produce completely different ciphertexts from the same plaintext — this is RSA-OAEP's random seed in action
- `Same ciphertext? false` — confirms probabilistic encryption
- In the textbook version, the same message always produced the same ciphertext — an attacker could build a dictionary and look up plaintexts. OAEP eliminates this entirely.

---

## Planned Class

`com.rsa.secure.Encryption`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
