package com.jisun.isotope.dao;

import com.jisun.isotope.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    int insertMember(MemberDto memberDto);

    MemberDto loginMember(String username);
}