package com.hyunsb.MVC_JPA;

import com.hyunsb.MVC_JPA.repository.JpaMemberRepository;
import com.hyunsb.MVC_JPA.repository.MemberRepository;
import com.hyunsb.MVC_JPA.repository.MemoryMemberRepository;
import com.hyunsb.MVC_JPA.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }
}
