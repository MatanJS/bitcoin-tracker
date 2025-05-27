variable "subscription_id" {
    type        = string
    description = "Subscription ID"
}

variable "tenant_id" {
    type        = string
    description = "Tenant ID"
}

variable "client_id" {
    type        = string
    description = "App ID"
}

variable "client_secret" {
    type        = string
    description = "Password"
}

variable "location" {
    type        = string
    description = "Location"
    default     = "East US"
}
