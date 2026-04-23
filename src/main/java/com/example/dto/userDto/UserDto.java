package com.example.dto.userDto;

import com.example.dto.skillDto.SkillResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private Integer timeBalans;
    private List<SkillResponseDto>  skills;
}
