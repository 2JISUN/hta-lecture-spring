package com.jisun.isotope.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDto {

    private String  userId;
    private String  name;
    private String  password;
    private String  email;
    private int     id;
    private String  userRole;
    private String  regdate;
}
