package com.jisun.jpa.service;

import com.jisun.jpa.entity.Board02;
import com.jisun.jpa.exception.DataNotFoundException;
import com.jisun.jpa.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Board02 insertBoard(Board02 board02){
        Board02 board = boardRepository.save(board02);
        return board;
    }


    public List<Board02> getAllBoard() {
        List<Board02> boardList = boardRepository.findAll();
        return boardList;
    }

    public Board02 getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id);
        if(board.isPresent()){
            return board.get();
        } else {
            throw new DataNotFoundException("존재하지 않는 페이지 입니다."); //예외 처리
        }
    }
}
