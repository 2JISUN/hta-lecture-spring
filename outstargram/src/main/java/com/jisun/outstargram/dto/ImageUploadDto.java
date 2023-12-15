package com.jisun.outstargram.dto;

import com.jisun.outstargram.entity.Image;
import com.jisun.outstargram.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class ImageUploadDto {
    private MultipartFile file;
    private String caption;

    //save는 엔티티가 하는것임~~ dto를 빌더패턴을 사용해서 엔티티에 맵핑시켜줌
    public Image toEntity(String imgUrl, Member member){
        return  Image.builder()
                    .caption(caption)
                    .imgUrl(imgUrl)
                    .member(member)
                    .build();
    }


}
