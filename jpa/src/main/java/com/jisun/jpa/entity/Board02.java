package com.jisun.jpa.entity;

import jakarta.persistence.*;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor

@Entity //기본키(PK)가 반드시 필요함
//@Table(name = "myBoard")
public class Board02 {

    @Id //PK 명시
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //오라클 기본키 전략
    private Integer id;

    private String subject;

    @Column(length = 2000)
    private String content;
}
