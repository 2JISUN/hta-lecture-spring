package com.jisun.isotope.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JoinDto {

    @NotBlank
    @Size(min=3,max=20)
    private String id;

    @NotBlank
    @Size(min=2,max=20)
    private String name;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,16}",
            message = "영문자 숫자 특수문자 조합해서 8자 16자 이하로 입력해주세요.")
    private String password;

    @Email
    private String email;

    private MultipartFile profile;

    private int age;

    @Past(message = "생일이 오늘보다 빠를 수 없습니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

}