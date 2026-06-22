package com.saibende.expense_approval_system.expense.service;

import java.util.List;

import com.saibende.expense_approval_system.expense.dto.CreateExpenseRequest;
import com.saibende.expense_approval_system.expense.dto.ExpenseResponse;
import com.saibende.expense_approval_system.expense.entity.ExpenseStatus;
import org.springframework.data.domain.Page;

public interface ExpenseService {
        ExpenseResponse createExpense(
                        CreateExpenseRequest request,
                        String email);

        List<ExpenseResponse> getMyExpenses(
                        String email);

        List<ExpenseResponse> getPendingExpenses();

        ExpenseResponse approveExpense(
                        Long expenseId);

        ExpenseResponse rejectExpense(
                        Long expenseId);

        ExpenseResponse financeApproveExpense(
                        Long expenseId);

        List<ExpenseResponse> getPendingFinanceExpenses();

        Page<ExpenseResponse> getAllExpenses(
                        int page,
                        int size,
                        ExpenseStatus status);

        Page<ExpenseResponse> searchExpenses(
                        String keyword,
                        int page,
                        int size);

        

}
