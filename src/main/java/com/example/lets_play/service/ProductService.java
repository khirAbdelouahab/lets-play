package com.example.lets_play.service;

import org.springframework.stereotype.Service;

import com.example.lets_play.model.dto.ProductRequestDto;
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



    public void delete(String id) {
         
    }



    
}
