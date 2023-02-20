package com.hyunsb.MVC_JPA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    /*
    * Controller 에서 리턴값으로 문자를 반환하면 ViewResolver가 View를 탐색한 후 처리한다.
    *   - 스프링부트 템플릿엔진 기본 viewName 매핑
    *   - resources:template/ + {viewName} + .html
    * */

    /*
    * spring-boot-devtools 라이브러리 추가 시
    * html 파일을 컴파일만 해주면 서버 재시작 없이 View 파일 변경이 가능하다.
    * */

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hyunsb!!");
        return "hello";
    }
}
