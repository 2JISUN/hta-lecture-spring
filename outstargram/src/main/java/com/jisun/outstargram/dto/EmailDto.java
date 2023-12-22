package com.jisun.outstargram.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    private String receiverAddress; //받는사람
    private String title; //제목
    private String content; //내용
    
}
