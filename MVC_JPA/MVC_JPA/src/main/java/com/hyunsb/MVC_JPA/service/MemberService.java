package com.hyunsb.MVC_JPA.service;

import com.hyunsb.MVC_JPA.domain.Member;
import com.hyunsb.MVC_JPA.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * */
    public Long join(Member member){

        vaildateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void vaildateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * */
    private List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(long id){
        return memberRepository.findById(id);
    }

}
