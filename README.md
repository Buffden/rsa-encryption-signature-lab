# RSA Encryption and Signature Lab (Java)

[![Portfolio](https://img.shields.io/badge/buffden.com-FF6B35?style=for-the-badge&logo=googlechrome&logoColor=white)](https://buffden.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/harshwardhanpatil23)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/buffden)

---

## Overview

This project is a from-scratch implementation of RSA cryptography in Java, built to understand the internal mechanics of one of the most widely used public-key cryptosystems in the world.

It started as a textbook RSA implementation — raw modular arithmetic, no padding, no hashing — to deeply understand the math. It is now being migrated to a production-grade implementation using real-world standards: RSA-OAEP, RSA-PSS, secure key generation, and constant-time comparison.

The goal is not just to understand how RSA works — but to understand what makes it secure in practice.

---

## Execution Environment

All experiments are conducted on a **Google Cloud Platform (GCP) Compute Engine** virtual machine:

| Component | Configuration |
| --- | --- |
| Operating System | Ubuntu 25.10 LTS |
| Machine Type | e2-small |
| vCPUs | 2 |
| RAM | ~2 GB |
| Architecture | x86/64 |
| CPU Platform | Intel Broadwell |
| Persistent Disk | 10 GB |
| Access | Remote via GCP SSH-in-browser terminal |

---

## What is Implemented

| # | Task | Description |
| --- | --- | --- |
| 1 | Key Generation | Derive private key `d` from primes `p`, `q` using Euler's Totient and modular inverse |
| 2 | Encryption | `c = m^e mod n` |
| 3 | Decryption | `m = c^d mod n` |
| 4 | Signing | Sign two messages differing by one character — observe the avalanche effect |
| 5 | Verification | Verify a valid signature and show a corrupted one fails |
| 6 | X.509 Verification | Connect to `www.amazon.com`, manually verify its TLS certificate using RSA and SHA-256 |

---

## Migration to Production-Grade

The current implementation uses textbook RSA — intentionally simplified to expose the underlying math. The full migration to production-grade will replace each task with a standards-compliant implementation:

| Task | Current | After Migration |
| --- | --- | --- |
| Key Generation | Hardcoded 128-bit primes | `KeyPairGenerator` + `SecureRandom`, 2048-bit |
| Encryption | Raw `m^e mod n` | RSA-OAEP with SHA-256 |
| Decryption | Raw `c^d mod n` | RSA-OAEP via `Cipher` API |
| Signing | Raw message signed | SHA-256 + RSA-PSS with random salt |
| Verification | Manual `s^e mod n` | `Signature.verify()` + constant-time comparison |
| X.509 Verification | RSA signature only | Full chain validation + expiry + hostname + revocation |

---

## Why This Project

Most developers use cryptographic libraries as black boxes — calling `encrypt()` or `verify()` without understanding what happens underneath. This project breaks that abstraction by:

- Working directly with arbitrary precision integers via `java.math.BigInteger`
- Implementing modular exponentiation manually using `modPow()`
- Deriving private keys using Euler's Totient Function and modular inverse
- Verifying real-world X.509 certificates without relying on automated tools
- Connecting to a live HTTPS server and manually validating its certificate chain
- Migrating the same operations to production standards to understand exactly what each protection adds

> You should understand the primitives before trusting the abstractions.

---

## Tech Stack

| Component | Technology |
| --- | --- |
| Language | Java 17+ |
| Arithmetic | `java.math.BigInteger` |
| Secure Crypto API | `javax.crypto`, `java.security` |
| Certificate Handling | `javax.net.ssl`, `java.security.cert` |
| Hashing | `java.security.MessageDigest` (SHA-256) |
| Build Tool | Maven |
| External Libraries | None |
| Runtime Environment | GCP Compute Engine (Ubuntu 25.10 LTS) |

---

## Lessons Learned

- Cryptography is easy to misuse and hard to implement correctly
- The math is elegant — the security comes from careful implementation
- Even a single bit change in a signature makes verification fail completely
- TLS trust is transitive — a browser trusts a server because a CA it already trusts has signed its certificate
- `java.math.BigInteger` is powerful enough to implement real cryptographic operations with zero dependencies
- Knowing why padding exists means you will never skip it

---

## References

- SEED Labs — RSA Public-Key Encryption and Signature Lab
  [https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)

- Java BigInteger Documentation
  [https://docs.oracle.com/en/java/docs/api/java.base/java/math/BigInteger.html](https://docs.oracle.com/en/java/docs/api/java.base/java/math/BigInteger.html)

- RFC 8017 — PKCS #1: RSA Cryptography Specifications
  [https://datatracker.ietf.org/doc/html/rfc8017](https://datatracker.ietf.org/doc/html/rfc8017)

- RSA Original Paper (1978) — Rivest, Shamir, Adleman

---

## License

This project is for **educational purposes only**.

---

## Author

**Harshwardhan Patil**
MS Software Engineering @ UTA | Ex-Clarivate | Angular · Spring Boot · AWS · PostgreSQL
