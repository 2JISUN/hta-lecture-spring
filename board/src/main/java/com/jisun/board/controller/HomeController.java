package com.jisun.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String index(@ModelAttribute("name") String name, //redirect로 받아온 값
                        Model model){
        model.addAttribute("name", name);
        log.info("addFlashAttributes로 보낸==={}", name);
        return "index";
    }





}
