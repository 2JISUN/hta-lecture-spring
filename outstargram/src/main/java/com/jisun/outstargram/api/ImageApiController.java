package com.jisun.outstargram.api;

import com.jisun.outstargram.Service.ImageService;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageApiController {

    private final ImageService imageService;


    @GetMapping("/image")
    public Map<String, Object> 스토리리스트_프로세스(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @PageableDefault(size = 3) Pageable pageable){
        Map<String, Object> resultMap = new HashMap<>();
        Page<Image> imageList = imageService.스토리리스트_서비스(customUserDetails.getLoggedMember().getId(), pageable);
        resultMap.put("imageList", imageList);
        return resultMap;
    }

}
