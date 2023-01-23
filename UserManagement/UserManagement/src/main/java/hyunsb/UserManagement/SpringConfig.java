package hyunsb.UserManagement;

import hyunsb.UserManagement.repository.MemberRepository;
import hyunsb.UserManagement.repository.MemoryMemberRepository;
import hyunsb.UserManagement.service.MemberService;
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
