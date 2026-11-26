# Oracle Autonomous Database

- a cloud-based database service built on Oracle AI Database that automates provisioning, tuning,
  scaling, patching, backup, and security while integrating AI, vector search, and multi-model data
  capabilities directly inside the database.
- Why?
    - Free Tier is available; more important to me since I don't want to spend lots of money for
      cloud service
    - provides automatic backup; this enables me to recover from loss of data

# Oracle Cloud Infrastructure (OCI) Vault

- Centrally manages encryption keys (HSM-backed) and application secrets (passwords, API keys,
  tokens).
- Why?
    - I need TNS ADMIN file to access to Oracle Autonomous Database. However, It is not secure if i
      store the file in the local. I need cloud server to manage the secret files in a secure
      manner.
    - OCI Vault is perfect because 1) it is available for free tier 2) perfectly compatible with
      serverless Oracle Autonomous Database 

