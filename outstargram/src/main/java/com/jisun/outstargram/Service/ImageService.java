package com.jisun.outstargram.Service;

import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.dto.ImageUploadDto;
import com.jisun.outstargram.entity.Image;
import com.jisun.outstargram.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
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
        return imageRepository.스토리리스트_레포지토리(customDetailId, pageable);
    }


}
