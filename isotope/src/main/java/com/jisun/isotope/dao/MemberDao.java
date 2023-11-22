package com.jisun.isotope.dao;

import com.jisun.isotope.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;


/*
* ❗MyBatis Mapper❗로 사용되는
* DAO(Data Access Object) 인터페이스입니다.
* */


@Mapper //인터페이스가 MyBatis의 Mapper임을 나타냅니다.
public interface MemberDao {

    /*회원가입*/
    int insertMember(MemberDto memberDto);

    /*로그인*/
    MemberDto loginMember(String username);
}