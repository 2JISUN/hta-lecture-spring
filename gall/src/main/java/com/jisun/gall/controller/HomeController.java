package com.jisun.gall.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class HomeController {
    @GetMapping("/")
    public String index(){
        return "/";
    }
}
