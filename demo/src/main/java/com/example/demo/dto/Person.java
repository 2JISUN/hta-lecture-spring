package com.example.demo.dto;

import lombok.*;

@Setter//lombok 설치 후
@Getter//lombok 설치 후
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    private int age;
    private String name;
    private String tel;
    private String address;

}
