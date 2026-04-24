package com.example.service;

import com.example.dto.skillDto.SkillResponseDto;
import com.example.mapper.SkillMapper;
import com.example.model.Skills;
import com.example.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;
    private final SkillMapper mapper;

    public List<SkillResponseDto> getAllSkills() {
        List<Skills> all = skillRepository.findAll();
        return mapper.toDto(all);
    }
}
