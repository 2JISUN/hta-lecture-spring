package com.jisun.outstargram.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class CommentsDto {

    private String content;
    //멤버Id는 세션에서 가져올 수 있다.
    private int imageId;

}
