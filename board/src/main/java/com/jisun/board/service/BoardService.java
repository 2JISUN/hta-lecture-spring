package com.jisun.board.service;

import com.jisun.board.dao.BoardDao;
import com.jisun.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor //final은 생성자 필수
public class BoardService {
    //@Autowired //생성자 없이 바로 가져오기

    private final BoardDao boardDao;


    public List<BoardDto> selectBoardList(){
        List<BoardDto> boardListList = boardDao.selectBoardList();
        return boardListList;
    }

    public List<BoardDto> selectBoardView(Integer id){
        List<BoardDto> boardViewList = boardDao.selectBoardView(id);
        return boardViewList;
    }


    public Integer insertBoardWrite(BoardDto boardDto){
        Integer resultInteger = boardDao.insertBoardWrite(boardDto);
        return resultInteger;
    }

    public Integer deleteBoard(Integer id){
        Integer resultInteger = boardDao.deleteBoard(id);
        return resultInteger;
    }


}
