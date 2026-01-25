package com.example.lets_play.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lets_play.model.entities.User;
import com.example.lets_play.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserService uService;

    public UserController(UserService uService) {
        this.uService = uService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<User> getUser(@PathVariable("name") String name) {
        return ResponseEntity.ok(this.uService.findUserByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(this.uService.findAllUsers());
    }

    @PostMapping("/new")
    public ResponseEntity<User> create(@RequestBody User user) {
        User createdUser = this.uService.save(user);
        return ResponseEntity.ok(createdUser);
    }
}