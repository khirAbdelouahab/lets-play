package com.example.lets_play.service;

import java.util.List;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.lets_play.exception.EmailAlreadyExistException;
import com.example.lets_play.model.entities.User;
import com.example.lets_play.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository uRepository;
    private final PasswordEncoder passwordEncoder;

    public User findUserByName(String name) {
        return this.uRepository.findByName(name)
                        .orElseThrow(() -> new UsernameNotFoundException("username not exists"));
    }

    public User findUserByEmail(String email) {
        return this.uRepository.findByEmail(email)
                .orElseThrow(() -> new EmailAlreadyExistException("email already exits"));
    }

    public List<User> findAllUsers() {
        return this.uRepository.findAll();
    }

    public void updateUserPassword(String username, String newPassword, String currentPassword) {
        User user = this.uRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (this.passwordEncoder.matches(currentPassword, user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(currentPassword));
            this.uRepository.save(user);
        } else {
            throw new BadCredentialsException("Current password is incorrect");
        }
    }

    public void updateUserEmail(String username, String newEmail) {
        User user = this.uRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEmail(newEmail);
        this.uRepository.save(user);
    }

    public User save(User user) {
        return this.uRepository.save(user);
    }
}
