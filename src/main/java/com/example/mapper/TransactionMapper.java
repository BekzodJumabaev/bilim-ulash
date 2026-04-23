package com.example.mapper;

import com.example.dto.transactionDto.TransactionCreateDto;
import com.example.dto.transactionDto.TransactionResponseDto;
import com.example.model.Transaction;
import com.example.model.Users;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TransactionMapper {

    public Transaction toEntity(Users teacher, Users student, TransactionCreateDto dto) {
        return Transaction
                .builder()
                .teacher(teacher)
                .student(student)
                .amount(dto.getAmount())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public TransactionResponseDto toDto(Transaction entity) {
        return TransactionResponseDto
                .builder()
                .id(entity.getId())
                .teacherName(entity.getTeacher().getFullName())
                .studentName(entity.getStudent().getFullName())
                .amount(entity.getAmount())
                .status("SUCCES")
                .createdAt(entity.getCreatedAt())
                .build();

    }

    public List<TransactionResponseDto> toDto(List<Transaction> entity) {
        return entity.stream().map(this::toDto).toList();
    }
}
