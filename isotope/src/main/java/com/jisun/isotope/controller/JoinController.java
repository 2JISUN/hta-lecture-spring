package com.jisun.isotope.controller;

import com.jisun.isotope.dto.JoinDto;
import com.jisun.isotope.dto.MemberDto;
import com.jisun.isotope.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequestMapping("/member")
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberDto",new MemberDto());
        return  "/join";
    }

    @PostMapping("/join")
    public String joinProcess(@ModelAttribute MemberDto memberDto) {
        int result = memberService.insertMember(memberDto);
        log.info("join=={}",result);
        return  "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {
        return  "/login";
    }
}