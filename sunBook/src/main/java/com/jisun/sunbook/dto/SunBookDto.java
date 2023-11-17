package com.jisun.sunbook.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class SunBookDto {
    private int id;

    @NotBlank(message = "닉네임은 필수이며, 중복이 불가능합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "관계선택은 필수입니다.")
    private Integer relationship;

    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    private String picture;
    private Date regdate;
}
