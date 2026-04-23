package com.example.exceptions;

import lombok.Getter;

@Getter
public class MyProjectException extends RuntimeException {

    private final ErrorType errorType;

    public MyProjectException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }

}
