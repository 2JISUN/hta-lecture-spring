package com.jisun.jpa.dto;


import com.jisun.jpa.entity.Board02;
import com.jisun.jpa.entity.Comment02;
import com.jisun.jpa.entity.Member02;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDto {

    private Integer id;
    private String subject;
    private String content;
    private LocalDateTime createDate;
    private List<Comment02> commentList;

    public static BoardDto fromEntity(Board02 board02) {
        return BoardDto.builder()
                .subject(board02.getSubject())
                .content(board02.getContent())
                .createDate(board02.getCreateDate())
                .build();
    }





}
