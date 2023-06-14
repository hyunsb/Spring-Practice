package com.hyunsb.SpringPractice.repository;

import com.hyunsb.SpringPractice.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemoryMemberRepositoryTest {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
    }

    @Test
    void save_Test_Success() {
        Member extract = new Member();
        extract.setName("TEST NAME");

        Member actual = memberRepository.save(extract);

        assertEquals(extract.getName(), actual.getName());
    }

    @Test
    void save_Test_Failed_Empty_Name() {
        Member member = new Member();
        assertThrows(IllegalArgumentException.class, () -> {
            memberRepository.save(member);
        });
    }

    @Test
    void save_Test_Failed_Duplicate_Name() {
        Member member = new Member();
        member.setName("TEST NAME");
        memberRepository.save(member);

        assertThrows(IllegalArgumentException.class, () -> {
            memberRepository.save(member);
        });
    }
}