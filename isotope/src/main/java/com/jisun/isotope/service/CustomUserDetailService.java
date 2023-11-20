package com.jisun.isotope.service;

import com.jisun.isotope.dao.MemberDao;
import com.jisun.isotope.dto.CustomUserDetails;
import com.jisun.isotope.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("로그인 하면 여기로 들어온다.==={}",userId);
        MemberDto loggedMember = memberDao.loginMember(userId);
        if(loggedMember!= null) {
            return new CustomUserDetails(loggedMember);
        }
        return null;
    }
}