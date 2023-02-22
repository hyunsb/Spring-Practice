package com.hyunsb.MVC_JPA;

import com.hyunsb.MVC_JPA.repository.MemberRepository;
import com.hyunsb.MVC_JPA.repository.MemoryMemberRepository;
import com.hyunsb.MVC_JPA.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
