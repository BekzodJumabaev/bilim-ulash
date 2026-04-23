package com.example.controller.restController;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.dto.skillDto.SkillsRequestDTO;
import com.example.dto.userDto.UserDto;
import com.example.service.SkillService;
import com.example.service.UserSkillsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/skills")
public class SkillController {

    private final UserSkillsService service;

    @PostMapping("/add/{userId}")
    public ResponseEntity<SkillResponseDto> addSkill(@PathVariable("userId") Long userId, @RequestBody SkillsRequestDTO dto) {
        return ResponseEntity.ok(service.addSkill(userId, dto));
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
