package hyunsb.UserManagement.service;

import hyunsb.hellospring.domain.Member;
import hyunsb.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Spring, Database 통합 테스트 코드
@SpringBootTest
@Transactional // 각각의 테스트 시작 전에 트랜잭션을 시작하고 테스트 완료 후에 항상 롤백한다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memoryMemberRepository;

    @Test
    void 회원가입() {
        //given: 상황이 주어졌을 때
        Member member = new Member();
        member.setName("member1");

        //when: 실행 시
        Long saveId = memberService.join(member);

        //then: 결과
        //Optional<Member> one = memberService.findOne(saveId).get();
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("duplication member");

        Member member2 = new Member();
        member2.setName("duplication member");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}