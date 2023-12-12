package com.jisun.outstargram.controller;


import com.jisun.outstargram.Service.MemberService;
import com.jisun.outstargram.dto.JoinDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;


    @GetMapping("/join") // url = /auth/join
    public String 회원가입_페이지(Model model) {
        model.addAttribute("joinDto", new JoinDto()); //유효성검사를 위한.. 없으면 안됩니다~~
        return "/auth/join";
    }


    @PostMapping("/join") // url = /auth/join
    public String 회원가입_프로세스(@Valid @ModelAttribute JoinDto joinDto, //유효성검사를 위한.. 없으면 안됩니다~~
                           BindingResult bindingResult,
                           Model model) {
        if(bindingResult.hasErrors()){
            model.addAttribute("joinDto", joinDto); //유효성검사를 위한.. 없으면 안됩니다~~
            return "/auth/join";
        }
        memberService.회원가입_서비스(joinDto);
        return "redirect:/auth/login";
    }



    @GetMapping("/login") // url = /auth/login
    public String 로그인_페이지(Model model) {
        model.addAttribute("joinDto", new JoinDto()); //없으면 안됩니다~~
        return "/auth/login";
    }



}
