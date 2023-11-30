package com.jisun.jpa.controller;

import com.jisun.jpa.entity.Board02;
import com.jisun.jpa.repository.BoardRepository;
import com.jisun.jpa.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;
    

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "/index";
    }



    @GetMapping("/insert")
    public String insertPage(){
        return "insert";
    }
    

    @PostMapping("/insert")
    public String insert(@ModelAttribute Board02 board02){
        Board02 dbInsertBoard = Board02.builder()
                .subject(board02.getSubject())
                .content(board02.getContent())
                .build();
        boardService.insertBoard(dbInsertBoard);
        return "redirect:/list";
    }



    @GetMapping("/list")
    public String list(Model model){
        List<Board02> boardList= boardService.getAllBoard();
        model.addAttribute("boardList",boardList);
        return "/list";
    }



    @GetMapping("/view/{id}")
    //@ResponseBody
    public String view(@PathVariable int id, Model model){
        Board02 boardView = boardService.getBoard(id);
        model.addAttribute("boardView", boardView);
        return "/view";
    }


}
