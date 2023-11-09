package com.jisun.board.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class BoardDto {

    private int id;

    @NotEmpty(message = "이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "제목은 필수입니다.")
    @Size(min=5, max=100, message="최소 5자 ~ 최대 100자 내로 입력해주세요")
    private String title;

    @NotEmpty(message = "내용은 필수입니다.")
    @Size(min=5, max=1000, message="최소 5자 ~ 최대 1000자 내로 입력해주세요")
    private String content;

    private String regdate;
}
