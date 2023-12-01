package com.jisun.jpa.dto;

import com.jisun.jpa.entity.Board02;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CommentDto {

    private Integer id;
    private String content;
    private LocalDateTime createDate;
    private Board02 board02;

}
