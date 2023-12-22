package com.jisun.aws.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/a")
    public String a(){
        return "a";
    }

    @GetMapping("/b")
    public String b(){
        return "b";
    }

    @GetMapping("/c")
    public String c(){
        return "c";
    }

}
