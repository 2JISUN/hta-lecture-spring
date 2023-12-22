package com.jisun.outstargram.api;


import com.jisun.outstargram.Service.EmailService;
import com.jisun.outstargram.dto.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EmailApiController {
    private final EmailService emailService;

    @PostMapping("/email/receive")
    public Map<String, Object> 이메일받기_프로세스(@RequestBody EmailDto emailDto){  //receiver
        emailService.이메일받기_서비스(emailDto.getReceiver());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isState", "OK");
        return resultMap;
    }
}
