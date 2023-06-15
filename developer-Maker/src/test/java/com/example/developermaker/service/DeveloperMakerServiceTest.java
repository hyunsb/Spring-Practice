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
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeveloperMakerServiceTest {

    @Mock
    private DeveloperRepository developerRepository;

    @Mock
    private RetiredDeveloperRepository retiredDeveloperRepository;

    @InjectMocks
    private DeveloperMakerService developerMakerService;

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
    void createDeveloperTest_success() {
        //given
        CreateDeveloper.Request request = CreateDeveloper.Request.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYear(3)
                .name("정현수")
                .memberId("memberId")
                .age(20)
                .build();

        BDDMockito.given(developerRepository.findByMemberId(anyString()))
                .willReturn(Optional.empty());

        ArgumentCaptor<Developer> captor = ArgumentCaptor.forClass(Developer.class);

        //when
        CreateDeveloper.Response developer = developerMakerService.createDeveloper(request);

        //then
        verify(developerRepository, times(1)).save(captor.capture());

        Developer savedDeveloper = captor.getValue();
        Assertions.assertEquals(savedDeveloper.getDeveloperLevel(), DeveloperLevel.SENIOR);
        Assertions.assertEquals(savedDeveloper.getDeveloperSkillType(), DeveloperSkillType.FRONT_END);
        Assertions.assertEquals(savedDeveloper.getExperienceYear(), 3);
    }
}