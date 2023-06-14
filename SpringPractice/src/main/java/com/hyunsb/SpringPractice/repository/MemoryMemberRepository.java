package com.hyunsb.SpringPractice.repository;

import com.hyunsb.SpringPractice.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        if (Objects.isNull(member.getName()))
            throw new IllegalArgumentException("name empty error");

        if (!Objects.equals(Optional.empty(), findByName(member.getName())))
            throw new IllegalArgumentException("name duplicate error");

        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = store.get(id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void deleteAll() {
        store.clear();
    }
}
