package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //파일 리턴

public class ThymeleafController {

    @GetMapping("/thyme/01")
    public String thyme01(Model model){
        model.addAttribute("name", "jisun");
        model.addAttribute("age", 9999);
        model.addAttribute("address", "서울");
        return "thyme01";
    }


}
