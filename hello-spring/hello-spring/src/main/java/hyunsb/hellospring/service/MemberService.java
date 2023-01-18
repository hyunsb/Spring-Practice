package hyunsb.hellospring.service;

import hyunsb.hellospring.domain.Member;
import hyunsb.hellospring.repository.MemberRepository;
import hyunsb.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private MemberRepository memberRepository = new MemoryMemberRepository();

    /**
     * 회원 가입
     * */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //같은 이름이 존재하는 중복 회원은 가입 불가능

        /*Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        });*/

        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     * 전체 회원 조회
     * */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
