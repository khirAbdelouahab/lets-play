package com.example.lets_play.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lets_play.model.entities.User;
import com.mongodb.client.MongoDatabase;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MongoDebugController {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @GetMapping("/debug/mongo-connection")
    public Map<String, Object> getMongoConnection() {
        Map<String, Object> info = new HashMap<>();
        
        try {
            MongoDatabase db = mongoTemplate.getDb();
            info.put("connected", true);
            info.put("databaseName", db.getName());
            info.put("collectionNames", mongoTemplate.getCollectionNames());
            
            // Try to count User documents
            if (mongoTemplate.collectionExists("User")) {
                long count = mongoTemplate.count(new Query(), "User");
                info.put("userCount", count);
                
                // Get actual documents
                List<User> users = mongoTemplate.findAll(User.class);
                info.put("users", users);
            }
            
        } catch (Exception e) {
            info.put("connected", false);
            info.put("error", e.getMessage());
        }
        
        return info;
    }

    @GetMapping("/csrf")
    public CsrfToken getCsrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

}