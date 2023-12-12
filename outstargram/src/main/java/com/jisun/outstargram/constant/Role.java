package com.jisun.outstargram.constant;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    ROLE_USER("role_user"),ROLE_ADMIN("role_admin"); //원래는 숫자로 ... 한다네욤?~~
    private final String role;

}
