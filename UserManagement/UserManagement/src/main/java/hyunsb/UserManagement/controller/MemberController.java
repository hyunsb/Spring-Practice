package hyunsb.UserManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("/members/new")
    public String join() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String join(MemberForm form) {
        // 회원 가입 서비스

        return "redirect:/";
    }
}