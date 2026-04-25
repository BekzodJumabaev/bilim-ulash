package com.example.controller.webController;

import com.example.dto.transactionDto.TransactionCreateDto;
import com.example.dto.transactionDto.TransactionResponseDto;
import com.example.dto.userDto.UserDto;
import com.example.exceptions.MyProjectException;
import com.example.service.TransactionService;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/view/transfer")
@RequiredArgsConstructor
public class TransactionViewController {

    private final TransactionService transactionService;
    private final UserService userService;

    @GetMapping()
    public String transferPage(@RequestParam (value = "keyword", required = false) String keyword,
                               @RequestParam (value = "page",defaultValue = "0")  int page,
                               @RequestParam(value = "size", defaultValue = "5")   int size,
                               Model model) {

        Page<UserDto> allUsers = userService.getAllUsers(keyword, page, size);

        model.addAttribute("users", allUsers.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", allUsers.getTotalPages());
        model.addAttribute("keyword", keyword);

        model.addAttribute("transactionDto", new TransactionCreateDto());
        model.addAttribute("list", transactionService.getHistory(keyword, page, size));
        return "pages/user/transfer";
    }

    @PostMapping()
    public String transferPage(@ModelAttribute("transactionDto") TransactionCreateDto dto, Principal principal) {
        transactionService.transferTime(dto, principal.getName());
        return "redirect:/view/users";
    }

    @GetMapping("/transactions")
    public String transactionHistory(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {

        Page<TransactionResponseDto> history = transactionService.getHistory(keyword, page, size);
        model.addAttribute("history", history.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", history.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "pages/user/history";
    }
}
