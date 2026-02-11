package com.example.lets_play.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.lets_play.model.dto.UserDto;
import com.example.lets_play.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {
    
    private final UserService uService;

    @GetMapping("/{name}")
    @PostAuthorize("returnObject.name == authentication.name or hasAuthority('ADMIN')")
    public UserDto getUser(@PathVariable("name") String name) {
        return UserDto.toDto(this.uService.findUserByName(name));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(UserDto.toDto(this.uService.findAllUsers()));
    }

}
