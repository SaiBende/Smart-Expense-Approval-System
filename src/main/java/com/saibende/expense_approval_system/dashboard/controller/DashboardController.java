package com.saibende.expense_approval_system.dashboard.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saibende.expense_approval_system.common.response.ApiResponse;
import com.saibende.expense_approval_system.dashboard.dto.DashboardStatsResponse;
import com.saibende.expense_approval_system.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStatsResponse>>
    getDashboardStats() {

        DashboardStatsResponse stats =
                dashboardService.getDashboardStats();

        return ResponseEntity.ok(
                ApiResponse.<DashboardStatsResponse>builder()
                        .success(true)
                        .message("Dashboard statistics fetched successfully")
                        .data(stats)
                        .build()
        );
    }
}