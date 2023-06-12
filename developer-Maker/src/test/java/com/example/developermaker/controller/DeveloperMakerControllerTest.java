package com.example.developermaker.controller;

import com.example.developermaker.dto.DeveloperDetailDto;
import com.example.developermaker.dto.DeveloperDto;
import com.example.developermaker.service.DeveloperMakerService;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import com.example.developermaker.type.StatusCode;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@MockBean(JpaMetamodelMappingContext.class)
@WebMvcTest(DeveloperMakerController.class)
class DeveloperMakerControllerTest {

    private final MockMvc mockMvc;
    protected MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            StandardCharsets.UTF_8);
    @MockBean
    private DeveloperMakerService developerMakerService;

    @Autowired
    public DeveloperMakerControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void getAllDevelopers() throws Exception {
        DeveloperDto developerDto1 = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.BACK_END)
                .developerLevel(DeveloperLevel.JUNIOR)
                .memberId("memberId1").build();

        DeveloperDto developerDto2 = DeveloperDto.builder()
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .developerLevel(DeveloperLevel.SENIOR)
                .memberId("memberId2").build();

        BDDMockito.given(developerMakerService.getAllEmployedDevelopers())
                .willReturn(Arrays.asList(developerDto1, developerDto2));

        mockMvc.perform(get("/developers").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.[0].developerSkillType", CoreMatchers.is(DeveloperSkillType.BACK_END.name())))
                .andExpect(jsonPath("$.[0].developerLevel", CoreMatchers.is(DeveloperLevel.JUNIOR.name())))
                .andExpect(jsonPath("$.[1].developerSkillType", CoreMatchers.is(DeveloperSkillType.FRONT_END.name())))
                .andExpect(jsonPath("$.[1].developerLevel", CoreMatchers.is(DeveloperLevel.SENIOR.name())));
    }

    @Test
    void getDeveloperDetail() throws Exception {
        DeveloperDetailDto developerDetail = DeveloperDetailDto.builder()
                .developerSkillType(DeveloperSkillType.BACK_END)
                .developerLevel(DeveloperLevel.SENIOR)
                .memberId("memberId")
                .experienceYear(12)
                .name("홍길동")
                .age(34)
                .statusCode(StatusCode.EMPLOYED)
                .build();

        String testMemberId = "memberId";
        BDDMockito.given(developerMakerService.getDeveloperDetail(testMemberId))
                .willReturn(developerDetail);

        mockMvc.perform(get("/developer/" + testMemberId).contentType(contentType))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.developerSkillType", CoreMatchers.is(DeveloperSkillType.BACK_END.name())))
                .andExpect(jsonPath("$.developerLevel", CoreMatchers.is(DeveloperLevel.SENIOR.name())));
    }
}