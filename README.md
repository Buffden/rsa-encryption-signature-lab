# RSA Encryption and Signature Lab (Java)

[![Portfolio](https://img.shields.io/badge/buffden.com-FF6B35?style=for-the-badge&logo=googlechrome&logoColor=white)](https://buffden.com)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/harshwardhanpatil23)
[![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/buffden)

---

## Overview

This project is a from-scratch implementation of RSA cryptography in Java, built to understand the internal mechanics of one of the most widely used public-key cryptosystems in the world.

It covers the complete RSA pipeline:

- Private key derivation from public parameters
- Public-key encryption and private-key decryption
- Digital signature generation and verification
- Manual X.509 certificate signature verification against a live TLS connection

The goal is to understand how RSA works at the mathematical level, beyond what high-level libraries abstract away, and to explore how trust is established in real-world systems like HTTPS.

> This project implements textbook RSA for learning purposes only. It is **NOT secure for production use**.

---

## Execution Environment

This project is implemented and executed in a **controlled cloud environment** — not on a local machine.

All experiments are conducted on a **Google Cloud Platform (GCP) Compute Engine** virtual machine with the following configuration:

| Component | Configuration |
|---|---|
| Operating System | Ubuntu 25.10 LTS |
| Machine Type | e2-small |
| vCPUs | 2 |
| RAM | ~2 GB |
| Architecture | x86/64 |
| CPU Platform | Intel Broadwell |
| Persistent Disk | 10 GB |
| Access | Remote via GCP SSH-in-browser terminal |

### Why a Controlled Environment

- Ensures a consistent, reproducible setup across all tasks
- Avoids dependency conflicts that may arise on local machines
- Mirrors how cryptographic tooling is deployed in real-world server environments
- Keeps sensitive key material off personal devices

---

## Why This Project

Most developers use cryptographic libraries as black boxes — calling `encrypt()` or `verify()` without understanding what happens underneath. This project breaks that abstraction by:

- Working directly with arbitrary precision integers via `java.math.BigInteger`
- Implementing modular exponentiation manually using `modPow()`
- Deriving private keys using Euler's Totient Function and modular inverse
- Verifying real-world X.509 certificates without relying on automated tools
- Connecting to a live HTTPS server and manually validating its certificate chain

This aligns with a core security principle:

> You should understand the primitives before trusting the abstractions.

---

## Tech Stack

| Component | Technology |
|---|---|
| Language | Java 17+ |
| Arithmetic | `java.math.BigInteger` |
| Certificate Handling | `javax.net.ssl`, `java.security.cert` |
| Hashing | `java.security.MessageDigest` (SHA-256) |
| Build Tool | Maven |
| External Libraries | None |
| Runtime Environment | GCP Compute Engine (Ubuntu 25.10 LTS) |

---

## Key Concepts Demonstrated

### RSA Mathematics

- Modular arithmetic
- Euler's Totient Function
- Modular inverse computation
- Fast modular exponentiation

### Security Concepts

- Public vs private key separation
- Digital signature authenticity and tamper detection
- Certificate trust chains in TLS
- Avalanche effect in cryptographic operations

### Java-Specific Insight

- `BigInteger.modPow()` for efficient modular exponentiation
- `BigInteger.modInverse()` for private key derivation
- `X509Certificate` API for live certificate parsing
- `MessageDigest` for SHA-256 hashing
- Zero external dependencies — pure Java SE

---

## Security Limitations

This implementation is **NOT secure for production use**.

### What is missing

- No padding schemes (OAEP for encryption, PSS for signatures)
- No hashing before signing
- No side-channel attack protections
- No constant-time operations
- No secure key storage or key generation

### Vulnerabilities of textbook RSA

- Chosen-plaintext attacks
- Signature forgery
- Timing attacks

### What real-world systems use

- RSA-OAEP for encryption
- RSA-PSS for signatures
- Hybrid encryption (RSA + AES-GCM)

---

## Then Why Build This?

> "I built the insecure version so I could deeply understand what makes the secure version secure."

This is the right question to ask — and here is the honest answer.

### You understand what every crypto library hides from you

When you call `Cipher.getInstance("RSA")` in any language, it runs exactly what is built here — plus padding, hashing, and constant-time operations on top. This project shows what is underneath.

### You can reason about security failures

Most security breaches happen because a developer misused a library they did not understand. Knowing why padding exists means you will never skip it. Knowing why key size matters means you will never use a weak one.

### Task 6 shows how the entire internet's trust model works

Every HTTPS connection — banking, email, everything — relies on exactly what Task 6 does manually. The certificate your browser silently verifies thousands of times a day is verified the same way this project does it, step by step.

### The limitations you know are the lessons

Being able to articulate why this is not production safe — no OAEP, no PSS, timing attacks, small keys — is proof of deep understanding. That is worth more in an interview than knowing how to call a library.

---

## Lessons Learned

- Cryptography is easy to misuse and hard to implement correctly
- The math is elegant — the security comes from careful implementation
- Even a single bit change in a signature makes verification fail completely
- TLS trust is transitive — a browser trusts a server because a CA it already trusts has signed its certificate
- `java.math.BigInteger` is powerful enough to implement real cryptographic operations with zero dependencies

---

## Future Improvements

- Add RSA-OAEP padding
- Implement RSA-PSS signatures
- Integrate SHA-256 hashing before signing
- Add benchmarking and key size performance analysis
- Implement constant-time comparison to prevent timing attacks
- Support 2048-bit and 4096-bit key sizes

---

## References

- SEED Labs — RSA Public-Key Encryption and Signature Lab
  [https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)

- Java BigInteger Documentation
  [https://docs.oracle.com/en/java/docs/api/java.base/java/math/BigInteger.html](https://docs.oracle.com/en/java/docs/api/java.base/java/math/BigInteger.html)

- RFC 8017 — PKCS #1: RSA Cryptography Specifications
  [https://datatracker.ietf.org/doc/html/rfc8017](https://datatracker.ietf.org/doc/html/rfc8017)

- RSA Original Paper (1978) — Rivest, Shamir, Adleman

- SEED Labs — RSA Public-Key Encryption and Signature Lab (Lab Instructions)
  [https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)

---

## License

This project is for **educational purposes only**.

---

## Author

**Harshwardhan Patil**
MS Software Engineering @ UTA | Ex-Clarivate | Angular · Spring Boot · AWS · PostgreSQL
