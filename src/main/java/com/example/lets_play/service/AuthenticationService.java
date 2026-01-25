package com.example.lets_play.service;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.lets_play.model.dto.AuthResponse;
import com.example.lets_play.model.dto.ErrorResponse;
import com.example.lets_play.model.dto.LoginRequestDto;
import com.example.lets_play.model.dto.RegisterRequestDto;
import com.example.lets_play.model.entities.User;
import com.example.lets_play.repository.UserRepository;

@Service
public class AuthenticationService {
    private final UserRepository uRepository;

    public AuthenticationService(UserRepository uRepository) {
        this.uRepository = uRepository;
    }

    public ResponseEntity<?> login(LoginRequestDto loginRequestDto) {
        Optional<User> userOptional = this.uRepository.findByName(loginRequestDto.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(new ErrorResponse(false, "UNAUTHORIZED", "credentials", 
                  "Invalid email or password"));
        }
        User user = userOptional.get();
        return ResponseEntity.ok("123445567890987");
    }

    public ResponseEntity<?> register(RegisterRequestDto registerRequestDto) {
        if (this.uRepository.existsByEmail(registerRequestDto.getEmail())) {
            ErrorResponse errorResponse = new ErrorResponse(
                    false,
                    "CONFLICT",
                    "email",
                    "Email already exists");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        if (this.uRepository.existsByName(registerRequestDto.getName())) {
             ErrorResponse errorResponse = new ErrorResponse(
                    false,
                    "CONFLICT",
                    "name",
                    "UserName already taken");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
        AuthResponse authResponse = new AuthResponse(
            true, "successfuly" , null);
        return ResponseEntity.ok(authResponse);
    }
}
