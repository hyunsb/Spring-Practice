package com.example.developermaker.service;

import com.example.developermaker.dto.CreateDeveloper;
import com.example.developermaker.dto.DeveloperDetailDto;
import com.example.developermaker.dto.DeveloperDto;
import com.example.developermaker.dto.EditDeveloper;
import com.example.developermaker.entity.Developer;
import com.example.developermaker.entity.RetiredDeveloper;
import com.example.developermaker.exception.DeveloperMakerException;
import com.example.developermaker.repository.DeveloperRepository;
import com.example.developermaker.repository.RetiredDeveloperRepository;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import com.example.developermaker.type.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.developermaker.exception.DeveloperMakerErrorCode.*;

@Service
@RequiredArgsConstructor
public class DeveloperMakerService {

    private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(CreateDeveloper.Request request) {

        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYear(request.getExperienceYear())
                .statusCode(StatusCode.EMPLOYED)
                .memberId(request.getMemberId())
                .name(request.getName())
                .age(request.getAge()).build();

        System.out.println(developer);

        Developer result = developerRepository.save(developer);

        System.out.println(result);
        return CreateDeveloper.Response.fromEntity(result);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {

        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYear());

        developerRepository.findByMemberId(request.getMemberId()).ifPresent(developer -> {
                    throw new DeveloperMakerException(DUPLICATED_MEMBER_ID);
                });
    }

    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
                .stream()
                .map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .map(DeveloperDetailDto::fromEntity)
                .orElseThrow(() -> new DeveloperMakerException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(String memberId, EditDeveloper.Request request) {
        validateDeveloperLevel(request.getDeveloperLevel(), request.getExperienceYear());

        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DeveloperMakerException(DUPLICATED_MEMBER_ID));

        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYear(request.getExperienceYear());

        return DeveloperDetailDto.fromEntity(developer);
    }

    private void validateDeveloperLevel(DeveloperLevel developerLevel, Integer experienceYear) {
        if (developerLevel == DeveloperLevel.SENIOR && experienceYear < 10)
            throw new DeveloperMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);

        if (developerLevel == DeveloperLevel.JUNGNIOR && (experienceYear < 4 || experienceYear > 10))
            throw new DeveloperMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);

        if (developerLevel == DeveloperLevel.JUNIOR && (experienceYear > 4))
            throw new DeveloperMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DeveloperMakerException(DUPLICATED_MEMBER_ID));

        developer.setStatusCode(StatusCode.RETIRED);
        developerRepository.delete(developer);

        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();

        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }
}
