# Running — Production-Grade RSA

This document covers how to clone, build, and run the production-grade RSA tasks on the GCP VM.

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
mkdir -p outputs
```

---

## Step 4: Run Individual Tasks

```bash
mvn exec:java -Dexec.mainClass="com.rsa.SecureKeyGeneration"
mvn exec:java -Dexec.mainClass="com.rsa.SecureEncryption"
mvn exec:java -Dexec.mainClass="com.rsa.SecureDecryption"
mvn exec:java -Dexec.mainClass="com.rsa.SecureSigning"
mvn exec:java -Dexec.mainClass="com.rsa.SecureVerification"
mvn exec:java -Dexec.mainClass="com.rsa.SecureX509Verification"
```

---

## Step 5: Save Outputs to Text Files

```bash
mvn exec:java -Dexec.mainClass="com.rsa.SecureKeyGeneration" > outputs/secure-key-generation.txt
mvn exec:java -Dexec.mainClass="com.rsa.SecureEncryption" > outputs/secure-encryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.SecureDecryption" > outputs/secure-decryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.SecureSigning" > outputs/secure-signing.txt
mvn exec:java -Dexec.mainClass="com.rsa.SecureVerification" > outputs/secure-verification.txt
mvn exec:java -Dexec.mainClass="com.rsa.SecureX509Verification" > outputs/secure-x509-verification.txt
```

To suppress Maven build logs and get only program output:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.SecureKeyGeneration" -q > outputs/secure-key-generation.txt
```

To view an output file:

```bash
cat outputs/secure-key-generation.txt
```
