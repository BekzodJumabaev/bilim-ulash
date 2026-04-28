package com.example.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(MyProjectException.class)
    public Object handleException(MyProjectException ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        if (request.getRequestURI().startsWith("/view/")) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getErrorType().getMessage());
            return "redirect: " + request.getHeader("Referer");
        }

        return new ResponseEntity<>(
                new ErrorMessage(ex.getErrorType().getMessage(), ex.getErrorType().getCode()),
                HttpStatus.valueOf(ex.getErrorType().getCode())
        );
    }
}

