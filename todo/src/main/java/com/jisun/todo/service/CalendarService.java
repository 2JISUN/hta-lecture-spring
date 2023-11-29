package com.jisun.todo.service;

import com.jisun.todo.dao.CalendarDao;
import com.jisun.todo.dao.TodoDao;
import com.jisun.todo.dto.CalendarDto;
import com.jisun.todo.dto.TodoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {
    private final CalendarDao calendarDao;

    @Transactional
    public int insertCalendar(CalendarDto calendarDto) {
        int result  = calendarDao.insertCalendar(calendarDto);
        return result;
    }

    @Transactional
    public List<CalendarDto> getAllCalendar() {
        List<CalendarDto> calendarDtoList  = calendarDao.getAllCalendar();
        return calendarDtoList;
    }
}