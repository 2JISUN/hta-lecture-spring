package com.jisun.board.dao;

import com.jisun.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


// dao가 db에 dto 입출력
@Mapper //mybatis 추가 후 -> bean으로 등록됨
public interface BoardDao {


    //List
    public List<BoardDto> selectBoardList();

    //View
    public List<BoardDto> selectBoardView(Integer id);

    //Write
    Integer insertBoardWrite(BoardDto boardDto);


    //Delete
    int deleteBoard(Integer id);
}
