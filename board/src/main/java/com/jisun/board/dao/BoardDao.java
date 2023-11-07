package com.jisun.board.dao;

import com.jisun.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


// dao가 db에 dto 입출력
@Mapper //mybatis 추가 후
public interface BoardDao {
    public List<BoardDto> selectBoardList();
}
