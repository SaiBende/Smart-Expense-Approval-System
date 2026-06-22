package com.saibende.expense_approval_system.expense.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.saibende.expense_approval_system.expense.entity.ExpenseStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpenseResponse {
    private Long id;

    private String title;

    private String description;

    private BigDecimal amount;

    private ExpenseStatus status;

    private LocalDateTime submittedAt;

    private String employeeName;

    private String managerName;

    private String financeName;

    private LocalDateTime managerApprovedAt;

    private LocalDateTime financeApprovedAt;

    private LocalDateTime rejectedAt;
}
