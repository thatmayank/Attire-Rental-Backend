package com.mayank.inventoryservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "t_inventory")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private Long id;
    private String skuCode;
    private Integer quantity;
}
