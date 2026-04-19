# Running — Textbook RSA

This document covers how to clone, build, and run the textbook RSA tasks on the GCP VM.

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
mvn exec:java -Dexec.mainClass="com.rsa.KeyGeneration"
mvn exec:java -Dexec.mainClass="com.rsa.Encryption"
mvn exec:java -Dexec.mainClass="com.rsa.Decryption"
mvn exec:java -Dexec.mainClass="com.rsa.Signing"
mvn exec:java -Dexec.mainClass="com.rsa.Verification"
mvn exec:java -Dexec.mainClass="com.rsa.X509Verification"
```

---

## Step 5: Save Outputs to Text Files

```bash
mvn exec:java -Dexec.mainClass="com.rsa.KeyGeneration" > outputs/key-generation.txt
mvn exec:java -Dexec.mainClass="com.rsa.Encryption" > outputs/encryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.Decryption" > outputs/decryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.Signing" > outputs/signing.txt
mvn exec:java -Dexec.mainClass="com.rsa.Verification" > outputs/verification.txt
mvn exec:java -Dexec.mainClass="com.rsa.X509Verification" > outputs/x509-verification.txt
```

To suppress Maven build logs and get only program output:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.KeyGeneration" -q > outputs/key-generation.txt
```

To view an output file:

```bash
cat outputs/key-generation.txt
```
