package com.jisun.board.service;

import com.jisun.board.code.ErrorCode;
import com.jisun.board.dao.BoardDao;
import com.jisun.board.dto.BoardDto;
import com.jisun.board.dto.Criteria;
import com.jisun.board.exception.BoardException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    // spring container 에 등록된 bean을 주입받을때 쓴다.
    private final BoardDao boardDao;

//    public BoardService(BoardDao boardDao) {
//        this.boardDao = boardDao;
//    }

    /*public List<BoardDto> getAllBoard(String category, String searchTxt) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("category",category);
        hashMap.put("searchTxt",searchTxt);
        List<BoardDto> boardList = boardDao.getAllBoard(hashMap);
        return boardList;
    }*/

    public List<BoardDto> getAllBoard(Criteria criteria) {
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("category",category);
//        hashMap.put("searchTxt",searchTxt);
        List<BoardDto> boardList = boardDao.getAllBoard(criteria);
        return boardList;
    }

    @Transactional //오류터지면 롤백해줌~~~!!(셀렉트와는 상관없지만 업데이트,딜리트,인서트에는 완전 완전 관계있음~!!! 후덜덜~~~)
    public int insertBoard(BoardDto boardDto) {
        int result = boardDao.insertBoard(boardDto);
        if(result<=0){
            throw new BoardException(ErrorCode.INVALID_REQUEST);
        }
        return result;
    }



    public int deleteBoard(int id) {
        int result = boardDao.deleteBoard(id);
        if(result<=0){
            throw new BoardException(ErrorCode.INVALID_REQUEST);
        }

        return result;
    }



    public BoardDto getOneBoard(int id) {
        BoardDto result = boardDao.getOneBoard(id);
        return result;
    }

    public int modifyBoard(BoardDto boardDto) {
        int result = boardDao.modifyBoard(boardDto);
        return result;
    }

    public int getTotalCount(Criteria criteria) {
        int result = boardDao.getTotalCount(criteria);
        return result;
    }



//    public BoardDto getOneBoard(String name) {
//        BoardDto boardDto = boardDao.getOneBoard(name);
//        return boardDto;
//    }

}