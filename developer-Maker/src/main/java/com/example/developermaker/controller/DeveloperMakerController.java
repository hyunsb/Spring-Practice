package com.example.developermaker.controller;

import com.example.developermaker.dto.CreateDeveloper;
import com.example.developermaker.dto.DeveloperDetailDto;
import com.example.developermaker.dto.DeveloperDto;
import com.example.developermaker.dto.EditDeveloper;
import com.example.developermaker.service.DeveloperMakerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeveloperMakerController {

    private final DeveloperMakerService developerMakerService;

    @GetMapping("/developers")
    public List<DeveloperDto> getAllDevelopers() {
        log.info("GET /developers HTTP/1.1");
        return developerMakerService.getAllEmployedDevelopers();
    }

    @GetMapping("/developer/{memberId}")
    public DeveloperDetailDto getDeveloperDetail(@PathVariable String memberId) {
        return developerMakerService.getDeveloperDetail(memberId);
    }

    @PutMapping("/developer/{memberId}")
    public DeveloperDetailDto editDeveloper(
            @PathVariable String memberId,
            @Valid @RequestBody EditDeveloper.Request request) {

        return developerMakerService.editDeveloper(memberId, request);
    }

    @PostMapping("/create-developer")
    public CreateDeveloper.Response createDevelopers(
            @Valid @RequestBody CreateDeveloper.Request request) {

        log.info("Request : {}", request);
        return developerMakerService.createDeveloper(request);
    }

    @DeleteMapping("/developer/{memberId}")
    public DeveloperDetailDto deleteDeveloper(@PathVariable String memberId){
        return developerMakerService.deleteDeveloper(memberId);
    }
}
