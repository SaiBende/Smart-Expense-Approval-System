package com.saibende.expense_approval_system.user.service;

import org.springframework.stereotype.Service;
import com.saibende.expense_approval_system.user.repository.UserRepository;
import com.saibende.expense_approval_system.user.entity.User;
import com.saibende.expense_approval_system.auth.dto.RegisterRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.saibende.expense_approval_system.common.exception.BadRequestException;
import com.saibende.expense_approval_system.user.entity.Role;



import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
public User registerUser(RegisterRequest request) {

    if (userRepository.existsByEmail(request.getEmail())) {
        throw new BadRequestException(
                "Email already exists"
        );
    }

    if (userRepository.existsByUsername(request.getUsername())) {
        throw new BadRequestException(
                "Username already exists"
        );
    }

    User user = User.builder()
            .fullname(request.getFullname())
            .username(request.getUsername())
            .email(request.getEmail())
            .password(
                    passwordEncoder.encode(
                            request.getPassword()
                    )
            )
            .role(
                Role.EMPLOYEE
            )
            .build();

    return userRepository.save(user);
}
}
