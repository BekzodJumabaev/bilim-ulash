package com.example.controller.webController.auth;


import com.example.dto.userDto.UserCreateDto;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/auth/register")
    public String registerPage(Model model){
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "register";
    }

    @PostMapping("/auth/register")
    public String registerUser(@Valid @ModelAttribute("userCreateDto") UserCreateDto dto,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.register(dto);
        return "redirect:/login?success=true";
    }
}
