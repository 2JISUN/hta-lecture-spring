package com.jisun.outstargram.api;


import com.jisun.outstargram.Service.MemberService;
import com.jisun.outstargram.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @PutMapping("/api/member/{id}/profileImageUrl")
    public Map<String, Object> 프로필이미지업데이트_프로세스(@PathVariable int id,
                                                          MultipartFile profileImageUrl){ //스프링은 이미지를 저장하는 메소드가 있음~ 대단해!!
        Map<String, Object> resultMap = new HashMap<>();
        Member memberInfo =  memberService.프로필이미지업데이트_서비스(id, profileImageUrl); // 리턴 === void
        resultMap.put("isState", "OK");
        resultMap.put("memberInfo", memberInfo);

        return resultMap;
    }

}
