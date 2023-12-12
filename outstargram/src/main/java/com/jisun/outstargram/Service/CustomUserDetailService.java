package com.jisun.outstargram.Service;

import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.entity.Member;
import com.jisun.outstargram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> loggedMember = memberRepository.findByUserId(userId);
        if(loggedMember.isPresent()){
            return new CustomUserDetails(loggedMember.get());
        }
        throw new UsernameNotFoundException("해당 아이디를 찾을 수 없습니다.");
    }
}
