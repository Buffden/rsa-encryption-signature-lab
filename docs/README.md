# Docs

This folder contains documentation for each step of the RSA Encryption and Signature Lab.

---

## Setup & Environment

| File | Description |
| --- | --- |
| [instance-setup-guide.md](instance-setup-guide.md) | GCP Compute Engine instance setup and environment configuration |
| [running.md](running.md) | Cloning, building, and preparing to run tasks |

---

## Contents

| # | File | Description |
| --- | --- | --- |
| 1 | [key-generation.md](key-generation.md) | Deriving the RSA private key from primes p, q, and public exponent e |
| 2 | [encrypt.md](encrypt.md) | Encrypting a plaintext message using the RSA public key |
| 3 | [decrypt.md](decrypt.md) | Decrypting a ciphertext using the RSA private key |
| 4 | [sign.md](sign.md) | Signing messages and observing the avalanche effect |
| 5 | [verify.md](verify.md) | Verifying a digital signature and testing with a corrupted signature |
| 6 | [x509-verify.md](x509-verify.md) | Manual X.509 certificate verification using RSA and SHA-256 |

---

## What This Project Proves

RSA is not magic — it is math. Every operation in this project reduces to one formula: modular exponentiation.

The six tasks together tell a complete story:

- Tasks 1–3 show the full encryption cycle: derive a private key from primes, encrypt a message with the public key, decrypt it back with the private key
- Task 4 shows that signing is mathematically the same operation as decryption — and that even a one-character change in the message produces a completely unrecognizable signature
- Task 5 shows that verification is just encryption in reverse — and that a single corrupted byte in the signature causes it to fail entirely
- Task 6 takes everything above and applies it to a live HTTPS connection — manually replicating what every browser does silently on every secure request

The conclusion: trust on the internet is built on the same primitive repeated at scale. A CA signs a certificate. A browser verifies it. The math is identical to Tasks 4 and 5 — just with larger keys and a real target.

---

## Reference

All tasks follow the SEED Labs RSA Public-Key Encryption and Signature Lab specification.

[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
