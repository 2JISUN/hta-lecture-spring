package com.jisun.outstargram.Service;

import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.dto.ImageUploadDto;
import com.jisun.outstargram.entity.Image;
import com.jisun.outstargram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    @Value("${file.path}") //이미지 저장 경로 맵핑!!! --> import org.springframework.beans.factory.annotation.Value;
    private String uploadFolder;


    public void 스토리업로드_서비스(ImageUploadDto imageUploadDto, CustomUserDetails customUserDetails){
        String originalFileName = imageUploadDto.getFile().getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        String imageFileName = uuid+"_"+originalFileName;
        Path imageFilePath = Paths.get(uploadFolder+imageFileName);

        try {
            Files.write(imageFilePath,imageUploadDto.getFile().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image image = imageUploadDto.toEntity(imageFileName ,customUserDetails.getLoggedMember()); //이미지파일명, 유저정보
        imageRepository.save(image);
    }


    public Page<Image> 스토리리스트_서비스(int customDetailId, Pageable pageable) {
        Page<Image> images = imageRepository.스토리리스트_레포지토리(customDetailId, pageable);
        images.forEach((image) ->{
            image.setLikeTotal(image.getLikes().size());  //arraylist
            image.getLikes().forEach((like)->{
                if(like.getMember().getId()==customDetailId){
                    image.setLikeState(true);
                }
            });
        });
        return images;
    }


    public Page<Image> 인기짱스토리리스트_서비스(Pageable pageable) {
        return imageRepository.인기짱스토리리스트_레포지토리(pageable);
    }


    public Image 스토리디테일_서비스(int id) {
        Image imageInfo = imageRepository.findById(id)
                .orElseThrow(()->{
                    return new RuntimeException("해당 이미지를 찾을 수 없습니다.");
                });
        imageInfo.setLikeTotal(imageInfo.getLikes().size());
        return imageInfo;
    }
}
