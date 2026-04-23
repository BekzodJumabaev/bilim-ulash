package com.example.dto.transactionDto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private Long id;
    private String studentName;
    private String teacherName;
    private Integer amount;
    private String status;
    private LocalDateTime createdAt;
}
