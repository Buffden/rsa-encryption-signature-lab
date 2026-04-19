# Running — Production-Grade RSA

This document covers how to clone, build, and run the secure-rsa RSA tasks on the GCP VM.

---

## Before You Begin: Start the GCP Instance

1. Go to [GCP Console](https://console.cloud.google.com/) → **Compute Engine** → **VM Instances**
2. Find `rsa-lab-ubuntu-java` and click **Start** if it is not already running
3. Once the status shows a green checkmark, click **SSH** to open the browser-based terminal

---

## Step 1: Clone the Repository

```bash
git clone https://github.com/buffden/rsa-encryption-signature-lab.git
cd rsa-encryption-signature-lab
```

---

## Step 2: Compile

```bash
mvn compile
```

---

## Step 3: Create the Outputs Directory

```bash
mkdir -p outputs/secure-rsa
```

---

## Step 4: Run Individual Tasks

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification"
mvn exec:java -Dexec.mainClass="com.rsa.secure.X509Verification"
```

---

## Step 5: Save Outputs to Text Files

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration" > outputs/secure-rsa/key-generation.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption" > outputs/secure-rsa/encryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption" > outputs/secure-rsa/decryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing" > outputs/secure-rsa/signing.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification" > outputs/secure-rsa/verification.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.X509Verification" > outputs/secure-rsa/x509-verification.txt
```

To suppress Maven build logs and get only program output:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration" -q > outputs/secure-rsa/key-generation.txt
```

To view an output file:

```bash
cat outputs/secure-rsa/key-generation.txt
```
