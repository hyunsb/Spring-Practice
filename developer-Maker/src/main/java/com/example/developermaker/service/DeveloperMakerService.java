package com.example.developermaker.service;

import com.example.developermaker.dto.CreateDeveloper;
import com.example.developermaker.entity.Developer;
import com.example.developermaker.exception.DeveloperMakerException;
import com.example.developermaker.repository.DeveloperRepository;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.developermaker.exception.DeveloperMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.example.developermaker.exception.DeveloperMakerErrorCode.LEVEL_EXPERIENCE_YEARS_NOT_MATCHED;

@Service
@RequiredArgsConstructor
public class DeveloperMakerService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public Developer createDeveloper(CreateDeveloper.Request request) {

        validateCreateDeveloperRequest(request);

        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYear(2)
                .name("Olaf")
                .age(5)
                .build();

        return developerRepository.save(developer);
    }

    private void validateCreateDeveloperRequest(CreateDeveloper.Request request) {

        DeveloperLevel developerLevel = request.getDeveloperLevel();
        Integer experienceYear = request.getExperienceYear();

        if (developerLevel == DeveloperLevel.SENIOR && experienceYear < 10)
            throw new DeveloperMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);

        if (developerLevel == DeveloperLevel.JUNGNIOR && (experienceYear < 4 || experienceYear > 10))
            throw new DeveloperMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);

        if (developerLevel == DeveloperLevel.JUNIOR && (experienceYear > 4))
            throw new DeveloperMakerException(LEVEL_EXPERIENCE_YEARS_NOT_MATCHED);

        developerRepository.findByMemberId(request.getMemberId()).ifPresent(developer -> {
            throw new DeveloperMakerException(DUPLICATED_MEMBER_ID);
        });
    }
}
