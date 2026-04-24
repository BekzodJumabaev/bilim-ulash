package com.example.service;

import com.example.dto.transactionDto.TransactionCreateDto;
import com.example.dto.transactionDto.TransactionResponseDto;
import com.example.exceptions.ErrorType;
import com.example.exceptions.MyProjectException;
import com.example.mapper.TransactionMapper;
import com.example.model.Transaction;
import com.example.model.Users;
import com.example.repository.TransactionRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final TransactionMapper mapper;


    @Transactional(rollbackFor = Exception.class)
    public TransactionResponseDto transferTime(TransactionCreateDto dto) {

        if (dto.getStudentId().equals(dto.getTeacherId())){
            throw new MyProjectException(ErrorType.SELF_TRANSFER);
        }

        Users teacher = userRepository.findById(dto.getTeacherId()).orElseThrow(
                () -> new MyProjectException(ErrorType.USER_NOT_FOUND)
        );

        Users student = userRepository.findById(dto.getStudentId()).orElseThrow(
                () -> new MyProjectException(ErrorType.USER_NOT_FOUND)
        );
        if (student.getTimeBalans() < dto.getAmount()) {
            throw new MyProjectException(ErrorType.BALANCE_NOT_ENOUGH);
        }

            student.setTimeBalans(student.getTimeBalans()-dto.getAmount());
            teacher.setTimeBalans(teacher.getTimeBalans()+dto.getAmount());

            Transaction entity = transactionRepository.save(mapper.toEntity(teacher, student, dto));

            return mapper.toDto(entity);

        }


    public Page<TransactionResponseDto> getHistory(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());

        Page<Transaction> transactions;
        if (keyword!=null && !keyword.isEmpty()){
             transactions=transactionRepository.searchAllHistory(keyword, pageable);
        }else {
             transactions= transactionRepository.findAll(pageable);
        }
        return transactions.map(mapper::toDto);
    }
}
