package com.saibende.expense_approval_system.user.dto;

import lombok.Builder;
import lombok.Data;
import com.saibende.expense_approval_system.user.entity.Role;

@Data
@Builder
public class UserResponse {
    private Long id;

    private String fullName;

    private String username;

    private String email;

    private Role role;
}
