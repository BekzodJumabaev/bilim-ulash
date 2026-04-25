package com.example.dto.transactionDto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionCreateDto {
    private Long teacherId;
    private Integer amount;
}
