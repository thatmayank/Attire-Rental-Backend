package com.mayank.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderLineItemRequest {

    @NotBlank(message = "SKU code cannot be blank")
    private String skuCode;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive value")
    private BigDecimal price;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be a positive value")
    private Integer quantity;
}
