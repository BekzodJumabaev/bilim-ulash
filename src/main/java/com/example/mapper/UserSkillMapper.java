package com.example.mapper;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.model.UserSkill;
import com.example.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSkillMapper {

    public SkillResponseDto toDto(UserSkill skill) {
        return SkillResponseDto
                .builder()
                .id(skill.getId())
                .skillName(skill.getSkills().getSkillName())
                .skillType(skill.getSkillType())
                .build();

    }

    public List<SkillResponseDto> toDto(List<UserSkill> allByUserId) {
        return allByUserId.stream().map(this::toDto).toList();
    }
}
