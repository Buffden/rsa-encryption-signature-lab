# RSA Encryption

## Objective

Encrypt a plaintext message using the RSA public key `(e, n)`.

---

## Given Values

| Parameter | Value |
| --- | --- |
| n | DCBFFE3E51F62E09CE7032E2677A78946A849DC4CDDE3A4D0CB81629242FB1A5 |
| e | 010001 (decimal: 65537) |
| Message (M) | A top secret! |

> The private key `d` is also provided to help verify the encryption result:
> d = 74D806F9F3A62BAE331FFE3F0A68AFE35B3D2E4794148AACBC26AA381CD7D30D

---

## What Needs to Be Done

- Convert the plaintext message `"A top secret!"` to its hex representation
- Encrypt using the public key: `c = m^e mod n`
- The hex representation of `"A top secret!"` is:

```text
4120746f702073656372657421
```

---

## Run

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Encryption"
```

To save output to a file:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.Encryption" > outputs/encryption.txt
```

---

## Expected Output

```text
n = DCBFFE3E51F62E09CE7032E2677A78946A849DC4CDDE3A4D0CB81629242FB1A5
e = 10001
m = 4120746F702073656372657421
c = 6FB078DA550B2650832661E14F4F8D2CFAEF475A0DF3A75CACDC5DE5CFC5FADC
```

---

## Observations

- The ciphertext `c` looks nothing like the original message `m` — this is expected
- The same message encrypted with the same key will always produce the same ciphertext (textbook RSA is deterministic — a known weakness)
- The ciphertext can only be recovered by someone who holds the private key `d`

---

## Reference

SEED Labs — RSA Public-Key Encryption and Signature Lab
[https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/](https://seedsecuritylabs.org/Labs_20.04/Crypto/Crypto_RSA/)
