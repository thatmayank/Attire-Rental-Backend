package com.mayank.productservice.service.impl;

import com.mayank.productservice.dto.ProductRequest;
import com.mayank.productservice.dto.ProductResponse;
import com.mayank.productservice.exception.ProductNotFoundException;
import com.mayank.productservice.model.Product;
import com.mayank.productservice.repository.ProductRepository;
import com.mayank.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.mayank.productservice.config.MessageStrings.Product_Not_Found;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    /**
     * Dependency Injection using Constructor injection
     */
    private final ProductRepository productRepository;

    @Override
    public void create(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    @Override
    public ProductResponse read(String id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new ProductNotFoundException(Product_Not_Found);
        Product product = optionalProduct.get();
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public List<ProductResponse> readAll(Sort.Direction direction) {
        List<Product> products = productRepository.findAll(Sort.by(direction, "id"));
        return products.stream().map(this::mapToProductResponse).toList();
    }

    @Override
    public void update(String id, ProductRequest productRequest) throws ProductNotFoundException {
        /*
         * Guard statement to check if product is present in DB or not
         */
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new ProductNotFoundException(Product_Not_Found);

        Product product = Product.builder()
                .id(id)
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
    }

    @Override
    public void delete(String id) throws ProductNotFoundException {
        /*
         * Guard statement to check if product is present in DB or not
         */
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) throw new ProductNotFoundException(Product_Not_Found);

        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
