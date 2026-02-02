package com.example.lets_play.service;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.lets_play.model.entities.User;
import com.example.lets_play.repository.UserRepository;
import com.example.lets_play.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository uRepository;

    public CustomUserDetailsService(UserRepository uRepository) {
        this.uRepository = uRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
        System.out.println("🔥 CustomUserDetailsService called");  
        User user = this.uRepository.findByName(username)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new UserPrincipal(user);
    }
}
