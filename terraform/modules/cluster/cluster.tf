resource "azurerm_resource_group" "default-rg" {
  name     = var.rg_name
  location = var.location
}

resource "azurerm_container_registry" "myDefaultContainerRegistry1" {
  name                = "myDefaultContainerRegistry1"
  resource_group_name = azurerm_resource_group.default-rg.name
  location            = azurerm_resource_group.default-rg.location
  sku                 = "Basic"
  admin_enabled       = true
}

resource "azurerm_kubernetes_cluster" "default-aks-cluster" {
  name                = var.aks_cluster_name
  location            = azurerm_resource_group.default-rg.location
  resource_group_name = azurerm_resource_group.default-rg.name
  dns_prefix          = var.dns_prefix

  default_node_pool {
    name       = "default"
    node_count = 1
    vm_size    = "standard_b2pls_v2"
  }

  role_based_access_control_enabled = true

  service_principal {
    client_id       = var.client_id
    client_secret   = var.client_secret
  }

  network_profile {
    network_plugin = "azure"
    network_policy = "azure"
  }
}