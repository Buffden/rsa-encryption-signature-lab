# Running the Project

This document covers the one-time setup steps to get the project running on the GCP VM.

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

Each task has its own run command. See the individual task docs in this folder.
