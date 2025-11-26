package com.dynii.doplyshop.account.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
// 회원가입 요청 시 사용하는 DTO
public class AccountJoinRequests {
    private String loginId;
    private String loginPw;
    private String name;
}