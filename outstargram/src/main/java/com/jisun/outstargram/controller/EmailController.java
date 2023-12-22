package com.jisun.outstargram.controller;

import com.jisun.outstargram.dto.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    @GetMapping("/receive")
    public String 이메일받기_페이지(Model model,
                            @AuthenticationPrincipal CustomUserDetails customUserDetails){
        model.addAttribute("memberInfo", customUserDetails);



        return "email/receive";
    }



}
