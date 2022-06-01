package com.pos.posproject.Repository;


import com.pos.posproject.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Integer, Member> store = new HashMap<>();//코드, 멤버클래스
    private static int sequence = 0;

    public void clearStore() {
        store.clear();
    }


    @Override
    public Member save(Member member) {
        member.setCode(++sequence);
        store.put(member.getCode(), member);
        return member;
    }

    @Override
    public Optional<Member> findByCode(int code) {
        return Optional.ofNullable(store.get(code));//NULL이라도 감쌀 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {//name은 중복확인 필요없지 않나,, 이름으로 찾지도 않을듯
        return store.values().stream()
                .filter(member -> member.getName().equals(name))//동일한 값만 담음
                .findAny();//전체에서 검색?,,이라고 되어있음
    }

    @Override
    public Optional<Member> findById(String id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();//
    }


    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//store의 모든 값을 가져와 arrayList에 담아서 반환
    }
}
