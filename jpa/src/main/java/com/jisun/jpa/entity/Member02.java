package com.jisun.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor //빈 생성자
@AllArgsConstructor //전체 생성자
@Builder
@ToString
@Entity
public class Member02 {

    @Id //pk 필수임ㅋ
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(length = 30)
    private String userId;

    @Column(length = 100)
    private String nickName;

    @Column(length = 30)
    private String email;

    private int age;
}
