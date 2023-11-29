package com.jisun.todo.dao;

import com.jisun.todo.dto.CalendarDto;
import com.jisun.todo.dto.TodoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarDao {
    int insertCalendar(CalendarDto CalendarDto);
    List<CalendarDto> getAllCalendar();
}