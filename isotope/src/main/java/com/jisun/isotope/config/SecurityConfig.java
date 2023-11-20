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

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    // 여기에 시쿠리티가 적용될 사항들 설정....
    /*@Autowired
    private CustomUserDetailService customUserDetailService;*/

    private final CustomUserDetailService customUserDetailService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //람다식으로 작성....
        httpSecurity.authorizeHttpRequests((auth)-> auth
                        .requestMatchers("/","/member/**","/css/**","/js/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin((auth)-> auth
                        .loginPage("/member/login")
                        .usernameParameter("userId")
                        .loginProcessingUrl("/member/login")
                        .defaultSuccessUrl("/isotope/main")
                        .permitAll()
                )
                .logout((auth)->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .rememberMe((auth)->auth
                        .rememberMeParameter("saveMe")
                        .key(UUID.randomUUID().toString())
                        .userDetailsService(customUserDetailService)
                        .tokenValiditySeconds(60*24*30)
                )
                .csrf((auth)->auth.disable());
        return httpSecurity.build();
    }
}