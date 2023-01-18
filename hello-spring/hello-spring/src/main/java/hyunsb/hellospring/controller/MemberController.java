package hyunsb.hellospring.controller;

import hyunsb.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링 컨테이너에서 스프링 빈이 관리
@Controller
public class MemberController {

    private final MemberService memberService;

    // 스프링 컨테이너에 존재하는 memberService 를 연결(의존관계 주입)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
