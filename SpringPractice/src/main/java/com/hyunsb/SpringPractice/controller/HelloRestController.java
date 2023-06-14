package com.hyunsb.SpringPractice.controller;

import com.hyunsb.SpringPractice.dto.HelloResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloRestController {

    @GetMapping("/helloRest")
    public ResponseEntity<?> hello() {
        log.info("helloRest");
        return ResponseEntity.ok("hello Rest!");
    }

    // Object를 반환하면 json으로 파싱됨
    @GetMapping("helloRest-mvc")
    public ResponseEntity<HelloResponseDto> helloMvc(@RequestParam("name") String name) {
        log.info("helloRest-Mvc");
        return ResponseEntity.ok(new HelloResponseDto(name));
    }
}
