package com.jisun.board.dao;

import com.jisun.board.dto.JoinDto;
import com.jisun.board.dto.LoginDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int insertMember(JoinDto joinDto);
    JoinDto loginMember(String username);
    int deleteMember(LoginDto loginDto);
    int updateMember(JoinDto joinDto);
    int insertDeleteMember(JoinDto joinDto);
}