package com.example.lets_play.model.dto;
import com.example.lets_play.model.entities.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {  
    private String id;
    private String name;
    private String email;
    private String role;

    public static UserDto toDto(User entity) {
        return UserDto.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .role(entity.getRole())
                .build();
    }

}
