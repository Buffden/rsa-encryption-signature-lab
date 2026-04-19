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

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.X509Verification"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.X509Verification" > outputs/x509-verification.txt
```

---

## Expected Output

```text
Target: https://www.amazon.com

Server cert subject : CN=www.amazon.com
CA cert subject     : CN=DigiCert Global CA G2, O=DigiCert Inc, C=US

CA n = D3487CBEF305865D5BD52F854E4BE086...
CA e = 10001

Signature length    : 256 bytes
Signature (hex)     : 01A239E79132F6C84F88A47F2511A2A4...

Decrypted block     : 01FFFFFFFFFFFF...003031300D0609608648016503040201050004200C38287164B327B8...

Embedded hash (M')  : 0C38287164B327B8E3DC9A14F1B774BB07D9740D97A34BA7FD93320BEE7F7EA3
Computed hash (M)   : 0C38287164B327B8E3DC9A14F1B774BB07D9740D97A34BA7FD93320BEE7F7EA3

Verification result : VALID
```

> The CA certificate, signature bytes, and hashes will differ on each run as Amazon rotates its certificates periodically.

---

## Observations

- The decrypted block starts with `01 FF FF FF...` — this is the PKCS#1 v1.5 signature padding. The `FF` bytes are stripped to find the `00` separator, after which the DigestInfo and hash follow
- The embedded hash (`M'`) recovered from the signature exactly matches the SHA-256 hash computed independently over the TBSCertificate bytes
- This confirms the certificate was signed by the CA and has not been tampered with
- This is exactly what every browser does silently when you visit an HTTPS website — just automated and in milliseconds

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
