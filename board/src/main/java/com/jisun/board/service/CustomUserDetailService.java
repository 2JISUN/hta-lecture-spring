package com.jisun.board.service;


import com.jisun.board.dao.MemberDao;
import com.jisun.board.dto.CustomUserDetails;
import com.jisun.board.dto.JoinDto;
import com.jisun.board.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberDao memberDao;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //db에 있는지 없는지 따진다음 비밀번호는 시큐리티가 알아서 해준다.
        JoinDto loggedMember = memberDao.loginMember(userId);
        //id,email,name
        if(loggedMember!=null) {
            // dto  userna

            return new CustomUserDetails(loggedMember);
            //return null;
        }
        throw new UsernameNotFoundException("아이디 패스워드 확인해주세요.");
    }
}