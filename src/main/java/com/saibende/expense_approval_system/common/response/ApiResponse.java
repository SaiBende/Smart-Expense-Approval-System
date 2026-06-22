package com.saibende.expense_approval_system.common.response;

import lombok.*;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private boolean success;
}
