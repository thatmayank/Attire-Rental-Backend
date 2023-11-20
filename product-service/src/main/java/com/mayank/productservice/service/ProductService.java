package com.mayank.productservice.service;

import com.mayank.productservice.dto.ProductRequest;
import com.mayank.productservice.dto.ProductResponse;
import com.mayank.productservice.exception.ProductNotFoundException;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {

    void create(ProductRequest productRequest);

    ProductResponse read(String id) throws ProductNotFoundException;

    List<ProductResponse> readAll(Sort.Direction direction);

    void update(String id, ProductRequest productRequest) throws ProductNotFoundException;

    void delete(String id) throws ProductNotFoundException;
}
