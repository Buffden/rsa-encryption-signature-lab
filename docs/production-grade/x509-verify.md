# X.509 Certificate Verification — Secure Implementation

## Objective

Verify an X.509 certificate the way a real TLS client does — not just the RSA signature, but the full set of checks that browsers and production systems perform before trusting a certificate.

---

## What Changes vs Textbook

| Property | Textbook | Production |
| --- | --- | --- |
| RSA signature verification | Manual `sig^e mod n` | Same — already correct in Task 6 |
| Certificate chain | Checks server cert against one CA only | Validates full chain up to a trusted root |
| Expiry check | Not checked | `cert.checkValidity()` — rejects expired certs |
| Hostname verification | Not checked | CN/SAN must match the target hostname |
| Revocation | Not checked | OCSP or CRL check to confirm cert is not revoked |

---

## Planned Approach

- Retain the manual RSA signature verification from Task 6 — it is already correct
- Add `cert.checkValidity()` on both the server certificate and the CA certificate
- Add hostname verification using `HttpsURLConnection.getDefaultHostnameVerifier()`
- Walk the full certificate chain — not just `chain[0]` and `chain[1]` — and verify each issuer-subject link
- Add OCSP check using the AIA (Authority Information Access) extension from the server certificate

---

## Why Each Check Matters

**Expiry** — a certificate has a validity window. Accepting an expired certificate means accepting one that may have been compromised after its intended lifetime.

**Hostname** — without hostname verification, a valid certificate issued for `evil.com` could be used to impersonate `amazon.com`. This is the basis of man-in-the-middle attacks.

**Chain validation** — trusting only the immediate CA is not enough. The CA itself must be trusted — either as a known root or as an intermediate whose root is trusted.

**Revocation** — a certificate can be revoked before it expires if the private key is compromised. Without revocation checking, a stolen key remains usable until the cert naturally expires.

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.SecureX509Verification"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.SecureX509Verification" > outputs/secure-x509-verification.txt
```

---

## Expected Output

```text
Target: https://www.amazon.com

Chain length: 3

=== Expiry Check ===
Cert [0] CN=www.amazon.com : VALID
Cert [1] CN=Amazon RSA 2048 M04,O=Amazon,C=US : VALID
Cert [2] CN=Amazon Root CA 1,O=Amazon,C=US : VALID

=== Hostname Verification ===
Hostname 'www.amazon.com' matches cert: YES

=== Chain Issuer-Subject Validation ===
Cert [0] issuer == Cert [1] subject: YES
Cert [1] issuer == Cert [2] subject: YES

=== RSA Signature Verification ===
Server : CN=www.amazon.com
CA     : CN=Amazon RSA 2048 M04, O=Amazon, C=US
RSA signature verification : VALID
Computed TBS hash : 2086440C0EAA3387CEA8264D8FB5D542...

=== Revocation ===
OCSP URL found : http://ocsp.r2m04.amazontrust.com

=== Overall Result ===
VALID
```

> Certificate details and hashes will differ over time as Amazon rotates its certificates.

---

## Observations

- Chain length is 3 — server cert, intermediate CA, root CA — the textbook version only used chain[0] and chain[1]
- All three certs pass expiry check — `cert.checkValidity()` confirms none are expired
- Hostname `www.amazon.com` matches the SAN in the server certificate — verified by inspecting the Subject Alternative Names extension
- Both issuer-subject chain links validate — confirming the chain of trust is unbroken
- RSA signature verified using `cert.verify()` — cleaner than the manual `sig^e mod n` approach in Task 6
- OCSP URL extracted from the AIA extension — points to Amazon's certificate status responder

---

## Planned Class

`SecureX509Verification.java`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
