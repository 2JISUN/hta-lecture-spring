package com.jisun.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {
    @GetMapping({"/","/index"})
    public String index() {
        return "/todo/index";
    }
}