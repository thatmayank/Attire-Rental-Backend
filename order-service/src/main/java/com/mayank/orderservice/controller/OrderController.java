package com.mayank.orderservice.controller;

import com.mayank.orderservice.dto.OrderRequest;
import com.mayank.orderservice.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    /**
     * Dependency Injection using Constructor injection
     */
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody @Valid OrderRequest orderRequest) {
        orderService.create(orderRequest);
        return "Order Placed Successfully";
    }
}
