package com.example.controller.webController.auth;


import com.example.dto.userDto.UserCreateDto;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("userCreateDto") UserCreateDto dto){
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userCreateDto") UserCreateDto dto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "login";
        }
        userService.register(dto);
        return "redirect:/login?success=true";
    }
}
