package com.hyunsb.MVC_JPA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http의 body에 내용을 직접 입력, 데이터를 직접 응답
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //json 방식으로 표현 {"key" : "value"}
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name,
                          @RequestParam("age") int age,
                          @RequestParam("identity") String identity){

        Hello hello = new Hello(name, age, identity);
        return hello;
    }

    public class Hello {
        private String name;
        private int age;
        private String identity;

        public Hello(String name, int age, String identity) {

            this.name = name;
            this.age = age;
            this.identity = identity;

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getIdentity() {
            return identity;
        }

        public void setIdentity(String identity) {
            this.identity = identity;
        }
    }
}
