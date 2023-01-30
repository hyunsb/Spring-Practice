package com.hyunsb.login.controller;

import com.hyunsb.login.dto.MemberDTO;
import com.hyunsb.login.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        return "member/login";
    }

    @GetMapping("/member/login")
    public String loginForm(){
        return "member/login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공, 세션에 저장
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // 로그인 실패
            return "/member/login";
        }
    }

    @GetMapping("/member/list")
    public String list(Model model){
        model.addAttribute("memberList", memberService.findAll());
        return "/member/list";
    }

}
