package com.ssg.gallery.account.dto;

import lombok.Getter;

// 로그인 요청 시 사용하는 DTO
@Getter
public class AccountLoginRequest {
    private String loginId;
    private String loginPw;
}
