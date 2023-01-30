package com.hyunsb.login.controller;

import com.hyunsb.login.dto.MemberDTO;
import com.hyunsb.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor // 생성자 자동생성
public class MemberController {

    //생성자 주입
    private final MemberService memberService;

    // 회원가입 페이지 요청
    @GetMapping("/member/save")
    public String saveForm(){
        return "member/save";
    }

    @PostMapping("/member/save")
    public String saveMember(@ModelAttribute MemberDTO memberDTO){
        memberService.save(memberDTO);
        return "index";
    }

}
