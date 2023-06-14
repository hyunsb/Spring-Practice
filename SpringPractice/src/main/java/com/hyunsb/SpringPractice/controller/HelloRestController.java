package com.hyunsb.SpringPractice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloRestController {

    @GetMapping("/helloRest")
    public ResponseEntity<?> hello() {
        log.info("call helloRest");
        return ResponseEntity.ok("hello Rest!");
    }
}
