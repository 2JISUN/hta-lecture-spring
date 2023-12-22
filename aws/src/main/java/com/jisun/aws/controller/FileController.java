package com.jisun.aws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
public class FileController {


    @GetMapping("/a")
    public String a(){
        return "a"; //html 파일을 찾을 때 앞에 "/"(root)를 지워주어야 aws가 "타임리프"를 인식할 수 있다
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
