# Production-Grade RSA

The same RSA pipeline rebuilt using real-world standards — RSA-OAEP, RSA-PSS, secure key generation, and constant-time verification. This is the migration of the textbook implementation to production-safe Java code under the `com.rsa.secure` package.

---

## Setup & Environment

| File | Description |
| --- | --- |
| [instance-setup-guide.md](instance-setup-guide.md) | GCP Compute Engine instance setup and environment configuration |
| [running.md](running.md) | Cloning, building, and running production-grade tasks |

---

## Tasks

| # | File | Class | Description |
| --- | --- | --- | --- |
| 1 | [key-generation.md](key-generation.md) | `com.rsa.secure.KeyGeneration` | Secure 2048-bit key generation using SecureRandom |
| 2 | [encrypt.md](encrypt.md) | `com.rsa.secure.Encryption` | Probabilistic encryption using RSA-OAEP with SHA-256 |
| 3 | [decrypt.md](decrypt.md) | `com.rsa.secure.Decryption` | Decryption of RSA-OAEP encrypted ciphertext |
| 4 | [sign.md](sign.md) | `com.rsa.secure.Signing` | Signing with SHA-256 hashing and RSA-PSS |
| 5 | [verify.md](verify.md) | `com.rsa.secure.Verification` | Signature verification with RSA-PSS and constant-time comparison |
| 6 | [x509-verify.md](x509-verify.md) | `com.rsa.secure.X509Verification` | Full X.509 chain validation with expiry, hostname, and revocation checks |

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| Key size | 128-bit hardcoded | 2048-bit via SecureRandom |
| Encryption padding | None (raw `m^e mod n`) | RSA-OAEP with explicit SHA-256 for hash and MGF1 |
| Signing | Raw message, no hash | SHA-256 + RSA-PSS with 32-byte random salt |
| Determinism | Same input = same output | Probabilistic — different ciphertext/signature every run |
| Timing safety | `BigInteger.equals()` — not constant-time | `Signature.verify()` — constant-time internally |
| Parameter safety | N/A | Explicit `OAEPParameterSpec` and `PSSParameterSpec` — no SHA-1 defaults |
