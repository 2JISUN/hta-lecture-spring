package com.jisun.board.controller;

import com.jisun.board.dto.BoardDto;
import com.jisun.board.dto.ModalDto;
import com.jisun.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/board") //get,post 모두 인식
@RequiredArgsConstructor //final은 생성자 필수

public class BoardController {

    private final BoardService boardService;


    @GetMapping("/list") // /board/list
    public String boardList(Model model){
        List<BoardDto> boardListList = boardService.selectBoardList();
        model.addAttribute("boardListList",boardListList);
        return "/board/list"; //html
    }

    @GetMapping("/view/{id}")
    public String boardView(@PathVariable Integer id,
                            Model model){
        List<BoardDto> boardViewList = boardService.selectBoardView(id);
        model.addAttribute("boardViewList", boardViewList);
        log.info("boardView==={}",id);
        return "/board/view";
    }


    @GetMapping("/write") // /board/write
    public String boardWrite(Model model){
        model.addAttribute("boardDto", new BoardDto());
        return "/board/write";
    }

    @PostMapping("/write") // /board/write
    public String boardWriteProcess(@Valid @ModelAttribute
                                    BoardDto boardDto,
                                    BindingResult bindingResult, //오류검증
                                    Model model,
                                    RedirectAttributes redirectAttributes
                                    ){
        if(bindingResult.hasErrors()){
            log.info("에러있을유");
            model.addAttribute("boardDto",boardDto);
            return "/board/write";
        }
        int resultInt = boardService.insertBoardWrite(boardDto);
        if (resultInt>0) {
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("방명록을 남겨주어서 참 고마워^^ 잊지 않을게")
                    .msg("내가 쓴 글 보러가기")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto", modalDto);
        }

        boardService.insertBoardWrite(boardDto);
        redirectAttributes.addFlashAttribute("boardDto", boardDto  );

        return "redirect:/"; //리다이렉트 해주는 이유는? 안해주면 url 안바뀜;;;;
        }



    @GetMapping("/delete/{id}")
    @ResponseBody //페이지를 따로 생성하지 않음-> String으로 받기
    public Map<String, String> deleteBoard(@PathVariable Integer id){
        Integer resultInteger = boardService.deleteBoard(id);
        Map<String, String> resultMap = new HashMap<>();
        if(resultInteger>0){
            resultMap.put("isDelete","ok");
        } else {
            resultMap.put("isDelete","fail");
        }
        return resultMap;
    }



}

