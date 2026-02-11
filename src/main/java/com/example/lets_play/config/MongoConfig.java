package com.example.lets_play.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Primary
public class MongoConfig {
    
    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(
            MongoClients.create("mongodb://localhost:27018"), 
            "letsplaydb"
        );
    }

    public void MongoDebug(MongoTemplate mongoTemplate) {
        System.out.println("🚨 Spring MongoTemplate is connected to DB = " + mongoTemplate.getDb().getName());
    }
}
