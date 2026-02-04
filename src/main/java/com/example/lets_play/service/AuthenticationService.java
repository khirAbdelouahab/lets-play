package com.example.lets_play.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.lets_play.exception.EmailAlreadyExistException;
import com.example.lets_play.exception.UsernameAlreadyExistsException;
import com.example.lets_play.model.dto.AuthResponse;
import com.example.lets_play.model.dto.ErrorResponse;
import com.example.lets_play.model.dto.LoginRequestDto;
import com.example.lets_play.model.dto.RegisterRequestDto;
import com.example.lets_play.model.entities.User;
import com.example.lets_play.repository.UserRepository;
import com.example.lets_play.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository uRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> login(LoginRequestDto loginRequestDto){
        try {
            Authentication authentication = this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDto.getUsername(),
                            loginRequestDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = this.jwtTokenProvider.generateToken(userDetails.getUsername());
            return ResponseEntity.ok(new AuthResponse(true, "Login successful", jwtToken));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(false, "UNAUTHORIZED", "credentials", 
                      "Invalid email or password"));
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(false, "UNAUTHORIZED", "account", 
                      "Account is disabled"));
        } catch (LockedException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(false, "UNAUTHORIZED", "account", 
                      "Account is locked"));
        }
    }

    public ResponseEntity<?> register(RegisterRequestDto registerRequestDto) {
        if (this.uRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new EmailAlreadyExistException("Email already Exists");
        }
        if (this.uRepository.existsByName(registerRequestDto.getName())) {
            throw new UsernameAlreadyExistsException("Username already Exists");
        }
        User user = new User();
        user.setEmail(registerRequestDto.getEmail());
        user.setName(registerRequestDto.getName());
        String hashedPassword = this.passwordEncoder.encode(registerRequestDto.getPassword());
        user.setPassword(hashedPassword);
        user.setRole("USER");
        this.uRepository.save(user);
        AuthResponse authResponse = new AuthResponse(
                true, "successfuly", null);
        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }
}
