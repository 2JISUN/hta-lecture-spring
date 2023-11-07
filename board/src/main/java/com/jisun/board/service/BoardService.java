package com.jisun.board.service;

import com.jisun.board.dao.BoardDao;
import com.jisun.board.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class BoardService {
    @Autowired //생성자 없이 바로 가져오기
    BoardDao boardDao;

    public List<BoardDto> selectBoardList(){
        List<BoardDto> boardList = boardDao.selectBoardList();
        return boardList;
    }


}
