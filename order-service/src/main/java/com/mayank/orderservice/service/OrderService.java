package com.mayank.orderservice.service;

import com.mayank.orderservice.dto.OrderRequest;

public interface OrderService {

    void create(OrderRequest orderRequest);
}
