package com.saibende.expense_approval_system.dashboard.service;

import org.springframework.stereotype.Service;

import com.saibende.expense_approval_system.dashboard.dto.DashboardStatsResponse;
import com.saibende.expense_approval_system.expense.entity.ExpenseStatus;
import com.saibende.expense_approval_system.expense.repository.ExpenseRepository;
import com.saibende.expense_approval_system.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    @Override
    public DashboardStatsResponse getDashboardStats() {

        return DashboardStatsResponse.builder()
                .totalExpenses(
                        expenseRepository.count()
                )
                .pendingManagerApproval(
                        expenseRepository.countByStatus(
                                ExpenseStatus.PENDING_MANAGER_APPROVAL
                        )
                )
                .pendingFinanceApproval(
                        expenseRepository.countByStatus(
                                ExpenseStatus.PENDING_FINANCE_APPROVAL
                        )
                )
                .approvedExpenses(
                        expenseRepository.countByStatus(
                                ExpenseStatus.APPROVED
                        )
                )
                .rejectedExpenses(
                        expenseRepository.countByStatus(
                                ExpenseStatus.REJECTED
                        )
                )
                .totalUsers(
                        userRepository.count()
                )
                .build();
    }
}