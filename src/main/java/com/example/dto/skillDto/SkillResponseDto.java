package com.example.dto.skillDto;
import com.example.model.SkillType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SkillResponseDto {
    private Long id;
    private String skillName;
    private SkillType skillType;
}
