package com.mayank.orderservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequest {

    @Valid
    @NotEmpty(message = "Order line items list cannot be empty")
    private List<OrderLineItemRequest> orderLineItemDtoList;
}
