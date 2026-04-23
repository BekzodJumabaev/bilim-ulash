package com.example.controller.restController;

import com.example.dto.transactionDto.TransactionCreateDto;
import com.example.dto.transactionDto.TransactionResponseDto;
import com.example.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponseDto> transfer(@RequestBody TransactionCreateDto dto) {
        return ResponseEntity.ok(service.transferTime(dto));
    }
}
