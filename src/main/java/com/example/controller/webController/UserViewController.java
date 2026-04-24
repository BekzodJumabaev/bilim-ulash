package com.example.controller.webController;
import com.example.dto.transactionDto.TransactionCreateDto;
import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.service.TransactionService;
import com.example.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/users")
@RequiredArgsConstructor
public class UserViewController {
    private final UserService userService;

    @GetMapping
    public String userList(
            @RequestParam(value = "keyword", required = false) String keyword,
            @RequestParam(value = "page", defaultValue = "0")  int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {
        Page<UserDto> allUsers = userService.getAllUsers(keyword, page, size);

        model.addAttribute("users", allUsers.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", allUsers.getTotalPages());
        model.addAttribute("keyword", keyword);

        return "pages/user/list";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("userDto", new UserCreateDto());
        return "pages/user/add";
    }

    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("userDto") UserCreateDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "pages/user/add";
        }
        userService.save(dto);
        return "redirect:/view/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/view/users";
    }

}
