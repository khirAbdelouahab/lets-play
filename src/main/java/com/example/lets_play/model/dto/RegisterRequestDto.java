package com.example.lets_play.model.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class RegisterRequestDto {
    @NotBlank(message = "name is required")
    @Size(min = 6, max = 20, message = "name should be at least 6 charachters and 20 at most")
    private String name;
    @NotBlank(message = "Email is required")
    @Size(min = 15, max = 70, message = "email should be at least 15 charachters and 70 at most")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "password is required")
    @Size(min = 6, max = 20, message = "password should be at least 6 charachters and 20 at most")
    private String password;
}
