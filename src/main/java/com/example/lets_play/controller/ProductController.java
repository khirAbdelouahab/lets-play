package com.example.lets_play.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lets_play.model.dto.ProductRequestDto;
import com.example.lets_play.model.dto.ProductResponseDto;
import com.example.lets_play.model.entities.Product;
import com.example.lets_play.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductRepository pRepository;
    @PostMapping
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails user, @RequestBody ProductRequestDto newProduct) {
        Product product = Product.builder()
                        .description(newProduct.getDescription())
                        .name(newProduct.getName())
                        .price(newProduct.getPrice())
                        .ownerName(user.getUsername())
                        .build();
        this.pRepository.save(product);
        return ResponseEntity.ok(ProductResponseDto.builder()
                                .success(true)
                                .message("product created succesfuly")
                                .product(newProduct)
                                .build());
    }
    @GetMapping
    public ResponseEntity<?> getProducts(@AuthenticationPrincipal UserDetails user) {
        
        return ResponseEntity.ok("hello " + user.getUsername() + " all products here");
    }

}
