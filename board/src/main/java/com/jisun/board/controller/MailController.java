package com.jisun.board.controller;

import com.jisun.board.dto.MailDto;
import com.jisun.board.dto.UpdateDto;
import com.jisun.board.service.MailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    /*메일 페이지 url*/
    @GetMapping("/mail")
    public String mail(){
        return "/mail/mail";
    }

    /*메일 보내기  프로세스*/
    @PostMapping("/send")
    public String send(@ModelAttribute MailDto mailDto){
        mailService.sendMail(mailDto);
        return "redirect:/";
    }

    /*메일 인증 프로세스*/
    @PostMapping("/confirm")
    @ResponseBody
    public Map<String, String> send(String mail){ //⭐mail⭐:$("#email").val() 에서 mail과 동일하게 맞춰주어야함!!!!
        String randomNum = mailService.sendAuthEmail(mail);
        Map<String, String> resultMap = new HashMap<>();
        resultMap.put("confirmNumber",randomNum);
        return resultMap;
        /*
        * {
        * String        : String
        * confirmNumber : 00000
        * }
        * */
    }

    /*비밀번호 재발급*/
    @GetMapping("/find_password")
    public String find_password( ) {
        return "/mail/find_password";
    }

    @PostMapping("/find_password")
    public String findPasswordProcess(UpdateDto updateDto) {
        //비밀번호를 암호화해서 넣어둔다. update
        //int randomNum = mailService.sendAuthEmail(mail);  // 메일보내고 랜덤값 뱉어내기....
        mailService.sendMailAndChangePassword(updateDto);
        return "redirect:/member/login";
    }




}
