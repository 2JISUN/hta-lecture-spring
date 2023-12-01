package com.jisun.jpa.service;

import com.jisun.jpa.dto.BoardDto;
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

    // 얘가 db에 왔다 갔다 하는 애....
    private final BoardRepository boardRepository;

    public Board02 insertBoard(Board02 board02){
        Board02 board = boardRepository.save(board02);
        return board;
    }

    public List<Board02> getAllBoard() {
        List<Board02> boardList = boardRepository.findAll();
        return boardList;
    }

    // Optional
    public BoardDto getBoard(int id) {
        Optional<Board02> board = boardRepository.findById(id);
        if(board.isPresent()) {
            return BoardDto.fromEntity(board.get());
        }
        throw new DataNotFoundException("찾는 거 없음");
        //return null;
    }
}
