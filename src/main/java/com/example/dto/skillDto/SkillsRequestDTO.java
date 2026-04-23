package com.example.dto.skillDto;

import com.example.model.SkillType;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SkillsRequestDTO {
    private Long id;
    private SkillType skillType;
}
