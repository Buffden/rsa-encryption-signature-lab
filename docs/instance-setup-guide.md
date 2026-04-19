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
