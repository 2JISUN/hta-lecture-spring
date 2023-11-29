package com.jisun.todo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CalendarDto {

    private int no;
    private String id;
    private String title;
    private String start;  // 시작일
    private String end;    // 마무리
    private boolean allDay;
    private String startTime;
    private String endTime;
    private String url; //!!!
    private String backgroundColor;
    private String textColor;
    private String borderColor;

}