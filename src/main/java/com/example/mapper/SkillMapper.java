package com.example.mapper;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.model.Skills;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SkillMapper {

    public SkillResponseDto toDto(Skills skills) {
        return SkillResponseDto
                .builder()
                .id(skills.getId())
                .skillName(skills.getSkillName())
                .build();
    }

    public List<SkillResponseDto> toDto(List<Skills> list) {
        return list.stream().map(this::toDto).toList();
    }
}
