package com.hyunsb.springbootprac.controller;

import com.hyunsb.springbootprac.service.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final SortService sortService;

    @GetMapping("/")
    public String hello(@RequestParam List<String> list) {
        List<String> strings = sortService.doSort(list);
        System.out.println(strings);
        return strings.toString();
    }
}
