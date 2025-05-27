# ☁️ Azure + Kubernetes + Docker + Terraform Setup Guide

---

## 🔧 Azure Setup

### 1. Install Azure CLI

Download and install the Azure CLI from the [official documentation](https://learn.microsoft.com/en-us/cli/azure/install-azure-cli).

### 2. Login to Azure

```bash
az login
```

> This will redirect you to the Azure portal for authentication.

### 3. Create a Service Principal

```bash
az ad sp create-for-rbac --role="Contributor" --scopes="/subscriptions/<SUBSCRIPTION_ID>"
```

### 4. Assign Contributor Role to the Service Principal

This allows Terraform to manage infrastructure on your subscription:

```bash
az role assignment create --assignee "<APP_ID>" --scope "/subscriptions/<SUBSCRIPTION_ID>" --role Contributor
```

---

## 🌍 Terraform Setup

### 1. Initialize Terraform

After defining your provider in `main.tf`, run:

```bash
terraform init
```

### 2. Plan and Apply the Infrastructure

```bash
terraform plan
terraform apply
```

### 3. Connect to Your AKS Cluster

```bash
az login
az account set --subscription "<your-subscription-name>"
az aks get-credentials --resource-group <your-resource-group> --name <your-cluster-name>
```

---

## 🌐 Ingress Controller Setup

### 1. Deploy NGINX Ingress Controller

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.3.0/deploy/static/provider/cloud/deploy.yaml
```

### 2. Verify the Ingress Deployment

```bash
kubectl get pods --namespace=ingress-nginx
kubectl get service --namespace=ingress-nginx
```

> ✅ You can also use tools like **Helm** for more flexible deployments.

---

## 🐳 Docker & Azure Container Registry (ACR)

### 1. Build Your Docker Image

```bash
docker build -t <image-name> .
```

### 2. Run the Docker Image Locally to test it

```bash
docker run -p 80:80 <image-id>
```

### 3. Push Image to ACR

```bash
az login
az acr login --name <registry-name>
```

#### Tag and Push `service-a`

```bash
docker tag service-a:1.0.0 mydefaultcontainerregistry1.azurecr.io/samples/service-a:latest
docker push mydefaultcontainerregistry1.azurecr.io/samples/service-a:latest
```

> 📌 You can use versioning instead of latest

> 📌 `service-b` uses the `httpd:alpine` image in its `deployment.yaml`.

### 4. Create ACR Pull Secret

This secret allows your pods to pull images from ACR:

```bash
kubectl create secret docker-registry acr-secret   --docker-server=<your-acr-server>   --docker-username=<username>   --docker-password=<password>   --docker-email=unused
```

---

## ☸️ Kubernetes (K8s) Deployment

### 🚀 Deploy `service-a`

```bash
kubectl apply -f k8s/service-a/deployment.yaml
kubectl apply -f k8s/service-a/service.yaml
kubectl apply -f k8s/service-a/ingress.yaml
kubectl apply -f k8s/service-a/network-policy.yaml
```

### 🚀 Deploy `service-b`

```bash
kubectl apply -f k8s/service-b/deployment.yaml
kubectl apply -f k8s/service-b/service.yaml
kubectl apply -f k8s/service-b/ingress.yaml
```

---

## 🌍 Access the Application

### 1. Get External IP

Run this command to retrieve the external IP of the Ingress Controller (Load Balancer):

```bash
kubectl get svc -n ingress-nginx
```

### 2. Update Your `/etc/hosts` File

Map the external IP to your domain:

```text
<EXTERNAL-IP>  bitcoin.tracker.com
```

### 3. Access Service-A Endpoints

- **Health Check**  
  `http://bitcoin.tracker.com/health`

- **Bitcoin Tracker Execution**  
  `http://bitcoin.tracker.com/get-btc`

---
