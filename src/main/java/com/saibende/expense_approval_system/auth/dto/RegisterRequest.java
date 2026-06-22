package com.saibende.expense_approval_system.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.saibende.expense_approval_system.user.entity.Role;

@Data
public class RegisterRequest {

    @NotBlank(message="Full name is required")
    private String fullname;

    @NotBlank(message="Username is required")
    private String username;

    @Email(message="Email should be valid")
    @NotBlank(message="Email is required")
    private String email;

    @NotBlank(message="Password is required")
    @Size(min=6, message="Password must be at least 6 characters long")
    private String password;


    private Role role;
    
}
