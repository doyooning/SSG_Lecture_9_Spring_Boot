package com.dynii.doplyshop.member.service;

import com.dynii.doplyshop.member.entity.Member;
import com.dynii.doplyshop.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService {
    private final MemberRepository memberRepository;


    @Override
    public void save(String name, String loginId, String loginPw) {
        memberRepository.save(new Member(name, loginId, loginPw));
    }

    @Override
    public Member find(String loginId, String loginPw) {
        Optional<Member> member = memberRepository.findByLoginIdAndLoginPw(loginId, loginPw);
        return member.orElse(null); // 회원 데이터가 있으면 해당 멤버를 리턴, 없으면 Null
    }
}
