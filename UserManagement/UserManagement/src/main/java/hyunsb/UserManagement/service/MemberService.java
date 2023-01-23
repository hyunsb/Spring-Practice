package hyunsb.UserManagement.service;

import hyunsb.UserManagement.domain.Member;
import hyunsb.UserManagement.repository.MemberRepository;
import hyunsb.UserManagement.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();

    public Long join(Member member){
        //중복 회원 검증
        validateDuplicateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(String name){
        memberRepository.findByName(name)
                .ifPresent(result -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public Optional<Member> findOne(String memberName) {
        return memberRepository.findByName(memberName);
    }
}
