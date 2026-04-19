# X.509 Certificate Manual Verification

## Objective

Manually verify an X.509 digital certificate using RSA. Extract the issuer's public key `(e, n)` from the CA certificate and verify the server's certificate signature without using any built-in SSL trust infrastructure.

---

## Background

Every HTTPS certificate is signed by a Certificate Authority (CA) using RSA (or ECDSA). The certificate contains:

- A **TBSCertificate** (To-Be-Signed) block — the actual certificate content
- A **signature** — the CA's digital signature over a hash of the TBSCertificate
- The **signature algorithm** — e.g., `SHA256withRSA`

To verify manually:

1. Extract the server's certificate and its issuer's (CA's) certificate
2. Get the CA's public key `(e, n)`
3. Decrypt the server certificate's signature: `m' = signature^e mod n`
4. Independently hash the server certificate's TBSCertificate using SHA-256
5. Compare `m'` with the computed hash — if they match, signature is valid

---

## What Needs to Be Done

- Connect to a live HTTPS server and retrieve the certificate chain
- Extract the server certificate and its issuer CA certificate from the chain
- Read the CA's RSA public key `(e, n)` using `RSAPublicKey`
- Read the server certificate's raw signature bytes
- Perform manual RSA verification: `signature^e mod n`
- Extract the embedded SHA-256 hash from the decrypted block (strip PKCS#1 v1.5 padding and DigestInfo prefix)
- Independently compute SHA-256 over the TBSCertificate bytes
- Compare and report `VALID` or `INVALID`

> Note: Default target is `www.amazon.com` (uses RSA certificates). Run on GCP Compute Engine to ensure outbound HTTPS access.

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
