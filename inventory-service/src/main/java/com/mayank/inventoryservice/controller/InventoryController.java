package com.mayank.inventoryservice.controller;

import com.mayank.inventoryservice.dto.InventoryResponse;
import com.mayank.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    /*
     * http://localhost:8082/api/inventory/iphone-13,iphone13-red - As PathVariable
     * http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red - As RequestParam
     */
    @GetMapping("{sku-codes}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@PathVariable("sku-codes") List<String> skuCodes) {
        log.info("Received inventory check request for skuCode: {}", skuCodes);
        return inventoryService.isInStock(skuCodes);
    }
}
