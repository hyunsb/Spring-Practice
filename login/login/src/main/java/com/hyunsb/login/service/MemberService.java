package com.hyunsb.login.service;

import com.hyunsb.login.dto.MemberDTO;
import com.hyunsb.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {

    }
}
