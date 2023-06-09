package com.example.developermaker.service;

import com.example.developermaker.dto.CreateDeveloper;
import com.example.developermaker.dto.DeveloperDetailDto;
import com.example.developermaker.dto.DeveloperDto;
import com.example.developermaker.entity.Developer;
import com.example.developermaker.repository.DeveloperRepository;
import com.example.developermaker.repository.RetiredDeveloperRepository;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import com.example.developermaker.type.StatusCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeveloperMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DeveloperMakerService developerMakerService;

    @Test
    void getAllEmployedDevelopers() {
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYear(3)
                .name("정현수")
                .memberId("memberId")
                .age(20)
                .build();

        developerMakerService.createDeveloper(request);

        List<DeveloperDto> allEmployedDevelopers = developerMakerService.getAllEmployedDevelopers();
        Assertions.assertEquals(1, allEmployedDevelopers.size());
    }

    @Test
    void createDeveloper() {

    }

    @Test
    void getDeveloperDetail() {

        given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.of(Developer.builder()
                        .developerLevel(DeveloperLevel.SENIOR)
                        .developerSkillType(DeveloperSkillType.FRONT_END)
                        .experienceYear(12)
                        .statusCode(StatusCode.EMPLOYED)
                        .name("name")
                        .age(20)
                        .build()));

        DeveloperDetailDto result = developerMakerService.getDeveloperDetail("Test");

        Assertions.assertEquals(DeveloperLevel.SENIOR, result.getDeveloperLevel());
        Assertions.assertEquals(DeveloperSkillType.FRONT_END, result.getDeveloperSkillType());
        Assertions.assertEquals(12, result.getExperienceYear());
    }

    @Test
    void editDeveloper() {
    }

    @Test
    void deleteDeveloper() {
    }
}