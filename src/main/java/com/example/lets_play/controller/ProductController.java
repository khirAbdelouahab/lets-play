package com.example.lets_play.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.lets_play.model.dto.ProductRequestDto;
import com.example.lets_play.model.dto.ProductResponseDto;
import com.example.lets_play.model.entities.Product;
import com.example.lets_play.service.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService pService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> create(@AuthenticationPrincipal UserDetails user,
            @RequestBody ProductRequestDto newProduct) {
        this.pService.create(newProduct, user.getUsername());
        return ResponseEntity.ok(ProductResponseDto.builder()
                .success(true)
                .message("product created succesfuly")
                .product(newProduct)
                .build());
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        return ResponseEntity.ok(this.pService.geProducts());
    }

    @GetMapping("/ownedBy/{username}")
    public ResponseEntity<?> getProducts(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.pService.geProducts(username));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@AuthenticationPrincipal UserDetails user,
            @PathVariable("id") String productId) {
        Product product = this.pService.find(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated() && @productSecurity.isOwner(#productId, authentication)")
    public ResponseEntity<?> updateProduct(@RequestBody ProductRequestDto productRequestDto,
            @PathVariable("id") String productId) {

            
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated() && (@productSecurity.isOwner(#productId, authentication) || hasAuthority('ADMIN'))")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String productId) {
        Product product = this.pService.find(productId);
        Map<String, String> response = new HashMap<>();
        if (product == null) {
            response.put("success", "false");
            response.put("error", "product not found");
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(response);
        }
        this.pService.delete(productId);
        response.put("success", "true");
        response.put("message", "product deleted successfuly");
        return ResponseEntity.ok(response);
    }
}
