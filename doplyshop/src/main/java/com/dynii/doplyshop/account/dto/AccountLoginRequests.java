package com.dynii.doplyshop.account.dto;

import lombok.Getter;

// 로그인 요청 시 사용하는 DTO
@Getter
public class AccountLoginRequests {
    private String loginId;
    private String loginPw;
}
