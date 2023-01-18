package hyunsb.hellospring.repository;

import hyunsb.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원을 저장소에 저장
    Member save(Member member);
    // 회원을 아이디로 검색
    Optional<Member> findById(Long id);
    // 회원을 이름으로 검색
    Optional<Member> findByName(String name);
    // 모든 회원을 검색
    List<Member> findAll();
}
