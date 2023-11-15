package com.jisun.sunbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor //final은 생성자 필수
public class SunBookController {


    @GetMapping("/sunbook/list")
    public String list(){
        return "/sunbook/list";
    }



    @GetMapping("/sunbook/write")
    public String write(){
        return "/sunbook/write";
    }



    @GetMapping("/sunbook/view")
    public String view(){
        return "/sunbook/view";
    }




    @GetMapping("/sunbook/modify")
    public String modify(){
        return "/sunbook/modify";
    }




}
