package com.jisun.board.dao;

import com.jisun.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


// dao가 db에 dto 입출력
@Mapper //mybatis 추가 후 -> bean으로 등록됨
public interface BoardDao {


    //List+search
    public List<BoardDto> selectBoardList(Map<String, Object> hashMap);

    //View
    public BoardDto selectBoardView(Integer id);

    //Write
    Integer insertBoardWrite(BoardDto boardDto);

    //Modify
    Integer updateBoardModify(BoardDto boardDto);


    //Delete
    int deleteBoard(Integer id);
}
