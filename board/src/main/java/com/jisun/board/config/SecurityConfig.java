package com.jisun.board.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration //해당 클래스가 스프링 설정 클래스임을 나타냅니다.
@EnableWebSecurity // 스프링 시큐리티를 활성화하는데 사용됩니다.
@RequiredArgsConstructor //Lombok에서 제공하는 어노테이션으로, 생성자 인젝션을 자동으로 생성합니다.
public class SecurityConfig {

    //로그인 실패 시 처리를 담당하는 핸들러
    private final AuthenticationFailureHandler userLoginFailHandler;


    // 비밀번호 암호화에 사용
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //SecurityFilterChain은 Spring Security 필터 체인을 정의하는 역할
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                //요청에 대한 권한 설정
                .authorizeHttpRequests((auth)->auth
                        //// 특정 URL 패턴에 대한 요청을 설정 -> 모든 사용자에게 허용
                        .requestMatchers("/","/member/**","/css/**","/js/**","/images/**", "/mail/**").permitAll()
                        //그 외의 요청은 모두 인증된 사용자에게만 허용
                        .anyRequest().authenticated()
                )
                // 폼 로그인에 대한 설정
                .formLogin((auth)->auth
                        .loginPage("/member/login") // [GET] 로그인 페이지 URL
                        .usernameParameter("userId") // 사용자명 파라미터명 -> default==username
                        .loginProcessingUrl("/member/login") //[post] 로그인 처리 URL
                        .defaultSuccessUrl("/board/list") //로그인 성공 후 이동할 URL
                        .failureHandler(userLoginFailHandler) //로그인 실패 핸들러 등록
                        //.failureUrl("/member/login?error=true")
                        .permitAll()
                )
                //로그아웃에 대한 설정
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃 처리 URL
                        .logoutSuccessUrl("/board/list") //로그아웃 성공 후 이동할 URL
                        .invalidateHttpSession(true) // HTTP 세션 무효화
                )
                //CSRF(Cross-Site Request Forgery) 보안 설정
                .csrf((auth)->auth.disable() // CSRF 보안 설정 비활성화
                );
        return httpSecurity.build();
    }

}