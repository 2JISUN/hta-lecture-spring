package com.jisun.ch02.controller;

import com.jisun.ch02.dto.SignupDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j //[롬목] 로그 찍고 싶을 때 -> log.info("name==={}",name)
public class TestController {
    @GetMapping("/")
    public String index(@RequestParam(required = false, defaultValue = "name이 ❌없는❌ index 페이지 .HTML") String name,
                        Model model //값을 setting
                        ){
        //log.info("name==={}",name);
        model.addAttribute("name",name);
        return "index"; //html로 디스패치
    }






    @GetMapping("/login")
    public String login(){

        return "login";
        ///log.info("name==={}",userid);
//        if(userid.equals("jisun") && password.equals("0109")){
//            return "redirect:/";
//        }
//        return "redirect:/login";
    }

    @RequestMapping("/login-process")
    public String loginProcess(@RequestParam(required = true) String userid,
                               @RequestParam(required = true) String password
                                ){

        if(userid.equals("jisun") && password.equals("0109")){
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }




    @GetMapping("/create")
    public String create(){
        return "create";
    }


    @PostMapping("/signup")
    @ResponseBody //json 변환
        public SignupDto signupProcess(SignupDto signupDto){
           return SignupDto.builder()
                    .name(signupDto.getName())
                    .password(signupDto.getPassword())
                    .userid(signupDto.getUserid())
                    .email(signupDto.getEmail())
                    .build();
        }
}


