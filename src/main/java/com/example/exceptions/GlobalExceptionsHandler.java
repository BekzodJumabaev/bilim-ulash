package com.example.exceptions;

import com.example.dto.userDto.UserDto;
import com.example.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@ControllerAdvice
public class GlobalExceptionsHandler {

    private final UserService userService;

    public GlobalExceptionsHandler(UserService userService) {
        this.userService = userService;
    }

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

    @ModelAttribute
    public void addAttributes(Model model, Principal principal) {
        if (principal != null) {
            UserDto user = userService.findByPhoneNumber(principal.getName());
            model.addAttribute("user", user);
        }
    }
}

