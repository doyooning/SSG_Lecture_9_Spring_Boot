package com.dynii.doplyshop.account.helper;

import com.dynii.doplyshop.account.dto.AccountJoinRequest;
import com.dynii.doplyshop.account.dto.AccountLoginRequest;
import com.dynii.doplyshop.account.etc.AccountConstants;
import com.dynii.doplyshop.common.util.HttpUtils;
import com.dynii.doplyshop.member.entity.Member;
import com.dynii.doplyshop.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionAccountHelper implements AccountHelper {

    private final MemberService memberService;

    @Override
    public void join(AccountJoinRequest joinReq) {
        memberService.save(joinReq.getName(), joinReq.getLoginId(), joinReq.getLoginPw());
    }

    @Override
    public String login(AccountLoginRequest loginReq, HttpServletRequest request, HttpServletResponse response) {
        Member member = memberService.find(loginReq.getLoginId(), loginReq.getLoginPw());
        // 회원 데이터가 없으면
        if (member == null) {
            return null;
        }
        HttpUtils.setSession(request, AccountConstants.MEMBER_ID_NAME, member.getId());
        return member.getLoginId().toString();
    }

    // 회원 아이디 조회
    @Override
    public Integer getMemberId(HttpServletRequest request) {
        Object memberId = HttpUtils.getSession(request, AccountConstants.MEMBER_ID_NAME);
        if (memberId != null) {
            return (int) memberId;
        }
        return null;
    }

    @Override
    public boolean isLoggedIn(HttpServletRequest request) {
        return getMemberId(request) != null;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpUtils.removeSession(request, AccountConstants.MEMBER_ID_NAME);
    }
}
