package com.jisun.outstargram.dto;

import com.jisun.outstargram.entity.Member;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileDto {
    private boolean pageOwner;
    private Member member;
    private int subscribeCount;
    private boolean subscribeState;
}






