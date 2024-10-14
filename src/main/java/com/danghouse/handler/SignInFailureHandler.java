package com.danghouse.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;

@Component
public class SignInFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errMsg = "Mismatched";
        if(exception instanceof InternalAuthenticationServiceException) {
            errMsg = ((InternalAuthenticationServiceException)exception).getMessage();
        }
        response.sendRedirect(request.getContextPath() + "/login?error="+ URLEncoder.encode(errMsg, "UTF-8"));
    }
}
