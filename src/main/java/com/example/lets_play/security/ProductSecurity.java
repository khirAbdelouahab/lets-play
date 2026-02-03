package com.example.lets_play.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.lets_play.model.entities.Product;
import com.example.lets_play.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class ProductSecurity {

    private final ProductService productService;

    public boolean isOwner(String productId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("authentication null");
            return false;
        }
        System.out.println("product id = " + productId);

        System.out.println("AUTH NAME = " + authentication.getName());
        Product product = this.productService.find(productId);
        if (product == null) {
            System.out.println("PRODUCT Not Found");
            return false;
        }
        System.out.println("PRODUCT OWNER = " + product.getOwnerName());
        String username = authentication.getName();
        return product.getOwnerName().equals(username);
    }
}
