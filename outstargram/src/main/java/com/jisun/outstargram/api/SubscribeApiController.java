package com.jisun.outstargram.api;


import com.jisun.outstargram.Service.MemberService;
import com.jisun.outstargram.Service.SubscribeService;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class SubscribeApiController {
    private final SubscribeService subscribeService;

    @PostMapping("/api/subscribe/{toMemberId}")
    //상태코드를 Map 타입으로 던질 수 있다.
    public Map<String,Object> 구독하기_프로세스(@PathVariable int toMemberId,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails){
        subscribeService.구독하기_서비스(customUserDetails.getLoggedMember().getId(), toMemberId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isSubscribe", "ok");
        return resultMap;
    }


    @DeleteMapping("/api/subscribe/{toMemberId}")
    //상태코드를 Map 타입으로 던질 수 있다.
    public Map<String,Object> 구독취소_프로세스(@PathVariable int toMemberId,
                                             @AuthenticationPrincipal CustomUserDetails customUserDetails){
        subscribeService.구독취소_서비스(customUserDetails.getLoggedMember().getId(), toMemberId);
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("isUnSubscribe", "ok");
        return resultMap;
    }

}
