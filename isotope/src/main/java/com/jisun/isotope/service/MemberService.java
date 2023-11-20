package com.jisun.isotope.service;

import com.jisun.isotope.dao.MemberDao;
import com.jisun.isotope.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberDao memberDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public int insertMember(@ModelAttribute MemberDto memberDto) {
        MemberDto inserMemberDto = MemberDto.builder()
                .userId(memberDto.getUserId())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .password( bCryptPasswordEncoder.encode( memberDto.getPassword() ))
                .build();
        int result = memberDao.insertMember(inserMemberDto);
        return result;
    }
}