package com.saibende.expense_approval_system.expense.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.saibende.expense_approval_system.common.response.ApiResponse;
import com.saibende.expense_approval_system.expense.dto.CreateExpenseRequest;
import com.saibende.expense_approval_system.expense.dto.ExpenseResponse;
import com.saibende.expense_approval_system.expense.entity.ExpenseStatus;
import com.saibende.expense_approval_system.expense.service.ExpenseService;
import org.springframework.security.access.prepost.PreAuthorize;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

        private final ExpenseService expenseService;

        @PostMapping
        public ResponseEntity<ApiResponse<ExpenseResponse>> createExpense(
                        @Valid @RequestBody CreateExpenseRequest request,
                        Authentication authentication) {

                String email = authentication.getName();

                ExpenseResponse expense = expenseService.createExpense(
                                request,
                                email);

                ApiResponse<ExpenseResponse> response = ApiResponse.<ExpenseResponse>builder()
                                .success(true)
                                .message("Expense submitted successfully")
                                .data(expense)
                                .build();

                return ResponseEntity.ok(response);
        }

        @GetMapping("/my")
        public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getMyExpenses(
                        Authentication authentication) {

                String email = authentication.getName();

                List<ExpenseResponse> expenses = expenseService.getMyExpenses(
                                email);

                return ResponseEntity.ok(
                                ApiResponse.<List<ExpenseResponse>>builder()
                                                .success(true)
                                                .message(
                                                                "Expenses fetched successfully")
                                                .data(expenses)
                                                .build());
        }

        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        @GetMapping("/pending")
        public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getPendingExpenses() {

                return ResponseEntity.ok(
                                ApiResponse.<List<ExpenseResponse>>builder()
                                                .success(true)
                                                .message(
                                                                "Pending expenses fetched")
                                                .data(
                                                                expenseService
                                                                                .getPendingExpenses())
                                                .build());
        }

        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        @PutMapping("/{id}/approve")
        public ResponseEntity<ApiResponse<ExpenseResponse>> approveExpense(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                ApiResponse.<ExpenseResponse>builder()
                                                .success(true)
                                                .message(
                                                                "Expense approved")
                                                .data(
                                                                expenseService
                                                                                .approveExpense(id))
                                                .build());
        }

        @PreAuthorize("hasAnyRole('MANAGER','ADMIN')")
        @PutMapping("/{id}/reject")
        public ResponseEntity<ApiResponse<ExpenseResponse>> rejectExpense(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                ApiResponse.<ExpenseResponse>builder()
                                                .success(true)
                                                .message(
                                                                "Expense rejected")
                                                .data(
                                                                expenseService
                                                                                .rejectExpense(id))
                                                .build());
        }

        @PreAuthorize("hasAnyRole('FINANCE','ADMIN')")
        @GetMapping("/pending-finance")
        public ResponseEntity<ApiResponse<List<ExpenseResponse>>> getPendingFinanceExpenses() {

                return ResponseEntity.ok(
                                ApiResponse.<List<ExpenseResponse>>builder()
                                                .success(true)
                                                .message(
                                                                "Pending finance expenses fetched")
                                                .data(
                                                                expenseService
                                                                                .getPendingFinanceExpenses())
                                                .build());
        }

        @PreAuthorize("hasAnyRole('FINANCE','ADMIN')")
        @PutMapping("/{id}/finance-approve")
        public ResponseEntity<ApiResponse<ExpenseResponse>> financeApproveExpense(
                        @PathVariable Long id) {

                return ResponseEntity.ok(
                                ApiResponse.<ExpenseResponse>builder()
                                                .success(true)
                                                .message(
                                                                "Expense approved by finance")
                                                .data(
                                                                expenseService
                                                                                .financeApproveExpense(id))
                                                .build());
        }

        @PreAuthorize("hasAnyRole('ADMIN')")
        @GetMapping("/all")
        public ResponseEntity<ApiResponse<Page<ExpenseResponse>>> getAllExpenses(

                        @RequestParam(defaultValue = "0") int page,

                        @RequestParam(defaultValue = "10") int size,

                        @RequestParam(required = false) ExpenseStatus status

        ) {

                Page<ExpenseResponse> expenses = expenseService.getAllExpenses(
                                page,
                                size,
                                status);

                return ResponseEntity.ok(
                                ApiResponse.<Page<ExpenseResponse>>builder()
                                                .success(true)
                                                .message("Expenses fetched successfully")
                                                .data(expenses)
                                                .build());
        }

        @PreAuthorize("hasAnyRole('ADMIN','MANAGER','FINANCE')")
        @GetMapping("/search")
        public ResponseEntity<ApiResponse<Page<ExpenseResponse>>> searchExpenses(

                        @RequestParam String keyword,

                        @RequestParam(defaultValue = "0") int page,

                        @RequestParam(defaultValue = "10") int size

        ) {

                Page<ExpenseResponse> expenses = expenseService.searchExpenses(
                                keyword,
                                page,
                                size);

                return ResponseEntity.ok(
                                ApiResponse.<Page<ExpenseResponse>>builder()
                                                .success(true)
                                                .message("Expenses fetched successfully")
                                                .data(expenses)
                                                .build());
        }
}