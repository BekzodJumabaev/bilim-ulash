package com.example.service;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.dto.skillDto.SkillsRequestDTO;
import com.example.dto.userDto.UserDto;
import com.example.exceptions.ErrorType;
import com.example.exceptions.MyProjectException;
import com.example.mapper.UserSkillMapper;
import com.example.model.Skills;
import com.example.model.UserSkill;
import com.example.model.Users;
import com.example.repository.SkillRepository;
import com.example.repository.UserRepository;
import com.example.repository.UserSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserSkillsService {
    private final SkillRepository skillRepository;
    private final UserRepository userRepository;
    private final UserSkillRepository  userSkillRepository;
    private final UserSkillMapper  mapper;

    @Transactional
    public SkillResponseDto addSkill(Long userId, SkillsRequestDTO dto) {

        Users users = userRepository.findById(userId).orElseThrow(
                () -> new MyProjectException(ErrorType.USER_NOT_FOUND));
        Skills skill = skillRepository.findById(dto.getId()).orElseThrow(
                () -> new MyProjectException(ErrorType.SKILL_NOT_FOUND));
        if (userSkillRepository.existsByUsersIdAndSkillsIdAndSkillType(userId, dto.getId(), dto.getSkillType())) {
            throw new MyProjectException(ErrorType.SKILL_ALREADY_REGISTERED);
        }

        UserSkill userSkill = UserSkill
                .builder()
                .users(users)
                .skills(skill)
                .skillType(dto.getSkillType())
                .build();
        UserSkill saveSkill = userSkillRepository.save(userSkill);
        return mapper.toDto(saveSkill);

    }

    public List<SkillResponseDto> getUserSkills(Long userId) {
        List<UserSkill> allByUserId = userSkillRepository.findAllByUsersId(userId);
        return mapper.toDto(allByUserId);
    }

    public List<UserDto> getTeacherSkills(Long skillsId) {
        List<Users> teachersBySkillId = userSkillRepository.findTeachersBySkillId(skillsId);
        return teachersBySkillId.stream().map(users -> UserDto
                .builder()
                .id(users.getId())
                .fullName(users.getFullName())
                .phoneNumber(users.getPhoneNumber())
                .timeBalans(users.getTimeBalans())
                .build()).collect(Collectors.toList());
    }

    public SkillResponseDto addSkillByPhone(SkillsRequestDTO dto, String phoneNumber) {
        Users users = userRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new MyProjectException(ErrorType.USER_NOT_FOUND)
        );
        return this.addSkill(users.getId(), dto);
    }
}
