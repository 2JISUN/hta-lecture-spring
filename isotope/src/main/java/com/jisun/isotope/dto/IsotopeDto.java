package com.jisun.isotope.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    private String title;

    @NotBlank
    @Size(min=10, max=3000)
    private String description;

    private MultipartFile file;

    @Range(min = 0,max = 5)
    private Double point;

    private String category;

    private String regdate;

    private String original;

    private String renamed;


}
