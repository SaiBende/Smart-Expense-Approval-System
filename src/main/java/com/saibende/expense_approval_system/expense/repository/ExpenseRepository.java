package com.saibende.expense_approval_system.expense.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.saibende.expense_approval_system.expense.entity.Expense;
import com.saibende.expense_approval_system.expense.entity.ExpenseStatus;

public interface ExpenseRepository
                extends JpaRepository<Expense, Long> {

        List<Expense> findByEmployeeEmail(String email);

        List<Expense> findByStatus(ExpenseStatus status);

        long countByStatus(ExpenseStatus status);

        Page<Expense> findAll(Pageable pageable);

        Page<Expense> findByStatus(
                        ExpenseStatus status,
                        Pageable pageable);

        Page<Expense> findByTitleContainingIgnoreCase(
                        String keyword,
                        Pageable pageable);

        Page<Expense> findByDescriptionContainingIgnoreCase(
                        String keyword,
                        Pageable pageable);

        @Query("""
                        SELECT e
                        FROM Expense e
                        WHERE LOWER(e.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
                        OR LOWER(e.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
                        """)
        Page<Expense> searchExpenses(
                        @Param("keyword") String keyword,
                        Pageable pageable);
}