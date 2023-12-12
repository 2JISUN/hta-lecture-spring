package com.jisun.outstargram.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jisun.outstargram.constant.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table_member")
@Entity //jpa 필드 생성 -> 엔티티는 생성자 필수임~~~
@EntityListeners(AuditingEntityListener.class) //우리가 신경쓰지 않아도 날짜 알아서;;;; 후덜덜
public class Member {
    @Id //FK -> 필수임
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //오라클 number 전략 = sequence
    @Column(name = "member_id")
    private int id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = true)
    @JsonIgnore //???
    private String password;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String mbti;

    private String description;

    private String phone;

    @Enumerated(EnumType.STRING) //원래 0,1,2,3... 이건데 내가 바꾸는거임
    @Column(name = "role")
    private Role role;

    private String profileImageUrl;

    @CreatedDate
    private LocalDateTime createDate; //계정 생성날짜

    @LastModifiedDate
    private LocalDateTime modifyDate; //계정 수정날짜

}
