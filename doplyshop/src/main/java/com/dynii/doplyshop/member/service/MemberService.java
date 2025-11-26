package com.dynii.doplyshop.member.service;

import com.dynii.doplyshop.member.entity.Member;

public interface MemberService {

    // 회원 데이터를 저장하는 메서드, 회원의 이름과 로그인 아이디, 비번을 매개변수로 받음
    void save(String name, String loginId, String loginPw);

    // 회원 데이터를 조회하는 메서드, 매개변수로 로그인 아이디, 비번을 받고 리턴 타입으로 회원 엔티티를 넘김
    Member find(String loginId, String loginPw);
}
