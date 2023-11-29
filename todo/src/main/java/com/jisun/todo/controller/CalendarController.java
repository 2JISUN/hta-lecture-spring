package com.jisun.todo.controller;
import com.jisun.todo.dto.CalendarDto;
import com.jisun.todo.dto.TodoDto;
import com.jisun.todo.service.CalendarService;
import com.jisun.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping({"","/"})
    public String calendar(Model model) {
        List<CalendarDto> calendarDtoList = calendarService.getAllCalendar();
        model.addAttribute("calendarDtoList",calendarDtoList);
        return "/todo/calendar";
    }


    @GetMapping("/form")
    public String calendarForm() {
        return "/todo/calendarForm";
    }

    @PostMapping("/todo")
    @ResponseBody
    public String calendarTodo(@ModelAttribute CalendarDto calendarDto) {
        log.info("calendarDto.toString()===={}",calendarDto.toString());
        CalendarDto dbInserCalendarDto = CalendarDto.builder()
                .id(calendarDto.getId())
                .start(calendarDto.getStart()+" "+calendarDto.getStartTime())
                .end(calendarDto.getEnd()+" "+calendarDto.getEndTime())
                .allDay(calendarDto.isAllDay())
                .title(calendarDto.getTitle())
                .url(calendarDto.getUrl())
                .backgroundColor(calendarDto.getBackgroundColor())
                .borderColor(calendarDto.getBackgroundColor())
                .build();
        calendarService.insertCalendar(dbInserCalendarDto);
        return "/todo/calendar";
    }

}