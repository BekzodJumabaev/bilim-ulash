package com.example.mapper;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.dto.userDto.UserCreateDto;
import com.example.dto.userDto.UserDto;
import com.example.dto.userDto.UserUpdateDto;
import com.example.model.Users;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    public Users toEntity(UserCreateDto dto) {
        return Users
                .builder()
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .password(dto.getPassword())
                .timeBalans(60)
                .build();
    }
    public UserDto toDto(Users entity) {
        return UserDto.builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .timeBalans(entity.getTimeBalans())
                .build();
    }

    public UserDto toDto(Users entity, List<SkillResponseDto> userSkills) {
        return UserDto
                .builder()
                .id(entity.getId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .timeBalans(entity.getTimeBalans())
                .skills(userSkills)
                .build();
    }

    public void updateEntity(UserUpdateDto dto, Users entity) {
        if (dto.getFullName() != null) {
            entity.setFullName(dto.getFullName());
        }
        if (dto.getPhoneNumber() != null) {
            entity.setPhoneNumber(dto.getPhoneNumber());
        }
    }
}
