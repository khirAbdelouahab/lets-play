package com.example.lets_play.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.example.lets_play.model.entities.User;
import com.example.lets_play.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AdminAccount implements CommandLineRunner {

    private final UserService uService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User admin = this.uService.findUserByName("admin");
        if (admin == null) {
            admin = new User();
            admin.setName("admin");
            admin.setEmail("admin@g.c");
            admin.setPassword(this.passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            this.uService.save(admin);
            System.out.println("Admin account created successfully!");
        }
    }
}
