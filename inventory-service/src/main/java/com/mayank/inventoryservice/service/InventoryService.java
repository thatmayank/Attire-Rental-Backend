package com.mayank.inventoryservice.service;

import com.mayank.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCodes);
}
