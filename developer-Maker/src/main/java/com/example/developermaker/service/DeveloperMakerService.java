package com.example.developermaker.service;

import com.example.developermaker.entity.Developer;
import com.example.developermaker.repository.DeveloperRepository;
import com.example.developermaker.type.DeveloperLevel;
import com.example.developermaker.type.DeveloperSkillType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class DeveloperMakerService {

    private final DeveloperRepository developerRepository;

    @Transactional
    public void createDeveloper() {
        Developer developer = Developer.builder()
                .developerLevel(DeveloperLevel.JUNIOR)
                .developerSkillType(DeveloperSkillType.FRONT_END)
                .experienceYear(2)
                .name("Olaf")
                .age(5)
                .build();

        developerRepository.save(developer);
    }
}
