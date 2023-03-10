package com.hyunsb.MVC_JPA.service;

import com.hyunsb.MVC_JPA.domain.Member;
import com.hyunsb.MVC_JPA.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    void cleanUp(){
        memberRepository.clearStore();
    }


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

        Member member2 = new Member();
        member2.setName("test");

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });

        Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join(member);
//            fail();
//        } catch (IllegalStateException exception){
//            Assertions.assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
    }
}