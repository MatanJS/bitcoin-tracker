variable "client_id" {
    type        = string
    description = "App ID"
}

variable "client_secret" {
    type        = string
    description = "Password"
}

variable "rg_name" {
    type        = string
    description = "Resource Group name"
    default     = "default-rg"
}

variable "location" {
    type        = string
    description = "Location"
    default     = "West EU"
}

variable "aks_cluster_name" {
    type        = string
    description = "kubernetes Cluster name"
    default     = "default-aks-cluster"
}

variable "dns_prefix" {
    type        = string
    description = "DNS Prefix"
    default     = "aks"
}