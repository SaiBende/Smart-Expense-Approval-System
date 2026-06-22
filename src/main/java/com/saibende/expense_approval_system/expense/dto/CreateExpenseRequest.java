package com.saibende.expense_approval_system.expense.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateExpenseRequest {
    
    @NotBlank
    private String title;

    private String description;

    @NotNull
    @DecimalMin("1.00")
    private BigDecimal amount;

}
