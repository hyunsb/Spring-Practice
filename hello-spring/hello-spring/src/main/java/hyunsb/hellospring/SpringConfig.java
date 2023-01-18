package hyunsb.hellospring;

import hyunsb.hellospring.repository.MemberRepository;
import hyunsb.hellospring.repository.MemoryMemberRepository;
import hyunsb.hellospring.service.MemberService;
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
