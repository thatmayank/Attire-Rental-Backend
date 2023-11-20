package com.mayank.productservice.controller;

import com.mayank.productservice.dto.ProductRequest;
import com.mayank.productservice.dto.ProductResponse;
import com.mayank.productservice.exception.ProductNotFoundException;
import com.mayank.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {
    /**
     * Dependency Injection using Constructor injection
     */
    private final ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid ProductRequest productRequest) {
        productService.create(productRequest);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse read(@PathVariable("id") String id) throws ProductNotFoundException {
        return productService.read(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> readAll(@RequestParam(value = "direction", defaultValue = "ASC", required = false) String direction) {
        return productService.readAll(Sort.Direction.fromString(direction));
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") String id, @RequestBody @Valid ProductRequest productRequest) throws ProductNotFoundException {
        productService.update(id, productRequest);
    }

    @DeleteMapping(value = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) throws ProductNotFoundException {
        productService.delete(id);
    }
}
