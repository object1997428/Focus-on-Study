package com.pos.posproject.service;

import PoSproject.PoS.Repository.MemberRepository;
import PoSproject.PoS.Repository.MemoryMemberRepository;
import PoSproject.PoS.dao.LookupMapper;
import PoSproject.PoS.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class memberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final LookupMapper lookupMapper;


    public String join(Member member) {
        validateDuplicateMember(member);//중복 회원 검증, 밑에 있음
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });

    }

    public List<Member> findMembers() {
        return LookupMapper.findAll();
    }

    public Optional<Member> findMemberById(String memberId) {
        return memberRepository.findById(memberId);
    }


}
