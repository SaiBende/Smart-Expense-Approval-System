package com.saibende.expense_approval_system.dashboard.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardStatsResponse {

    private long totalExpenses;

    private long pendingManagerApproval;

    private long pendingFinanceApproval;

    private long approvedExpenses;

    private long rejectedExpenses;

    private long totalUsers;
}