package com.jisun.todo.controller;

import com.jisun.todo.dto.CalendarDto;
import com.jisun.todo.dto.TodoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/calendar")
public class CalendarController {
    @GetMapping("/")
    public String calendar(Model model) {
        List<CalendarDto> todo = new ArrayList<>();
        todo.add(TodoDto.builder().id("aaa").start("2023-11-28").title("밥먹고 합시다.").build());
        todo.add(TodoDto.builder().id("bbb").start("2023-11-29").title("밥먹고 합시다.").build());
        todo.add(TodoDto.builder().id("ccc").start("2023-11-15").title("밥먹고 합시다.").build());
        model.addAttribute("todo",todo);
        return "/todo/calendar";
    }
}
