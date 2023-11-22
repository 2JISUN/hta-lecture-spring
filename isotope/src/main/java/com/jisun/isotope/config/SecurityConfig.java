package com.jisun.isotope.config;

import com.jisun.isotope.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/*
* 이 코드는 Spring Security를 사용하여
* 웹 애플리케이션의 ❗보안 설정❗을 구성하는
* SecurityConfig 클래스입니다.
* */

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // 여기에 시쿠리티가 적용될 사항들 설정....

    private final CustomUserDetailService customUserDetailService;

    //사용자의 비밀번호를 해싱하기 위한 암호화 방식 중 하나
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //HttpSecurity를 사용하여 보안 설정을 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //람다식으로 작성....
        httpSecurity
                //요청에 대한 권한을 설정
                .authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/","/member/**","/css/**","/js/**").permitAll() //인증 없이 접근 가능
                        .anyRequest().authenticated() //그 외의 모든 요청은 인증이 필요
                )
                //폼 로그인 구성을 설정
                .formLogin((auth)-> auth
                        .loginPage("/member/login") //로그인 페이지의 경로를 설정
                        .usernameParameter("userId") //로그인 폼에서 사용자 이름을 받는 파라미터의 이름을 설정 (디폴트: username)
                        .loginProcessingUrl("/member/login") // 실제 로그인을 처리하는 URL을 설정합니다.
                        .defaultSuccessUrl("/isotope/main") //로그인 성공 시 이동할 기본 URL을 설정
                        .permitAll() //로그인 페이지는 인증 없이 접근 가능합니다.
                )
                //로그아웃 구성을 설정
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃을 처리하는 URL을 설정
                        .logoutSuccessUrl("/") //로그아웃 성공 시 이동할 URL을 설정
                        .invalidateHttpSession(true) //HTTP 세션을 무효화
                )
                //"기억하기" 기능을 설정
                .rememberMe((auth)->auth
                        .rememberMeParameter("saveMe") //"기억하기" 체크 상자의 파라미터 이름을 설정
                        .key(UUID.randomUUID().toString()) //Remember-me 기능에 대한 키를 생성
                        .userDetailsService(customUserDetailService) //⭐사용자 정보를 제공하는 UserDetailsService를 설정
                        .tokenValiditySeconds(60*24*30) //Remember-me 토큰의 유효 기간을 설정
                )
                //Cross-Site Request Forgery 보호를 비활성화함
                .csrf((auth)->auth.disable());
        return httpSecurity.build();
    }
}