package com.jisun.outstargram.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Likes {


    @Id //FK -> 필수임
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //오라클 number 전략 = sequence
    private int id;


    @ManyToOne //이미지 한장 : 좋아요 여러개
    @JoinColumn(name="imageId")
    private Image image;

    @ManyToOne //한명의 멤버 : 좋아요 여러개
    @JoinColumn(name="memberId")
    @JsonIgnoreProperties({"imageList"})
    private Member member;


    @CreatedDate
    private LocalDateTime createDate; //생성날짜

    @LastModifiedDate
    private LocalDateTime modifyDate; //수정날짜


}
