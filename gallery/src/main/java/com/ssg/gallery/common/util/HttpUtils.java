package com.ssg.gallery.common.util;

import jakarta.servlet.http.HttpServletRequest;

// 공용 기능을 먼저 작업해주는 것이 좋다
// HttpUtils는 세션 처리 등 공통적으로 처리되는 작업
public class HttpUtils {

    // 세션 입력
    public static void setSession(HttpServletRequest request, String key, Object value) {
        request.getSession().setAttribute(key, value);
    }

    // 세션 값 조회
    public static Object getSession(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key);
    }

    // 세션 삭제
    public static void removeSession(HttpServletRequest request, String key) {
        request.getSession().removeAttribute(key);
    }
}
