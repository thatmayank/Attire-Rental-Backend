package com.mayank.orderservice.service.impl;

import com.mayank.orderservice.dto.InventoryResponse;
import com.mayank.orderservice.dto.OrderLineItemRequest;
import com.mayank.orderservice.dto.OrderRequest;
import com.mayank.orderservice.model.Order;
import com.mayank.orderservice.model.OrderLineItem;
import com.mayank.orderservice.repository.OrderRepository;
import com.mayank.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public void create(OrderRequest orderRequest) {

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderRequest.getOrderLineItemDtoList()
                        .stream()
                        .map(this::mapToOrderLineItem)
                        .toList())
                .build();

        /*
         * Call Inventory Service, and place order if product is in stock
         */

        List<String> skuCodes = orderRequest.getOrderLineItemDtoList()
                .stream()
                .map(OrderLineItemRequest::getSkuCode)
                .toList();

        List<InventoryResponse> inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/api/v1/inventory/{sku-codes}", uriBuilder -> uriBuilder.build(skuCodes))
                .retrieve()
                .bodyToFlux(InventoryResponse.class)
                .collectList()
                .block();

        /*
         * Write logic to check map inventory response with validation before placing order
         */

        if (inventoryResponses != null && inventoryResponses.stream().allMatch(InventoryResponse::isInStock)) {
            orderRepository.save(order);
            log.info("Order {} is saved", order.getId());
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemRequest orderLineItemRequest) {
        return OrderLineItem.builder()
                .skuCode(orderLineItemRequest.getSkuCode())
                .quantity(orderLineItemRequest.getQuantity())
                .price(orderLineItemRequest.getPrice())
                .build();
    }
}
