package com.jisun.outstargram.controller;

import com.jisun.outstargram.Service.ImageService;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.dto.ImageUploadDto;
import com.jisun.outstargram.entity.Image;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;


    @GetMapping("/story")
    public String 스토리_페이지() {
        return "image/story";
    }


    @GetMapping("/upload")
    public String 스토리업로드_페이지() {
        return "image/upload";
    }


    @PostMapping("/upload")
    public String 스토리업로드_프로세스(ImageUploadDto imageUploadDto, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        imageService.스토리업로드_서비스(imageUploadDto, customUserDetails);
        return "redirect:/member/mypage/" + customUserDetails.getLoggedMember().getId();
    }

    @GetMapping("/popular")
    public String 인기짱스토리리스트_페이지(@PageableDefault(size = 10) Pageable pageable, Model model){
        Page<Image> imageList = imageService.인기짱스토리리스트_서비스(pageable);
        model.addAttribute("imageList",imageList);
        return "image/popular";
    }


    @GetMapping("/detail/{id}")
    public String 스토리디테일_페이지(@PathVariable int id, Model model){

        Image imageInfo = imageService.스토리디테일_서비스(id);
        model.addAttribute("imageInfo",imageInfo);
        return "image/detail";
    }





}