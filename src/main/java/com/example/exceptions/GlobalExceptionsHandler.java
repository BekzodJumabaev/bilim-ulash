package com.example.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(MyProjectException.class)
    public ResponseEntity<ErrorMessage> handleRestException(MyProjectException ex) {
        return new ResponseEntity<>(
                new ErrorMessage(ex.getErrorType().getMessage(), ex.getErrorType().getCode()),
                HttpStatus.valueOf(ex.getErrorType().getCode())
        );
    }
}

