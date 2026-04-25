package com.example.controller.webController.homeMenu;

import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.service.TransactionService;
import com.example.service.UserService;
import com.example.service.UserSkillsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final UserService userService;

    @GetMapping
    private String homePage(Model model, Principal principal) {

        if (principal == null) {
            return "redirect:/login";
        }
        UserDto dto = userService.findByPhoneNumber(principal.getName());
        model.addAttribute("user", dto);
        return "fragments/home";
    }
}
