package hyunsb.hellospring.service;

import hyunsb.hellospring.domain.Member;
import hyunsb.hellospring.repository.MemberRepository;
import hyunsb.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    public void beforeEach(){
        memoryMemberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    public void afterEach(){
        memoryMemberRepository.clearStore();
    }

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
        /*memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException exception){
            assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}