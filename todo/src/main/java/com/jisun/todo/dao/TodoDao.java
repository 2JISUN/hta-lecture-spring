package com.jisun.todo.dao;

import org.apache.ibatis.annotations.Mapper;
import com.jisun.todo.dto.TodoDto;


import java.util.List;

@Mapper
public interface TodoDao {
    int insertTodo(TodoDto todoDto);
    List<TodoDto> getPickedDateTodo(TodoDto todoDto);
    int deleteTodo(TodoDto todoDto);

    int updateTodo(TodoDto todoDto);

    List<TodoDto> getDateCount();
}