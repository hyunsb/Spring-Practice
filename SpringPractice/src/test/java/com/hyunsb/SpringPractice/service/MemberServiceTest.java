package com.hyunsb.SpringPractice.service;

import com.hyunsb.SpringPractice.domain.Member;
import com.hyunsb.SpringPractice.repository.MemberRepository;
import com.hyunsb.SpringPractice.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @Test
    void join() {
        Member member = new Member();
        member.setName("TEST NAME");

        Long extract = 1L;
        Long actual = memberService.join(member);

        assertEquals(extract, actual);
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }

    @Test
    void testFindOne() {
    }
}