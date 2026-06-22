package com.saibende.expense_approval_system.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saibende.expense_approval_system.auth.dto.AuthResponse;
import com.saibende.expense_approval_system.auth.dto.LoginRequest;
import com.saibende.expense_approval_system.auth.dto.RegisterRequest;
import com.saibende.expense_approval_system.auth.security.JwtService;
import com.saibende.expense_approval_system.user.entity.User;
import com.saibende.expense_approval_system.user.repository.UserRepository;
import com.saibende.expense_approval_system.user.service.UserService;
import com.saibende.expense_approval_system.common.exception.BadRequestException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public User registerUser(RegisterRequest request) {
        return userService.registerUser(request);
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(
                request.getEmail()).orElseThrow(
                        () -> new BadRequestException(
                                "Invalid email or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!passwordMatches) {
            throw new BadRequestException(
                    "Invalid email or password");
        }

        String token = jwtService.generateToken(
                user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }

}
