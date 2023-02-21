package com.hyunsb.MVC_JPA.service;

import com.hyunsb.MVC_JPA.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberService();


    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("test");

        //then
        Long saveId = memberService.join(member);

        //when
        Optional<Member> result = memberService.findOne(saveId);
        Assertions.assertThat(saveId).isEqualTo(member.getId());
    }

    @Test
    void 중복_회원_예외() {
        Member member = new Member();
        member.setName("test");
        memberService.join(member);

        try{
            memberService.join(member);
            fail();
        } catch (IllegalStateException ignore){}
    }
}