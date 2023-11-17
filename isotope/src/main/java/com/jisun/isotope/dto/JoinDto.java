package com.jisun.isotope.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class JoinDto {


    private String id;
    private String name;
    private String password;
    private String email;
    private MultipartFile profile;





}
