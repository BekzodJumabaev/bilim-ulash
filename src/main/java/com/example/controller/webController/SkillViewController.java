package com.example.controller.webController;

import com.example.dto.skillDto.SkillsRequestDTO;
import com.example.dto.userDto.UserDto;
import com.example.service.SkillService;
import com.example.service.UserService;
import com.example.service.UserSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/view/skill")
@RequiredArgsConstructor
public class SkillViewController {
    private final UserService userService;
    private final SkillService skillService;
    private final UserSkillsService  userSkillsService;

    @GetMapping("/add")
    public String addSkill(Model model, Principal principal) {
        model.addAllAttributes(userSkillsService.allModelAttributes(principal.getName()));
        return "pages/user/add-skill";
    }

    @PostMapping("/save")
    public String saveSkill(@ModelAttribute("skillRequest") SkillsRequestDTO dto, Principal principal) {
        UserDto currentUser = userService.findByPhoneNumber(principal.getName());
        userSkillsService.addSkill(currentUser.getId(), dto);
        return "redirect:/home";
    }
}

