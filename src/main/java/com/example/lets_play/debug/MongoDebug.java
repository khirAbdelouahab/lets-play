package com.example.lets_play.debug;

import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.MongoTemplate;

@Component
public class MongoDebug {

    public MongoDebug(MongoTemplate mongoTemplate) {
        System.out.println("🚨 Spring MongoTemplate is connected to DB = " + mongoTemplate.getDb().getName());
    }
}

