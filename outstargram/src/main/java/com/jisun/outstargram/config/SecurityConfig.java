package com.jisun.outstargram.config;


import com.jisun.outstargram.Service.OAuth2DetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

/*
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder;*/

    //private final PasswordEncoderConfig passwordEncoderConfig; //BCry 암호화  -> 충돌나서 쪼갬~~~


    private final OAuth2DetailsService oAuth2DetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/","/auth/join","/auth/login","/css/**","/js/**","/images/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(auth->auth
                        .loginPage("/auth/login") // get
                        .loginProcessingUrl("/auth/login") // post
                        .defaultSuccessUrl("/",true)
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(auth->auth
                        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                )
                .oauth2Login((ouath2Login) -> ouath2Login
                        .loginPage("/auth/login")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuth2DetailsService)
                        )
                )
                .csrf((csrf) -> csrf.disable());
                return httpSecurity.build();
    }
}
