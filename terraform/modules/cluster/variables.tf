variable "client_id" {
    type        = string
    description = "App ID"
    default     = "225b1dd6-8cf4-423e-9d77-35e6636d0670"
}

variable "client_secret" {
    type        = string
    description = "Password"
    default     = "_lI8Q~_NtCRbV5Kf2jqdD~wmU0HsvcbXf.XeecoX"
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