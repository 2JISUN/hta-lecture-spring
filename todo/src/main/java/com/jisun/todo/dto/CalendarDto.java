package com.jisun.todo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class CalendarDto {
    private String id;
    private String title;
    private String start;
    private String end;
    private String backgroundColor;
    private String textColor;
    private String borderColor;
}