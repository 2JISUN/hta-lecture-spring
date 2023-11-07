package com.jisun.ch02.dto;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SignupDto {
    private String name;
    private String userid;
    private String email;
    private String password;

}
