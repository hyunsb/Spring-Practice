package com.example.developermaker.controller;

import com.example.developermaker.dto.CreateDeveloper;
import com.example.developermaker.entity.Developer;
import com.example.developermaker.service.DeveloperMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeveloperMakerController {

    private final DeveloperMakerService developerMakerService;

    @GetMapping("/developers")
    public List<String> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");

        return Arrays.asList("snow", "sela", "olaf");
    }

    @PostMapping("/create-developer")
    public Developer createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request) {

        log.info("GET /create-developer HTTP/1.1");
        log.info("Request : {}", request);
        return developerMakerService.createDeveloper(request);
    }
}
