package com.mayank.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//        return args -> {
//            Inventory inventory1 = Inventory.builder()
//                    .skuCode("test_1")
//                    .quantity(100)
//                    .build();
//
//            Inventory inventory2 = Inventory.builder()
//                    .skuCode("test_1_1")
//                    .quantity(100)
//                    .build();
//
//            inventoryRepository.save(inventory1);
//            inventoryRepository.save(inventory2);
//        };
//    }

}
