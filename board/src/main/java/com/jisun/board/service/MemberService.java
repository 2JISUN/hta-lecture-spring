package com.jisun.board.service;

import com.jisun.board.code.ErrorCode;
import com.jisun.board.dao.MemberDao;
import com.jisun.board.dto.JoinDto;
import com.jisun.board.dto.LoginDto;
import com.jisun.board.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
@RequiredArgsConstructor
@Slf4j

public class MemberService {
    /*Bean ???*/
    private final MemberDao memberDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*회원가입*/
    public int insertMember(@ModelAttribute JoinDto joinDto) {
        //회원가입시 이름에 욕설 사용 금지
        if(joinDto.getName().contains("개새")) {
            throw new MemberException(ErrorCode.BAD_NAME, "욕설은 적지 마시기를..");
        }
        //회원가입시 이메일 중복 불가능 처리
        if(memberDao.duplicateEmail(joinDto.getEmail())>0){
            throw new MemberException(ErrorCode.DUPLICATE_EMAIL);
        }

        JoinDto insertJoinDto = JoinDto.builder()
                .userId(joinDto.getUserId())
                .password(bCryptPasswordEncoder.encode(joinDto.getPassword()))
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .build();
        int result = memberDao.insertMember(insertJoinDto);
        if(result<=0){
            throw new MemberException(ErrorCode.DUPLICATE_MEMBER);
        }
        return result;
    }


    /*회원탈퇴*/
    @Transactional
    public  int deleteMember(@ModelAttribute LoginDto loginDto) {
        int result = 0;
        JoinDto dbLoginDto = memberDao.loginMember(loginDto.getUserId());
        // 정보를 다 얻어 올 수 있다.
        if(bCryptPasswordEncoder.matches(loginDto.getPassword(),dbLoginDto.getPassword())){
            log.info("비밀번호 같다.");
            String state = null;
            result = memberDao.deleteMember(loginDto);
            /*if(state==null) {
                throw new RuntimeException("에러남");
            }*/
            memberDao.insertDeleteMember(dbLoginDto);
        }
        return result;
    }

    /*비밀번호 재발급*/
    @Transactional
    public int updateMember(@ModelAttribute JoinDto joinDto) {
        int result = 0;
        JoinDto dbLoginDto = memberDao.loginMember(joinDto.getUserId());
        // 정보를 다 얻어 올 수 있다.
        if(bCryptPasswordEncoder.matches(joinDto.getPassword(),dbLoginDto.getPassword())){
            result = memberDao.updateMember(joinDto);
        }
        return result;
    }





}