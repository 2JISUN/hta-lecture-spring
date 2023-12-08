package com.jisun.outstargram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table_member")
@Entity //jpa 필드 생성 -> 엔티티는 생성자 필수임~~~
public class Member {
    @Id //FK -> 필수임
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오라클 number 전략
    @Column(name = "member_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = true)
    private String password;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

}
