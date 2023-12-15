package com.jisun.outstargram.controller;

import com.jisun.outstargram.Service.ImageService;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.dto.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @GetMapping("/story")
    public String 스토리_페이지() {
        return "/image/story";
    }

    @GetMapping("/upload")
    public String 스토리업로드_페이지() {
        return "/image/upload";
    }

    @PostMapping("/upload")
    public String 스토리업로드_프로세스(ImageUploadDto imageUploadDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        imageService.스토리업로드_서비스(imageUploadDto, customUserDetails);

        return "redirect:/member/mypage/"+customUserDetails.getLoggedMember().getId();
    }
}