package com.example.lets_play.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.lets_play.model.entities.User;
import com.example.lets_play.repository.UserRepository;
@Service
public class UserService {

    private final UserRepository uRepository;

    public UserService(UserRepository uRepository) {
        this.uRepository = uRepository;
    }

    public User findUserByName(String name) {
        return this.uRepository.findByName(name).orElse(null);
    }

    public User findUserByEmail(String email) {
        return this.uRepository.findByEmail(email).orElse(null);
    }

    public List<User> findAllUsers() {
        return this.uRepository.findAll();
    }

    public User save(User user) {
        return this.uRepository.save(user);
    }

}
