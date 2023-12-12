package com.jisun.outstargram.controller;


import com.jisun.outstargram.Service.MemberService;
import com.jisun.outstargram.Service.SubscribeService;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.dto.MemberProfileDto;
import com.jisun.outstargram.dto.UpdateMemberDto;
import com.jisun.outstargram.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final SubscribeService subscribeService;

    @GetMapping("/mypage/{id}")
    public String 회원정보_page(@PathVariable int id,
                               Model model,
                               @AuthenticationPrincipal CustomUserDetails customUserDetails){
        MemberProfileDto memberInfo = memberService.getProfile(id,customUserDetails.getLoggedMember().getId());
        model.addAttribute("memberInfo", memberInfo);
        return  "/member/mypage";

    }

    @GetMapping("/modify/{id}")
    public String 회원정보수정_page(@PathVariable int id,
                                     Model model,
                                     @AuthenticationPrincipal CustomUserDetails customUserDetails){
        model.addAttribute("memberInfo",customUserDetails);
        return "/member/modify"; //+ id; -> 리다이렉트 할때만 이런식으로 유효함!!!!!
    }



    @PostMapping("/modify/{id}")
    public String 회원정보수정_프로세스(@PathVariable int id,
                                      Model model,
                                      UpdateMemberDto updateMemberDto,
                                      @AuthenticationPrincipal CustomUserDetails customUserDetails){
        model.addAttribute("memberInfo",customUserDetails);
        memberService.회원정보수정_서비스(id, updateMemberDto);
        return "redirect:/member/mypage/" + id; //리다이렉트 할때만 이런식으로 유효함!!!!!
    }





}
