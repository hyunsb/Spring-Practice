package com.hyunsb.MVC_JPA.controller;

import com.hyunsb.MVC_JPA.domain.Member;
import com.hyunsb.MVC_JPA.dto.MemberDTO;
import com.hyunsb.MVC_JPA.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String saveMember(MemberDTO memberDTO){
        Member member = new Member();

        member.setName(memberDTO.getName());
        member.setPassword(memberDTO.getPassword());

        memberService.join(member);

        return "redirect:/";
    }


    @GetMapping("/members")
    public String memberList(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
