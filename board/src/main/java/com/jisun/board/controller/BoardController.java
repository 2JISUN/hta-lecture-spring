package com.jisun.board.controller;

import com.jisun.board.dto.BoardDto;
import com.jisun.board.dto.ModalDto;
import com.jisun.board.dto.PageDto;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@Slf4j
@RequestMapping("/board") //get,post 모두 인식
@RequiredArgsConstructor //final은 생성자 필수

public class BoardController {

    private final BoardService boardService;
    //private final BoardDto boardDto;



    @GetMapping("/list") // /board/list
    public String boardList(Model model,
                            @RequestParam(required = false) String searchCategory,
                            @RequestParam(required = false) String searchInput,

                            @RequestParam(defaultValue = "1") int page){

        int totalListCnt = boardService.selectBoardCount(); //전체 글수
        PageDto pageDto = new PageDto(page,totalListCnt);//전체 글수 당 page수를 입력
        int startIndex = pageDto.getStartIndex();
        int pageSize = pageDto.getPageSize();

        List<BoardDto> boardListList = boardService.selectBoardList(searchCategory, //검색
                                                                    searchInput,    //검색
                                                                    startIndex,     //페이지네이션
                                                                    pageSize);      //페이지네이션
        model.addAttribute("boardListList", boardListList);
        model.addAttribute("pagination", pageDto);

        return "/board/list"; //html
    }





    @GetMapping("/view/{id}")
    public String boardView(@PathVariable Integer id,
                            Model model){
        log.info("boardView==={}",id);
        BoardDto boardViewDto = boardService.selectBoardView(id);
        model.addAttribute("boardViewDto", boardViewDto);
        return "/board/view";
    }



/*    @GetMapping("/view/{id}")
    @ResponseBody
    public Map<String, Object> getOneBoard(@PathVariable int id) {
        log.info("getOneBoard==={}",id);
        BoardDto boardDto = boardService.getOneBoard(id);
        Map<String, Object> resultMap = new HashMap<>();
        if(boardDto!=null){
            resultMap.put("isState","ok");
            resultMap.put("viewData",boardDto);
        } else {
            resultMap.put("isState", "fail");
            resultMap.put("viewData",null);
        }
        return  resultMap;
    }*/







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
        if(bindingResult.hasErrors()){ //th:errors="*{name}
            log.info("에러있을유");
            model.addAttribute("boardDto",boardDto);
            return "/board/write";
        }
        int resultInt = boardService.insertBoardWrite(boardDto);

        if (resultInt>0) {
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("방명록 써줘서 ㄳㄳ")
                    .msg("내가 쓴 글 보러갈까요?")
                    .btnMsgClose("싫어!!!!")
                    .btnMsgOpen("좋아🩵")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto", modalDto);
        }

        //boardService.insertBoardWrite(boardDto);
        redirectAttributes.addFlashAttribute("boardDto", boardDto  );

        return "redirect:/board/list"; //리다이렉트 해주는 이유는? 안해주면 url 안바뀜;;;;
        }


    @GetMapping("/modify/{id}")
    public String boardModify(@PathVariable Integer id,
                              Model model){
        log.info("boardView==={}",id);

        model.addAttribute("boardDto", new BoardDto());
        BoardDto boardViewDto = boardService.selectBoardView(id);
        model.addAttribute("boardViewDto", boardViewDto);
        log.info("boardViewDto==={}",boardViewDto);
        return "/board/modify";
    }





    @PostMapping("/modify")
    public String boardModifyProcess(@Valid
                                     @ModelAttribute
                                     @PathVariable Integer id,
                                     BoardDto boardDto,
                                     BindingResult bindingResult, //오류검증
                                     Model model,
                                     RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){ //th:errors="*{name}
            log.info("에러있을유");
            model.addAttribute("boardDto",boardDto);
            return "/board/modify";
        }

        Integer resultInteger = boardService.updateBoardWrite(boardDto);
        if (resultInteger>0) {
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("방명록 수정해줬구나 최고야^^ 삭제는하지마 ㅡㅡ흥")
                    .msg("내가 쓴 글 보러가기")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto", modalDto);
        }

        //boardService.insertBoardWrite(boardDto);
        redirectAttributes.addFlashAttribute("boardDto", boardDto  );

        return "redirect:/board/view"; //리다이렉트 해주는 이유는? 안해주면 url 안바뀜;;;;
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

