package com.jisun.outstargram.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String caption;

    private String imgUrl;

    @JoinColumn(name="member_id")
    @ManyToOne
    @JsonIgnoreProperties({"imageList"})
    private Member member;

    @OneToMany(mappedBy = "image")
    @JsonIgnoreProperties({"image"})
    private List<Likes> likes;


    @OrderBy("createDate DESC")
    @OneToMany(mappedBy = "image") //Image : Comments = One : Many
    @JsonIgnoreProperties({"image"}) //무한참조 방지
    private List<Comments> commentsList;

    //좋아요 상태
    @Transient //컬럼 없이 속성을 만들 수 있음
    private boolean likeState;

    //좋아요 개수
    @Transient //컬럼 없이 속성을 만들 수 있음
    private int likeTotal;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;
}