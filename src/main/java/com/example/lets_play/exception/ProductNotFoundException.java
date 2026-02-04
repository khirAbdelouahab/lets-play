package com.example.lets_play.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String id) {
        super("Product with id " + id + " not found");
    }
}
