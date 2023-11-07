package com.jisun.json;

import com.jisun.json.dto.MemberDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HttpBodyController {
    // 데이터 전달 방법
    // get/post/put/patch

    // 데이터 전달 형식 httpbody

    @PostMapping("/body01")
    public String xwwwformurlencoded(String name){
        log.info("클라이언트에서 넘어온 name==={}", name );
        return "[json] httpbody 전송방식 중 하나인 key:value";
    }

    @PostMapping("/body02")
    public String textplain(@RequestBody String data){
        log.info("클라이언트에서 넘어온 name==={}", data );
        return "textplain";
    }

    @PostMapping("/body03")
    public String applicationjson(@RequestBody String data){
        log.info("클라이언트에서 넘어온 name==={}", data );
        return "application/json";
    }

    @PostMapping("/body04")
    public String applicationToObject(@RequestBody MemberDto memberDto){
        log.info("클라이언트에서 넘어온 data==={}", memberDto );
        log.info("memberDto==={}",memberDto);
        log.info("memberDto.getName()==={}",memberDto.getName());
        log.info("memberDto.getAge()==={}",memberDto.getAge());
        return "application/json";
    }



}
