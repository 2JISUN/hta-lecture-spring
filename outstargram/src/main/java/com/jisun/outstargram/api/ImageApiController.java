package com.jisun.outstargram.api;

import com.jisun.outstargram.Service.ImageService;
import com.jisun.outstargram.Service.LikesService;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;



    @GetMapping("/image")
    public Map<String, Object> 스토리리스트_프로세스(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                  @PageableDefault(size = 3) Pageable pageable){
        Map<String, Object> resultMap = new HashMap<>();
        Page<Image> imageList = imageService.스토리리스트_서비스(customUserDetails.getLoggedMember().getId(), pageable);
        resultMap.put("imageList", imageList);
        return resultMap;
    }


    @PostMapping("/image/{imageId}/likes")
    public Map<String, Object> 좋아요_프로세스(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                            @PathVariable int imageId ) {
        int result = likesService.좋아요_서비스(imageId, customUserDetails.getLoggedMember().getId());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isState", "OK");
        return resultMap;
    }

}
