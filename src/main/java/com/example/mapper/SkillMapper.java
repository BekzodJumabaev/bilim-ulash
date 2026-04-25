package com.example.mapper;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.model.Skills;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillResponseDto toDto(Skills skill);
    List<SkillResponseDto> toDto(List<Skills> skills);
}
