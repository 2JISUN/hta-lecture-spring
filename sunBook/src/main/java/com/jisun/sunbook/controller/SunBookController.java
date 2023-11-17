package com.jisun.sunbook.controller;

import com.jisun.sunbook.dto.ModalDto;
import com.jisun.sunbook.dto.SunBookDto;
import com.jisun.sunbook.service.SunBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor //final은 생성자 필수
public class SunBookController {



    private final SunBookService sunBookService;



    @GetMapping("/sunbook/write")
    public String writeGet(//@ModelAttribute("relationshipList")
                           Model model){
        /*유효성 검사를 위한 Dto 데이터*/
        model.addAttribute("sunBookDto", new SunBookDto());

        /*관계 선택을 위한 데이터*/
        //List<Integer> relationshipList = new ArrayList<>();

        return "/sunbook/write";
    }




    @PostMapping("/sunbook/write")
    public String writePost(@Valid
                            @ModelAttribute

                            SunBookDto sunBookDto,
                            BindingResult bindingResult, // 오류검증
                            Model model,
                            RedirectAttributes redirectAttributes
                            ){

        /*오류검증*/
        if(bindingResult.hasErrors()){
            log.info("입력안한듯~~~");
            model.addAttribute("sunBookDto", sunBookDto);
            return "/sunbook/write";
        }



        /*쿼리의 파라미터값 전달*/
        Integer rsInteger = sunBookService.insertWriteSunBook(sunBookDto);

        /*값이 들어왔다면 모달창 띄우기*/
        if(rsInteger>0){
            ModalDto modalDto = ModalDto.builder()
                    .isState("success")
                    .titleMsg("방명록 써준사람 ZZang😎").contentMsg("방금 쓴 글 보고싶으면 확인 버튼을 클릭해줘!")
                    .btnYes("확인").btnNo("닫기")
                    .build();
            redirectAttributes.addFlashAttribute("modalDto", modalDto);
        }

        /**/
        redirectAttributes.addFlashAttribute("sunBookDto", sunBookDto);

        return "redirect:/sunbook/view";
    }





    @GetMapping("/sunbook/modify")
    public String modifyGet(){
        return "/sunbook/modify";
    }




    @PostMapping("/sunbook/modify")
    public String modifyPost(){


        return "/sunbook/modify";
    }



    @GetMapping("/sunbook/list")
    public String list(){
        return "/sunbook/list";
    }


    @GetMapping("/sunbook/view")
    public String view(){
        return "/sunbook/view";
    }




}
