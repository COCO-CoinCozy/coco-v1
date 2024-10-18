package com.danghouse.cocov1.config;

import com.danghouse.handler.SignInFailureHandler;
import com.danghouse.handler.SignInSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationFailureHandler signInFailureHandler() {
        return new SignInFailureHandler();
    }

    @Bean
    public AuthenticationSuccessHandler signInSuccessHandler() {
        return new SignInSuccessHandler();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login", "/loginProcess", "/join", "/joinProcess").permitAll()
                        .requestMatchers("/css/**", "/icon/**", "/js/**", "/vendor/**", "/img/**", "/scss/**").permitAll() // CSS 파일 접근 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/menu/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated());

        //custom login
        http
                .formLogin((auth) -> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProcess")
                        .successHandler(signInSuccessHandler())
                        .failureHandler(signInFailureHandler()));

        http
                .logout((auth) -> auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/"));

        http
                .csrf((auth) -> auth.disable());

        //session
        http
                .sessionManagement((auth) -> auth
                        .invalidSessionUrl("/login"));

        //다중로그인 설정
        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1) //하나의 아이디에 대한 다중 로그인 허용 가수
                        .maxSessionsPreventsLogin(true)); // 다중 로그인 개수를 초과하였을 경우 (true -> 초과시 새로운 로그인 차단)

        //session 고정 보호
        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId());

        return http.build();
    }
}