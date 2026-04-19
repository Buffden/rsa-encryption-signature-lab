# RSA Decryption — Secure Implementation

## Objective

Decrypt an RSA-OAEP encrypted ciphertext and recover the original plaintext.

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| Padding | None | RSA-OAEP with SHA-256 for both hash and MGF1 |
| Input | Fixed ciphertext from Constants.java | Ciphertext produced by `com.rsa.secure.Encryption` |
| Process | Raw modular exponentiation | OAEP unpadding handled by the Cipher API |

---

## Planned Approach

- Use `Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding")` in decrypt mode
- Pass the same explicit `OAEPParameterSpec` used during encryption — the hash and MGF1 algorithm must match exactly or decryption fails
- Decrypt the ciphertext produced by ``com.rsa.secure.Encryption``
- Verify the recovered plaintext matches the original

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption" > outputs/secure-rsa/decryption.txt
```

---

## Expected Output

```text
Original  : A top secret!
Encrypted : 22BDA843A752BD39EF23883943C27CC8...
Decrypted : A top secret!

Match: true
```

> The encrypted value will differ on every run. The decrypted value will always be `A top secret!`.

---

## Observations

- `Match: true` confirms the full OAEP encrypt-decrypt round trip works correctly
- The encrypted bytes look nothing like the original — OAEP padding randomizes the entire block before encryption
- The `OAEPParameterSpec` must be identical on both ends — any mismatch in hash algorithm or MGF1 spec throws a `BadPaddingException`

---

## Planned Class

`com.rsa.secure.Decryption`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
