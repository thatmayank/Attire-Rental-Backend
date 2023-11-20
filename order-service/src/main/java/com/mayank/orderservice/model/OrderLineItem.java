package com.mayank.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
