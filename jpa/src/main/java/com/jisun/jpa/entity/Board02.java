package com.jisun.jpa.entity;

import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "board02", cascade = CascadeType.REMOVE)
    private List<Comment02> commentList;

}
