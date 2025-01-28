package com.practice.userservice.common.util;

import jakarta.servlet.http.HttpServletRequest;

public class ParseRequestUtil {

    public String extractUserIdFromRequest(HttpServletRequest request) {
        String userIdHeader = request.getHeader("X-Claim-sub");
        if (userIdHeader == null) {
            throw new RuntimeException("헤더에 사용자 ID가 없습니다.");
        }

        return userIdHeader;
    }
}
