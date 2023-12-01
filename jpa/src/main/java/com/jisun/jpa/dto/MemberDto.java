package com.jisun.jpa.dto;

import com.jisun.jpa.entity.Member02;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDto {
    private String userId;
    private String nickName;
    private String email;
    private int age;

    public static MemberDto fromEntity(Member02 member02) {
        return MemberDto.builder()
                .userId(member02.getUserId())
                .email(member02.getEmail())
                .nickName(member02.getNickName())
                .age(member02.getAge())
                .build();
    }


}