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
            MongoClients.create("mongodb+srv://abouchik:BERoujkanDA@4948-mongodb@letsplaydb.472e7rx.mongodb.net/?appName=letsplaydb"), 
            "letsplaydb"
        );
    }

    public void MongoDebug(MongoTemplate mongoTemplate) {
        System.out.println("🚨 Spring MongoTemplate is connected to DB = " + mongoTemplate.getDb().getName());
    }
}
