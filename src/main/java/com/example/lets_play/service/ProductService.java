package com.example.lets_play.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.lets_play.exception.ProductNotFoundException;
import com.example.lets_play.model.dto.ProductRequestDto;
import com.example.lets_play.model.dto.ProductResponseDto;
import com.example.lets_play.model.dto.ProductUpdateDto;
import com.example.lets_play.model.entities.Product;
import com.example.lets_play.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository pRepository;

    public ProductRequestDto create(ProductRequestDto newProduct, String OwnerName) {
        Product product = Product.builder()
                .description(newProduct.getDescription())
                .name(newProduct.getName())
                .price(newProduct.getPrice())
                .ownerName(OwnerName)
                .build();
        this.pRepository.save(product);
        return newProduct;
    }

    public ResponseEntity<?> delete(String ProductId) {
        Product product = this.find(ProductId);
        this.pRepository.delete(product);
        Map<String, String> response = new HashMap<>();
        response.put("success", "true");
        response.put("message", "product with id " + ProductId + " is deleted successfuly");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> update(String productId, ProductUpdateDto newProduct) {
        Product product = this.find(productId);
        if (newProduct.getName() != null) {
            product.setName(newProduct.getName());
        }

        if (newProduct.getDescription() != null) {
            product.setDescription(newProduct.getDescription());
        }

        if (newProduct.getPrice() != null) {
            product.setPrice(newProduct.getPrice());
        }

        product = this.pRepository.save(product);
        return ResponseEntity
                .ok(new ProductResponseDto(true, "product updated successfuly", ProductRequestDto.toDto(product)));
    }

    public Product find(String ProductId) {
        return this.pRepository.findById(ProductId)
                .orElseThrow(() -> new ProductNotFoundException(ProductId));
    }

    public List<Product> geProducts() {
        return this.pRepository.findAll();
    }

    public List<Product> geProducts(String ownerName) {
        return this.pRepository.findAllByOwnerName(ownerName);
    }
}
