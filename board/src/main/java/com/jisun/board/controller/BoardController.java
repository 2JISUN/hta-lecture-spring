package com.jisun.board.controller;

import com.jisun.board.dto.BoardDto;
import com.jisun.board.dto.Criteria;
import com.jisun.board.dto.ModalDto;
import com.jisun.board.dto.ToastDto;
import com.jisun.board.service.BoardService;
import com.jisun.board.utils.PaginationMaker;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @Value("${file.path}")
    private String uploadFolder;

    private final BoardService boardService;

    private final PaginationMaker paginationMaker;



    /*게시글 목록 : 페이지네이션, 검색*/
    @GetMapping("/list")
    public String list(Model model, @ModelAttribute Criteria criteria) {
        // 게시글 목록 조회
        List<BoardDto> boardList = boardService.getAllBoard(criteria);

        // 페이지네이션 설정
        paginationMaker.setCriteria(criteria);
        paginationMaker.setTotal(boardService.getTotalCount(criteria));

        // 모델에 데이터 추가
        model.addAttribute("boardList",boardList);
        model.addAttribute("paginationMaker",paginationMaker);
        //log.info("getCurrentPage==={}",paginationMaker.getCriteria().getCurrentPage());

        // 게시글 목록 페이지로 이동
        return  "/board/list";
    }

    /*게시글 작성*/
    @GetMapping("/write")
    public String write(Model model) {
        //model.addAttribute("title","write");
        // 게시글 작성 페이지로 이동
        BoardDto boardDto = BoardDto.builder().build();
        model.addAttribute("boardDto", boardDto);
        return  "/board/write";
    }

    @PostMapping("/write")
    public String writeProcess(@Valid @ModelAttribute BoardDto boardDto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes
    ) {
        // 게시글 작성 유효성 검사
        if(bindingResult.hasErrors()){
            model.addAttribute("boardDto", boardDto);
            log.info("boardDto=={}",boardDto.toString());
            return "/board/write";
        }

        // 게시글 저장
        int result = boardService.insertBoard(boardDto);

        // 성공적으로 저장되면 모달창 메시지 설정
        if(result>0){
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .msg("글이 입력되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
        }

        return "redirect:/";
    }


    /*게시글 상세보기*/
    @GetMapping("/view/{id}")
    public String getOneBoard(@PathVariable int id,Model model) {
        //log.info("getOneBoard==={}",id);

        // 게시글 상세 조회
        BoardDto boardDto = boardService.getOneBoard(id);

        // 모델에 데이터 추가
        model.addAttribute("boardDto",boardDto);

        // 게시글 상세보기 페이지로 이동
        return  "/board/view";
    }

    /*게시글 수정*/
    @GetMapping("/modify/{id}")
    public String modifyBoard(@PathVariable int id,Model model) {
        log.info("getOneBoard==={}",id);
        // 게시글 수정 페이지로 이동
        BoardDto boardDto = boardService.getOneBoard(id);
        model.addAttribute("boardDto",boardDto);
        return  "/board/modify";
    }

    @PostMapping("/modify")
    public String modifyProcessBoard(@Valid @ModelAttribute BoardDto boardDto,
                                     BindingResult bindingResult,
                                     Model model,
                                     @RequestParam int currentPage,
                                     @RequestParam String category,
                                     @RequestParam String searchTxt,
                                     RedirectAttributes redirectAttributes) {
        // 게시글 수정 유효성 검사
        if(bindingResult.hasErrors()) {
            model.addAttribute("boardDto", boardDto);
            return "/board/modify";
        }
        // 게시글 수정
        int result = boardService.modifyBoard(boardDto);

        // 성공적으로 수정되면 모달창 메시지 설정
        if(result>0){
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글수정")
                    .msg("글이 수정되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
        }
        // 목록 페이지로 리다이렉트
        return  "redirect:/board/list?currentPage="   + currentPage +
                                        "&category="  + category    +
                                        "&searchTxt=" + searchTxt;
    }

    /*게시글 삭제 by ajax*/
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Map<String,String> deleteBoard(@PathVariable int id) {
        log.info("ajax로 넘어언 id==={}",id);
        int result = boardService.deleteBoard(id);
        Map<String, String> resultMap = new HashMap<>();
        if(result>0){
            resultMap.put("isDelete","ok");
        } else {
            resultMap.put("isDelete", "fail");
        }
        return resultMap;
    }

    /*게시글 삭제 by form[GET]*/
    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id,
                              @RequestParam(required = false) int currentPage,
                              RedirectAttributes redirectAttributes
    ) {
        log.info("currentPage==={}",currentPage);

        // 게시글 삭제
        int result = boardService.deleteBoard(id);

        if(result>0) {
            log.info("0보다 크다");
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글삭제")
                    .msg(id+"번째 글이 삭제되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/board/list?currentPage="+currentPage;
        }
        log.info("0보다 작다");
        return "redirect:/board/list?currentPage="+currentPage;
    }

    @PostMapping("/test")
    public String test(@RequestParam int id,
                       @RequestParam(required = false) int currentPage,
                       RedirectAttributes redirectAttributes) {
        log.info("currentPage==={}",currentPage);
        int result = boardService.deleteBoard(id);

        if(result>0) {
            log.info("0보다 크다");
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .title("글삭제")
                    .msg(id+"번째 글이 삭제되었습니다.")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto",modalDto);
            return "redirect:/board/list?currentPage="+currentPage;
        }
        log.info("0보다 작다");
        return "redirect:/board/list?currentPage="+currentPage;
    }

    @PostMapping("/upload")
    @ResponseBody
    public Map<String,Object> upload(@RequestParam MultipartFile upload) {
        log.info("upload===={}",upload);
        log.info("originalFileName==={}",upload.getOriginalFilename());

        String originalFile = upload.getOriginalFilename(); // 이게 진짜 파일 이름...
        String renamedFile = null;
        String folder =  null;
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        folder = simpleDateFormat.format(now);
        File dir = new File(uploadFolder+File.separator+folder);
        if(!dir.exists()) dir.mkdirs();

        // file이름 분리하고 확장자 분리
        String fileName = originalFile.substring(0,originalFile.lastIndexOf("."));
        String ext = originalFile.substring(originalFile.lastIndexOf("."));
        simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String strNow = simpleDateFormat.format(now);
        log.info("strNow==={}",strNow);
        renamedFile = fileName+"_"+strNow+ext;
        Path imgFilePath = Paths.get(dir+File.separator+renamedFile);

        try {
            Files.write(imgFilePath,upload.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> resultMap =  new HashMap<>();
        resultMap.put("uploaded",true);
        resultMap.put("url","/upload/"+folder+"/"+renamedFile);
        return resultMap;
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handle(MethodArgumentTypeMismatchException exception){
        log.info("여기로 들어온다");
        return "/error";
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}