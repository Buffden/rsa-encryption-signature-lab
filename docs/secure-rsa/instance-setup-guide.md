# GCP Compute Engine — Instance Setup Guide

This document covers the full setup of the GCP Compute Engine VM used to run the RSA Encryption and Signature Lab Java project.

---

## Step 1: Create the Instance

Go to **GCP Console → Compute Engine → VM Instances → Create Instance**

### Instance Name

```text
rsa-lab-ubuntu-java
```

---

## Step 2: Machine Configuration

| Field | Value |
| --- | --- |
| Series | E2 |
| Machine Type | e2-small |
| vCPUs | 2 |
| RAM | ~2 GB |

---

## Step 3: Region & Zone

| Field | Value |
| --- | --- |
| Region | us-central1 (Iowa) |
| Zone | us-central1-a |

> Iowa is the cheapest GCP region for Compute Engine.

---

## Step 4: Boot Disk

Click **Change** and configure:

| Field | Value |
| --- | --- |
| Operating System | Ubuntu |
| Version | Ubuntu 25.10 LTS x86/64 |
| Boot Disk Type | Balanced persistent disk |
| Size | 10 GB |

---

## Step 5: Identity & API Access

Leave as default:

- Service account: Compute Engine default service account
- Access scopes: Allow default access

---

## Step 6: Firewall

Leave both **unchecked**:

- Allow HTTP traffic — No
- Allow HTTPS traffic — No

> No inbound traffic is needed. The project only runs CLI programs and connects outbound to HTTPS servers for X.509 certificate verification.

---

## Step 7: Everything Else

Leave all other tabs as default:

- Networking
- Disks
- Security
- Management
- Advanced

Click **Create**.

---

## Step 8: Install Git, Java & Maven on the VM

Once the instance is running, open the SSH-in-browser terminal from the GCP Console and run:

```bash
# Update package list
sudo apt update

# Install Git
sudo apt install git -y

# Verify Git
git --version

# Install Java 17
sudo apt install openjdk-17-jdk -y

# Verify Java
java -version

# Install Maven
sudo apt install maven -y

# Verify Maven
mvn -version
```

---

## Step 9: Clone & Run the Project

```bash
# Clone the repository
git clone https://github.com/buffden/rsa-encryption-signature-lab.git

# Navigate into the project
cd rsa-encryption-signature-lab

# Compile
mvn compile

# Run individual tasks
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing"
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification"
mvn exec:java -Dexec.mainClass="com.rsa.secure.X509Verification"
```

### Save outputs to text files

```bash
# Create the outputs directory
mkdir -p outputs/secure-rsa

# Run each task and redirect output to a file
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration" > outputs/secure-rsa/key-generation.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption" > outputs/secure-rsa/encryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption" > outputs/secure-rsa/decryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing" > outputs/secure-rsa/signing.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification" > outputs/secure-rsa/verification.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.X509Verification" > outputs/secure-rsa/x509-verification.txt
```

To get only the program output without Maven build logs, use the `-q` flag:

```bash
mvn exec:java -Dexec.mainClass="com.rsa.secure.KeyGeneration" -q > outputs/secure-rsa/key-generation.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Encryption" -q > outputs/secure-rsa/encryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Decryption" -q > outputs/secure-rsa/decryption.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Signing" -q > outputs/secure-rsa/signing.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.Verification" -q > outputs/secure-rsa/verification.txt
mvn exec:java -Dexec.mainClass="com.rsa.secure.X509Verification" -q > outputs/secure-rsa/x509-verification.txt
```

To view an output file:

```bash
cat outputs/secure-rsa/key-generation.txt
```

---

## Instance Summary

| Component | Configuration |
| --- | --- |
| Instance Name | rsa-lab-ubuntu-java |
| Operating System | Ubuntu 25.10 LTS |
| Machine Type | e2-small |
| vCPUs | 2 |
| RAM | ~2 GB |
| Architecture | x86/64 |
| Boot Disk | 10 GB Balanced Persistent Disk |
| Region | us-central1 (Iowa) |
| Zone | us-central1-a |
| Access | SSH-in-browser via GCP Console |
| Java Version | OpenJDK 17 |
| Build Tool | Maven |
| Version Control | Git |
