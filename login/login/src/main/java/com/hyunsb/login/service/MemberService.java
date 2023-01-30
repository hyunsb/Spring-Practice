package com.hyunsb.login.service;

import com.hyunsb.login.dto.MemberDTO;
import com.hyunsb.login.entity.MemberEntity;
import com.hyunsb.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        // 2. repository 의 save 메서드 호출
        memberRepository.save(memberEntity);
    }
}
