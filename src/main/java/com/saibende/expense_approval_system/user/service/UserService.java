package com.saibende.expense_approval_system.user.service;

import com.saibende.expense_approval_system.user.entity.User;
import com.saibende.expense_approval_system.auth.dto.RegisterRequest;

public interface UserService {
    User registerUser(RegisterRequest request);
}
