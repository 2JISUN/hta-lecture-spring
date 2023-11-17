package com.jisun.isotope.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class IsotopeDto {
    private int id;

    @NotBlank(message="제목은 필수 입력사항입니다.")
    private String title;

    @NotBlank(message="내용은 필수 입력사항입니다.")
    @Size(min=10, max=3000, message = "최소 5글자 이상 쓰셔야 합니다")
    private String description;

    private MultipartFile file;

    private Double point;

    private String category;

    private String regdate;

    private String original;

    private String renamed;

}
