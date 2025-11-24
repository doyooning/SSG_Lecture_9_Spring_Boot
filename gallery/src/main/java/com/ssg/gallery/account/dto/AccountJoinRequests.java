package com.ssg.gallery.account.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
// 회원가입 요청 시 사용하는 DTO
public class AccountJoinRequests {
    private String loginId;
    private String loginPw;
    private String name;
}
