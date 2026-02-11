package com.example.lets_play.model.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
public class ProductUpdateDto {
    private String id;
    @Size(min = 6, max = 20, message = "name should be at least 6 charachters and 20 at most")
    private String name;
    @Size(min = 10, max = 200, message = "description should be at least 6 charachters and 20 at most")
    private String description;
    @Positive(message = "Price must be greater than 0")
    private Double price;
}
