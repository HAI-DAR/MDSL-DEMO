package com.mdsl.institution.dto.auth;

import com.mdsl.institution.domain.user.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest
{
    @NotNull
    @NotBlank(message = "Field 'firstname' cannot be empty")
    private String firstname;
    
    @NotNull
    @NotBlank(message = "Field 'lastname' cannot be empty")
    private String lastname;
    
    @NotNull
    @NotBlank(message = "Field 'email' cannot be empty")
    @Email(message = "Email should be valid")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    
    @NotNull
    @NotBlank(message = "Field 'password' cannot be empty")
    private String password;
    
    @NotNull
    private UserRole role;
}
