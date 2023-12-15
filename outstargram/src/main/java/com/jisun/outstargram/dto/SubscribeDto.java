package com.jisun.outstargram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeDto {
    private Integer id;
    private String profileImageUrl;
    private String name;
    private Character subscribeState;
    private Character equalState;
}