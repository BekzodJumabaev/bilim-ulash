package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(MyProjectException.class)

    public ResponseEntity<ErrorMessage> handleException(MyProjectException exception){
        ErrorType type = exception.getErrorType();

        ErrorMessage errorMessage = new ErrorMessage(type.getMessage(), type.getCode());

        return ResponseEntity.status(type.getCode()).body(errorMessage);
    }
}
