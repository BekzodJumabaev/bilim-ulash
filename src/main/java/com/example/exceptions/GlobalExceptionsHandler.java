package com.example.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestControllerAdvice
public class GlobalExceptionsHandler {
        @ExceptionHandler(MyProjectException.class)
        public String handleWebException(MyProjectException exception, HttpServletRequest request, RedirectAttributes redirectAttributes) {

            String message = exception.getErrorType().getMessage();

            redirectAttributes.addFlashAttribute("errorMessage", message);

            String referer = request.getHeader("Referer");

            if (referer == null) {
                return "redirect:/view/users";
            }
            return "redirect:" + referer;
        }
    }

