package com.jisun.board.controller;

import com.jisun.board.dto.BoardDto;
import com.jisun.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/board") //get,post 모두 인식
public class BoardController {

    @Autowired //
    BoardService boardService;


    @GetMapping("/list") // /board/list
    public String boardList(Model model){
        //List<BoardDto> boardList = boardService.selectBoardList();

        List<BoardDto> boardList = new ArrayList<>();
        boardList.add(BoardDto.builder()
                                .name("썬휘")
                                .title("나 아픈거 다 낳았어ㅜ")
                                .content("이거보여주려고 어그로 끌었다")
                                .build());
        /*데이터 저장*/
        model.addAttribute("title", "list");
        model.addAttribute("boardList", boardList);
        return "/board/list"; //html
    }


    @GetMapping("/write") // /board/write
    public String boardWrite(Model model){
        model.addAttribute("title", "write");
        return "/board/write";
    }
}

