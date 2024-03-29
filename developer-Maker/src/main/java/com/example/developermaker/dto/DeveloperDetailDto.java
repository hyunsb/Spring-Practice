package com.example.developermaker.dto;

import com.example.developermaker.entity.Developer;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import com.example.developermaker.type.StatusCode;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeveloperDetailDto {

    private DeveloperLevel developerLevel;
    private DeveloperSkillType developerSkillType;
    private Integer experienceYear;
    private String memberId;
    private StatusCode statusCode;
    private String name;
    private Integer age;

    public static DeveloperDetailDto fromEntity(Developer developer) {
        return new DeveloperDetailDto(developer.getDeveloperLevel()
                , developer.getDeveloperSkillType()
                , developer.getExperienceYear()
                , developer.getMemberId()
                , developer.getStatusCode()
                , developer.getName()
                , developer.getAge());
    }
}
