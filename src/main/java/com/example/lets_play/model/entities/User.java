package com.example.lets_play.model.entities;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;

@Document(collection = "User")
public class User {

    @Id
    @Column(name = "user_id")
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private String role;

    public User() {

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
