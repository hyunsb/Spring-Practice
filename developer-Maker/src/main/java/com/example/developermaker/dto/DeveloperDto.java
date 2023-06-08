package com.example.developermaker.dto;

import com.example.developermaker.entity.Developer;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import lombok.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class DeveloperDto {
    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private String memberId;

    public static DeveloperDto fromEntity(Developer developer) {
        return new DeveloperDto(developer.getDeveloperLevel()
                , developer.getDeveloperSkillType()
                , developer.getMemberId());
    }
}
