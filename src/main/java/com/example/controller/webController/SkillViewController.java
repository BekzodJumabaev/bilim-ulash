package com.example.controller.webController;

import com.example.dto.skillDto.SkillsRequestDTO;
import com.example.service.SkillService;
import com.example.service.UserService;
import com.example.service.UserSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/skill")
@RequiredArgsConstructor
public class SkillViewController {
    private final UserService userService;
    private final SkillService skillService;
    private final UserSkillsService  userSkillsService;

    @GetMapping("/add/{userId}")
    public String addSkill(@PathVariable Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("skillList", skillService.getAllSkills());
        model.addAttribute("skillRequest", new SkillsRequestDTO());
        return "pages/user/add-skill";
    }

    @PostMapping("/save/{userId}")
    public String saveSkill(@PathVariable Long userId, @ModelAttribute("skillRequest") SkillsRequestDTO dto) {
        userSkillsService.addSkill(userId, dto);
        return "redirect:/view/users";
    }
}

