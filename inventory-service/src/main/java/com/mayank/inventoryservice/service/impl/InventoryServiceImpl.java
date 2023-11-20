package com.mayank.inventoryservice.service.impl;

import com.mayank.inventoryservice.dto.InventoryResponse;
import com.mayank.inventoryservice.repository.InventoryRepository;
import com.mayank.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()).toList();
    }
}
