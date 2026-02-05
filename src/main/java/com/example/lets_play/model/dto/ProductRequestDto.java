package com.example.lets_play.model.dto;

import com.example.lets_play.model.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String id;
    private String name;
    private String description;
    private Double price;

    public static ProductRequestDto toDto(Product product) {
        return ProductRequestDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .build();
    }
}
