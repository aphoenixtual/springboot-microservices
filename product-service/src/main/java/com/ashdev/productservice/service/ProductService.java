package com.ashdev.productservice.service;

import com.ashdev.productservice.dto.ProductRequest;
import com.ashdev.productservice.dto.ProductResponse;
import com.ashdev.productservice.model.Product;
import com.ashdev.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        //We've used Slf4j so we can use a placeholder {} instead of concatination
        log.info("Product {} is saved", product.getId());
        // log.info("Product" + product.getId() + "is saved"); This is we normally do
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

       return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return  ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

}
