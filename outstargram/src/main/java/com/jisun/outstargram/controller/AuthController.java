package com.jisun.outstargram.controller;


import com.jisun.outstargram.dto.JoinDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/join") // url = /auth/join
    public String 회원가입_페이지(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "/auth/join";
    }



    @GetMapping("/login") // url = /auth/login
    public String 로그인_페이지(Model model) {
        model.addAttribute("joinDto", new JoinDto());
        return "/auth/login";
    }



}
