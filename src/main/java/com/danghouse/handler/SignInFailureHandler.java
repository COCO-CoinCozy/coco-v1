package com.danghouse.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@Component
public class SignInFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errMsg = "Mismatched";

        String email = request.getParameter("username"); // 로그인 시도한 이메일
        String ipAddress = request.getRemoteAddr(); // 사용자 IP 주소
        String userAgent = request.getHeader("User-Agent"); // 사용자 에이전트

        // 로그 기록
        // 추후 로그인 성공, 실패 여부 추릴예정
        log.warn("[{}] LOGIN FAILURE: {} 로그인 실패: {} IP: {} User-Agent: {}",
                java.time.LocalDateTime.now(),
                email,
                exception.getMessage(),
                ipAddress,
                userAgent);

        response.sendRedirect(request.getContextPath() + "/login?error="+ URLEncoder.encode(errMsg, "UTF-8"));
    }
}
