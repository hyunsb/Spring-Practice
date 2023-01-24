package hyunsb.UserManagement.controller;

import hyunsb.UserManagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String create(){
        return "members/createMemberForm";
    }

    @GetMapping("/members")
    public String list(){
        return "members/memberList";
    }

}

