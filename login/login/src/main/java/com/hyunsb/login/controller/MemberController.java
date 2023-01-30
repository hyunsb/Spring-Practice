package com.hyunsb.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    // 회원가입 페이지 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "member/save";
    }

    @PostMapping("/member/save")
    public String saveMember(@RequestParam("memberEmail") String memberEmail,
                             @RequestParam("memberPassword") String memberPassword,
                             @RequestParam("memberName") String memberName){
        return "index";
    }

}
