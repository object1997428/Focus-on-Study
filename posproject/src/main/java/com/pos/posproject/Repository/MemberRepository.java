package com.pos.posproject.Repository;

import PoSproject.PoS.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface MemberRepository {//가입 저장을 위해 사용되는 인터페이스

    Member save(Member member);

    Optional<Member> findByCode(int code);

    Optional<Member> findByName(String name);

    Optional<Member> findById(String id);

    List<Member> findAll();//모든 회원 리스트 반환

}
