package com.example.lets_play.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.lets_play.model.entities.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findAllByOwnerName(String username);
}
