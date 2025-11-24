package com.ssg.gallery.account.helper;

import com.ssg.gallery.account.dto.AccountJoinRequests;
import com.ssg.gallery.account.dto.AccountLoginRequests;
import com.ssg.gallery.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SessionAccountHelper implements AccountHelper {

    private final MemberService memberService;

    @Override
    public void join(AccountJoinRequests joinReq) {

    }

    @Override
    public String login(AccountLoginRequests loginReq, HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @Override
    public Integer getMemberId(AccountLoginRequests loginReq) {
        return 0;
    }

    @Override
    public boolean isLoggedIn(HttpServletRequest request) {
        return false;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {

    }
}
