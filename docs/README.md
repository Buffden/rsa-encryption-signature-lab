# Docs

This folder contains documentation for each task of the RSA Encryption and Signature Lab.

---

## Setup & Environment

| File | Description |
| --- | --- |
| [textbook-rsa/instance-setup-guide.md](textbook-rsa/instance-setup-guide.md) | GCP Compute Engine instance setup and environment configuration |
| [textbook-rsa/running.md](textbook-rsa/running.md) | Cloning, building, and running textbook RSA tasks (`com.rsa.textbook`) |
| [production-grade/instance-setup-guide.md](production-grade/instance-setup-guide.md) | GCP Compute Engine instance setup and environment configuration |
| [production-grade/running.md](production-grade/running.md) | Cloning, building, and running production-grade tasks (`com.rsa.secure`) |

---

## Textbook RSA

RSA is not magic — it is math. Every operation reduces to one formula: modular exponentiation.

The six tasks tell a complete story:

- Tasks 1–3 show the full encryption cycle: derive a private key from primes, encrypt a message with the public key, decrypt it back with the private key
- Task 4 shows that signing is mathematically the same operation as decryption — and that even a one-character change in the message produces a completely unrecognizable signature
- Task 5 shows that verification is just encryption in reverse — and that a single corrupted byte in the signature causes it to fail entirely
- Task 6 takes everything above and applies it to a live HTTPS connection — manually replicating what every browser does silently on every secure request

| # | File | Description |
| --- | --- | --- |
| 1 | [textbook-rsa/key-generation.md](textbook-rsa/key-generation.md) | Deriving the RSA private key from primes p, q, and public exponent e |
| 2 | [textbook-rsa/encrypt.md](textbook-rsa/encrypt.md) | Encrypting a plaintext message using the RSA public key |
| 3 | [textbook-rsa/decrypt.md](textbook-rsa/decrypt.md) | Decrypting a ciphertext using the RSA private key |
| 4 | [textbook-rsa/sign.md](textbook-rsa/sign.md) | Signing messages and observing the avalanche effect |
| 5 | [textbook-rsa/verify.md](textbook-rsa/verify.md) | Verifying a digital signature and testing with a corrupted signature |
| 6 | [textbook-rsa/x509-verify.md](textbook-rsa/x509-verify.md) | Manual X.509 certificate verification using RSA and SHA-256 |

---

## Production-Grade Migration

The same six tasks rebuilt under `com.rsa.secure` using real-world standards — RSA-OAEP, RSA-PSS, secure key generation, and constant-time verification.

| # | File | Class | Description |
| --- | --- | --- | --- |
| 1 | [production-grade/key-generation.md](production-grade/key-generation.md) | `com.rsa.secure.KeyGeneration` | Secure 2048-bit key generation using SecureRandom |
| 2 | [production-grade/encrypt.md](production-grade/encrypt.md) | `com.rsa.secure.Encryption` | Probabilistic encryption using RSA-OAEP with SHA-256 |
| 3 | [production-grade/decrypt.md](production-grade/decrypt.md) | `com.rsa.secure.Decryption` | Decryption of RSA-OAEP encrypted ciphertext |
| 4 | [production-grade/sign.md](production-grade/sign.md) | `com.rsa.secure.Signing` | Signing with SHA-256 hashing and RSA-PSS |
| 5 | [production-grade/verify.md](production-grade/verify.md) | `com.rsa.secure.Verification` | Signature verification with RSA-PSS and constant-time comparison |
| 6 | [production-grade/x509-verify.md](production-grade/x509-verify.md) | `com.rsa.secure.X509Verification` | Full X.509 chain validation with expiry, hostname, and revocation checks |

---

## Reference

All tasks follow the SEED Labs RSA Public-Key Encryption and Signature Lab specification.

[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
