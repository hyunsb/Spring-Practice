package com.hyunsb.SpringPractice.service;

import com.hyunsb.SpringPractice.domain.Member;
import com.hyunsb.SpringPractice.repository.MemberRepository;
import com.hyunsb.SpringPractice.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Objects;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateEmptyName(member);
        validateDuplicateName(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateEmptyName(Member member) {
        if (Objects.isNull(member.getName()))
            throw new IllegalArgumentException("name empty error");
    }

    private void validateDuplicateName(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(member1 -> {
            throw new IllegalArgumentException("name Duplicate error");
        });
    }

    /**
     * 전체 회원 조회
     *
     * @return List:Member
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 아이디로 회원 조회
     *
     * @param memberId
     * @return Member
     */
    public Member findOne(Long memberId) {
        if (Objects.isNull(memberId)) throw new IllegalArgumentException("Id empty");
        return memberRepository.findById(memberId).orElseThrow(() ->
                new IllegalArgumentException("회원이 존재하지 않습니다."));
    }

    /**
     * 이름으로 회원 조회
     *
     * @param memberName
     * @return Member
     */
    public Member findOne(String memberName) {
        if (Objects.isNull(memberName)) throw new IllegalArgumentException("name empty");
        return memberRepository.findByName(memberName).orElseThrow(() ->
                new IllegalArgumentException("회원이 존재하지 않습니다."));
    }
}
