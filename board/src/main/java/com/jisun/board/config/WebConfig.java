package com.jisun.board.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    // application.yml의 file.path 속성 값을 주입받기 위한 필드
    @Value("${file.path}")
    private String uploadFolder;


    // 정적 리소스 매핑을 위한 ResourceHandler를 설정하는 메서드
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // "/upload/**" 경로로 들어오는 요청은 "file:///" + uploadFolder 경로로 매핑
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:///" + uploadFolder);
    }
}
