package com.saibende.expense_approval_system.expense.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.saibende.expense_approval_system.common.exception.BadRequestException;
import com.saibende.expense_approval_system.expense.dto.CreateExpenseRequest;
import com.saibende.expense_approval_system.expense.dto.ExpenseResponse;
import com.saibende.expense_approval_system.expense.entity.Expense;
import com.saibende.expense_approval_system.expense.entity.ExpenseStatus;
import com.saibende.expense_approval_system.expense.repository.ExpenseRepository;
import com.saibende.expense_approval_system.user.entity.User;
import com.saibende.expense_approval_system.user.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {

        private final ExpenseRepository expenseRepository;
        private final UserRepository userRepository;

        @Override
        public ExpenseResponse createExpense(
                        CreateExpenseRequest request,
                        String email) {

                User employee = userRepository
                                .findByEmail(email)
                                .orElseThrow(() -> new BadRequestException("User not found"));

                Expense expense = Expense.builder()
                                .title(request.getTitle())
                                .description(request.getDescription())
                                .amount(request.getAmount())
                                .status(ExpenseStatus.PENDING_MANAGER_APPROVAL)
                                .submittedAt(LocalDateTime.now())
                                .employee(employee)
                                .build();

                Expense savedExpense = expenseRepository.save(expense);

                return mapToResponse(savedExpense);
        }

        @Override
        public List<ExpenseResponse> getMyExpenses(
                        String email) {

                return expenseRepository
                                .findByEmployeeEmail(email)
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        @Override
        public List<ExpenseResponse> getPendingExpenses() {

                return expenseRepository
                                .findByStatus(
                                                ExpenseStatus.PENDING_MANAGER_APPROVAL)
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        @Override
        public ExpenseResponse approveExpense(
                        Long expenseId) {

                Expense expense = expenseRepository
                                .findById(expenseId)
                                .orElseThrow(
                                                () -> new BadRequestException(
                                                                "Expense not found"));

                expense.setStatus(
                                ExpenseStatus.PENDING_FINANCE_APPROVAL);

                expense.setManagerApprovedAt(
                                LocalDateTime.now());

                Expense updated = expenseRepository.save(expense);

                return mapToResponse(updated);
        }

        @Override
        public ExpenseResponse rejectExpense(
                        Long expenseId) {

                Expense expense = expenseRepository
                                .findById(expenseId)
                                .orElseThrow(
                                                () -> new BadRequestException(
                                                                "Expense not found"));

                expense.setStatus(
                                ExpenseStatus.REJECTED);

                expense.setRejectedAt(
                                LocalDateTime.now());

                Expense updated = expenseRepository.save(expense);

                return mapToResponse(updated);
        }

        private ExpenseResponse mapToResponse(
                        Expense expense) {

                return ExpenseResponse.builder()
                                .id(expense.getId())
                                .title(expense.getTitle())
                                .description(expense.getDescription())
                                .amount(expense.getAmount())
                                .status(expense.getStatus())
                                .submittedAt(expense.getSubmittedAt())
                                .build();
        }

        @Override
        public List<ExpenseResponse> getPendingFinanceExpenses() {

                return expenseRepository
                                .findByStatus(
                                                ExpenseStatus.PENDING_FINANCE_APPROVAL)
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        @Override
        public ExpenseResponse financeApproveExpense(
                        Long expenseId) {

                Expense expense = expenseRepository
                                .findById(expenseId)
                                .orElseThrow(
                                                () -> new BadRequestException(
                                                                "Expense not found"));

                expense.setStatus(
                                ExpenseStatus.APPROVED);

                expense.setFinanceApprovedAt(
                                LocalDateTime.now());

                Expense updated = expenseRepository.save(expense);

                return mapToResponse(updated);
        }

        @Override
        public Page<ExpenseResponse> getAllExpenses(
                        int page,
                        int size,
                        ExpenseStatus status) {

                Pageable pageable = PageRequest.of(page, size);

                Page<Expense> expenses;

                if (status != null) {
                        expenses = expenseRepository
                                        .findByStatus(
                                                        status,
                                                        pageable);
                } else {
                        expenses = expenseRepository
                                        .findAll(pageable);
                }

                return expenses.map(
                                this::mapToResponse);
        }

        @Override
        public Page<ExpenseResponse> searchExpenses(
                        String keyword,
                        int page,
                        int size) {

                Pageable pageable = PageRequest.of(page, size);

                return expenseRepository
                                .searchExpenses(
                                                keyword,
                                                pageable)
                                .map(this::mapToResponse);
        }
}