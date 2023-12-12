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
@Entity //jpa 필드 생성 -> 엔티티는 생성자 필수임~~~
@EntityListeners(AuditingEntityListener.class) //우리가 신경쓰지 않아도 날짜 알아서;;;; 후덜덜
public class Subscribe {

    @Id //FK -> 필수임
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //오라클 number 전략
    private int id;


    @ManyToOne
    @JoinColumn(name="fromMemberId")
    private Member fromMember; //팔로워 (상대방이 나를 팔로우함)

    @ManyToOne
    @JoinColumn(name="toMemberId")
    private Member toMember;    //팔로우 (내가 상대방을 팔로우함)


    @CreatedDate
    private LocalDateTime createDate; //계정 생성날짜

}
