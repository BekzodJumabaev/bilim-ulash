package com.example.mapper;

import com.example.dto.transactionDto.TransactionCreateDto;
import com.example.dto.transactionDto.TransactionResponseDto;
import com.example.model.Transaction;
import com.example.model.Users;
import jakarta.persistence.MappedSuperclass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {


    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "amount", source = "dto.amount")
    @Mapping(target = "teacher", source = "teacher")
    @Mapping(target = "student", source = "student")
    Transaction toEntity(Users teacher, Users student, TransactionCreateDto dto);

    @Mapping(target = "teacherName", source = "entity.fullName")
    @Mapping(target = "studentName", source = "entity.fullName")
    @Mapping(target = "status", constant = "SUCCESS")
    TransactionResponseDto toDto(Transaction entity);

    List<TransactionResponseDto> toDto(List<Transaction> entityList);
}
