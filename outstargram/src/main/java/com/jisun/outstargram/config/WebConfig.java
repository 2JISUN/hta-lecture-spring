package com.jisun.outstargram.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*이미지 폴더 경로를 클라이언트로 보내기 위한 설정파일 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadFolder;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadFolder);
    }


}
