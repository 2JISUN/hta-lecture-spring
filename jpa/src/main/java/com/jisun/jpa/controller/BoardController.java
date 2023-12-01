package com.jisun.jpa.controller;

import com.jisun.jpa.dto.BoardDto;
import com.jisun.jpa.entity.Board02;
import com.jisun.jpa.repository.BoardRepository;
import com.jisun.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String index() {
        return "/index";
    }


    @GetMapping("/insert")
    public String insert() {
        return "/board/insert";
    }


    @PostMapping("/insert")
    public String insertProcess(@ModelAttribute BoardDto boardDto) {
        Board02 dbInsertBoard = Board02.builder()
                .subject(boardDto.getSubject())
                .content(boardDto.getContent())
                .createDate(boardDto.getCreateDate())
                .build();
        boardService.insertBoard(dbInsertBoard);
        return "redirect:/board/list";
    }


    @GetMapping("/list")
    public String list(Model model) {
        List<Board02> boardList = boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/board/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable int id, Model model) {
        log.info("id==={}",id);
        BoardDto board = boardService.getBoard(id);
        model.addAttribute("board",board);
        return "/board/view";
    }

}
