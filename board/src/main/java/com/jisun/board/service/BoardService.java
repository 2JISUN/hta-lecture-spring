package com.jisun.board.service;

import com.jisun.board.dao.BoardDao;
import com.jisun.board.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
@RequiredArgsConstructor //final은 생성자 필수
public class BoardService {
    //@Autowired //생성자 없이 바로 가져오기

    private final BoardDao boardDao;


    //list + search
    public List<BoardDto> selectBoardList(String searchCategory,
                                          String searchInput){
        Map<String, Object> resultHashMap = new HashMap<>();
        resultHashMap.put("searchCategory", searchCategory);
        resultHashMap.put("searchInput", searchInput);
        List<BoardDto> boardListList = boardDao.selectBoardList(resultHashMap);
        return boardListList;
    }

    //view
    public BoardDto selectBoardView(Integer id){
        BoardDto boardViewDto = boardDao.selectBoardView(id);
        return boardViewDto;
    }


    //write
    public Integer insertBoardWrite(BoardDto boardDto){
        Integer resultInteger = boardDao.insertBoardWrite(boardDto);
        return resultInteger;
    }

    //modify
    public Integer updateBoardWrite(BoardDto boardDto){
        Integer resultInteger = boardDao.updateBoardModify(boardDto);
        return resultInteger;
    }

    //delete
    public Integer deleteBoard(Integer id){
        Integer resultInteger = boardDao.deleteBoard(id);
        return resultInteger;
    }


}
