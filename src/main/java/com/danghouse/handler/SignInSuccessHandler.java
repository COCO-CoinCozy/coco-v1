package com.danghouse.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SignInSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String email = authentication.getName(); // 로그인한 이메일
        String ipAddress = request.getRemoteAddr(); // 사용자의 IP 주소
        String userAgent = request.getHeader("User-Agent"); // 사용자 에이전트

        // 성공 로그 기록
        log.info("[{}] LOGIN SUCCESS: {} IP: {} User-Agent: {}",
                java.time.LocalDateTime.now(),
                email,
                ipAddress,
                userAgent);

        // 성공 시 리디렉션
        response.sendRedirect("/menu");
    }
}
