package com.saibende.expense_approval_system.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.saibende.expense_approval_system.auth.dto.AuthResponse;
import com.saibende.expense_approval_system.auth.dto.LoginRequest;
import com.saibende.expense_approval_system.auth.dto.RegisterRequest;
import com.saibende.expense_approval_system.auth.service.AuthService;
import com.saibende.expense_approval_system.common.response.ApiResponse;
import com.saibende.expense_approval_system.user.dto.UserResponse;
import com.saibende.expense_approval_system.user.entity.User;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

        private final AuthService authService;

        @PostMapping("/register")
        public ResponseEntity<ApiResponse<UserResponse>> register(
                        @Valid @RequestBody RegisterRequest request) {

                User user = authService.registerUser(request);

                UserResponse userResponse = UserResponse.builder()
                                .id(user.getId())
                                .fullName(user.getFullname())
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .role(user.getRole())
                                .build();

                ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
                                .success(true)
                                .message("User registered successfully")
                                .data(userResponse)
                                .build();

                return ResponseEntity
                                .status(HttpStatus.CREATED)
                                .body(response);
        }

        @PostMapping("/login")
        public ResponseEntity<ApiResponse<AuthResponse>> login(
                        @Valid @RequestBody LoginRequest request) {

                AuthResponse authResponse = authService.login(request);

                ApiResponse<AuthResponse> response = ApiResponse.<AuthResponse>builder()
                                .success(true)
                                .message("Login successful")
                                .data(authResponse)
                                .build();

                return ResponseEntity.ok(response);
        }
}