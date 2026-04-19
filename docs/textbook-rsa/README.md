# Textbook RSA

Raw RSA implemented from scratch using `java.math.BigInteger` — no padding, no hashing, no libraries. Built to understand the mathematical foundations before moving to production-grade standards.

---

## Setup & Environment

| File | Description |
| --- | --- |
| [instance-setup-guide.md](instance-setup-guide.md) | GCP Compute Engine instance setup and environment configuration |
| [running.md](running.md) | Cloning, building, and running textbook RSA tasks |

---

## Tasks

| # | File | Description |
| --- | --- | --- |
| 1 | [key-generation.md](key-generation.md) | Deriving the RSA private key from primes p, q, and public exponent e |
| 2 | [encrypt.md](encrypt.md) | Encrypting a plaintext message using the RSA public key |
| 3 | [decrypt.md](decrypt.md) | Decrypting a ciphertext using the RSA private key |
| 4 | [sign.md](sign.md) | Signing messages and observing the avalanche effect |
| 5 | [verify.md](verify.md) | Verifying a digital signature and testing with a corrupted signature |
| 6 | [x509-verify.md](x509-verify.md) | Manual X.509 certificate verification using RSA and SHA-256 |

---

## Limitations

This implementation is intentionally not production safe — it exists to expose the math underneath.

| Issue | Detail |
| --- | --- |
| No padding | Deterministic — same message always produces the same ciphertext |
| No hashing before signing | Vulnerable to signature forgery |
| Small keys | 128-bit keys can be factored in seconds |
| Not constant-time | Timing attacks can reconstruct the private key |
| Hardcoded primes | No secure random key generation |

These are addressed in the production-grade migration.
