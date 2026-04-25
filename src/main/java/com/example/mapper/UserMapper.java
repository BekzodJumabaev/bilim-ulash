package com.example.mapper;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.dto.userDto.UserUpdateDto;
import com.example.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring", uses =  {UserSkillMapper.class})
public interface UserMapper {

    @Mapping(target = "skills", source = "userSkills")
    UserDto toDto(Users user);


    Users toEntity(UserCreateDto dto);
}
