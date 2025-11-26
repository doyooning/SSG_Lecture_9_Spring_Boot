package com.ssg.gallery.common.interceptor;

import com.ssg.gallery.account.helper.AccountHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component // ① 스프링 컨테이너에서 관리하는 컴포넌트
@RequiredArgsConstructor
public class ApiInterceptor implements HandlerInterceptor {

    private final AccountHelper accountHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // ② 핸들러 인터셉터 인터페이스에서 정의한 메서드 구현. preHandle메서드 추후 구현 일단 true 리턴
        //   이렇게 처리하면 클라이언트의 요청이 이전과 동일하게 컨트롤러로 전달된다.
        // 로그인 회원 아이디가 없으면
        if (accountHelper.getMemberId(request) == null) {
            response.setStatus(401);
            return false;
        }
        return true;
    }
}
