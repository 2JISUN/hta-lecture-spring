package com.jisun.jpa.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity //기본키(PK)가 반드시 필요함
public class Comment02 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 2000)
    private String content;


    private LocalDateTime createDate;

    @ManyToOne  //보드하나에 댓글 여러개 달릴 수 있다
    private Board02 board02;
}
