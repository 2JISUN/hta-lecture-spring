package com.jisun.board.dao;

import com.jisun.board.dto.BoardDto;
import com.jisun.board.dto.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

// dao가 db에 dto 입출력

@Mapper
public interface BoardDao {
    List<BoardDto> getAllBoard(Criteria criteria);

    //@Select("Select * from board where name = #{name}")
    //BoardDto getOneBoard(@Param("name") String name);
    int insertBoard(BoardDto boardDto);
    int deleteBoard(int id);

    BoardDto getOneBoard(int id);

    int modifyBoard(BoardDto boardDto);

    int getTotalCount(Criteria criteria);
}
