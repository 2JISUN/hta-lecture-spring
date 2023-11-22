package com.jisun.isotope.service;

import com.jisun.isotope.dao.MemberDao;
import com.jisun.isotope.dto.CustomUserDetailsDto;
import com.jisun.isotope.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
* 이 코드는 Spring Security에서
* ❗사용자의 인증 정보❗를 가져오는
* UserDetailsService를 구현한
* CustomUserDetailService 클래스입니다.
* */

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberDao memberDao;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("로그인 하면 여기로 들어온다.==={}",userId);
        MemberDto loggedMember = memberDao.loginMember(userId);
        if(loggedMember!= null) {
            return new CustomUserDetailsDto(loggedMember);
        }
        return null;
    }
}