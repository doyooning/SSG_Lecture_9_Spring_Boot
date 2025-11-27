package com.ssg.gallery.account.controller;

import com.ssg.gallery.account.dto.AccountJoinRequest;
import com.ssg.gallery.account.dto.AccountLoginRequest;
import com.ssg.gallery.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor()
@RequestMapping("v1")
public class AccountController {
    private final AccountHelper accountHelper;

    @PostMapping("/api/account/join")
    // 회원가입
    public ResponseEntity<?> join(@RequestBody AccountJoinRequest joinReq) {
        // 입력값이 비어있다면
        if (joinReq.getName() == null || joinReq.getLoginId() == null || joinReq.getLoginPw() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        accountHelper.join(joinReq);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/api/account/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response, @RequestBody AccountLoginRequest loginReq) {
        if(loginReq.getLoginId() == null || loginReq.getLoginPw() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String output = accountHelper.login(loginReq, request, response);
        if (output == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/api/account/check")
    public ResponseEntity<?> check(HttpServletRequest request) {
        return new ResponseEntity<>(accountHelper.isLoggedIn(request), HttpStatus.OK);
    }

    @PostMapping("/api/account/logout")
    public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
        accountHelper.logout(request, response);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
