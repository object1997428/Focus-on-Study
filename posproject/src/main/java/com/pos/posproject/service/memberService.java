package com.pos.posproject.service;


import com.pos.posproject.Repository.MemberRepository;
import com.pos.posproject.Repository.MemoryMemberRepository;
import com.pos.posproject.domain.Member;
//import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class memberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();


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

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findMemberById(String memberId) {
        return memberRepository.findById(memberId);
    }


}
