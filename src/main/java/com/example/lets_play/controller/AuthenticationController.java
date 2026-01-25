package com.example.lets_play.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lets_play.model.dto.LoginRequestDto;
import com.example.lets_play.model.dto.RegisterRequestDto;
import com.example.lets_play.service.AuthenticationService;

@RestController
@RequestMapping("/auth/")
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto) {
        // check if user exists

        // generate token

        // sends token to the client
        return null;
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto registerRequestDto) {
        // check if user doesn't exists

        // hash password before saveing it to database

        // save user in database
        return null;
    }
}
