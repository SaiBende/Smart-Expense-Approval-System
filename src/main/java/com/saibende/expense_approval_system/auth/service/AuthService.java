package com.saibende.expense_approval_system.auth.service;

import com.saibende.expense_approval_system.auth.dto.AuthResponse;
import com.saibende.expense_approval_system.auth.dto.LoginRequest;
import com.saibende.expense_approval_system.auth.dto.RegisterRequest;
import com.saibende.expense_approval_system.user.entity.User;

public interface AuthService {
    User registerUser(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
