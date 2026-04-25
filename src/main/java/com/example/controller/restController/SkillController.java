package com.example.controller.restController;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.dto.skillDto.SkillsRequestDTO;
import com.example.dto.userDto.UserDto;
import com.example.service.SkillService;
import com.example.service.UserService;
import com.example.service.UserSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {

    private final UserSkillsService service;
    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<SkillResponseDto> addSkill(@RequestBody SkillsRequestDTO dto, Authentication authentication) {
        return ResponseEntity.ok(service.addSkillByPhone(dto, authentication.getName()));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SkillResponseDto>> getMySkills(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(service.getUserSkills(userId));
    }

    @GetMapping("/teachers/{skillsId}")
    public ResponseEntity<List<UserDto>> getTeachers(@PathVariable("skillsId")  Long skillsId) {
        return ResponseEntity.ok(service.getTeacherSkills(skillsId));
    }
}
