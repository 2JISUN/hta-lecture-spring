package com.jisun.sunbook.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class SunBookDto {
    private int id;
    private String writer;
    private String password;
    private int relationship;
    private String title;
    private String content;
    private Date regdate;


}
