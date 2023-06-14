package com.hyunsb.SpringPractice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @GetMapping("helloRest-mvc")
    public ResponseEntity<?> helloMvc(@RequestParam("name") String name) {
        log.info("helloRest-Mvc");
        return ResponseEntity.ok("안녕하세요, " + name + " 님");
    }
}
