package com.example.mapper;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.model.UserSkill;
import com.example.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserSkillMapper {

    @Mapping(target = "skillName", source = "skills.skillName")
    @Mapping(target = "id", source = "skills.id")
    SkillResponseDto toDto(UserSkill userSkill);

    List<SkillResponseDto> toDto(List<UserSkill> userSkills);
}
