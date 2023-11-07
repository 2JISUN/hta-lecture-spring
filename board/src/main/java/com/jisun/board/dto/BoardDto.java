package com.jisun.board.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class BoardDto {
    private String name;
    private String title;
    private String content;
}
