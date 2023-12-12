package com.jisun.outstargram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //감사(auditing) 기능을 활성화 ->  엔티티의 생성일자, 수정일자, 생성자, 수정자 등을 추적
public class OutstargramApplication {

    public static void main(String[] args) {
        SpringApplication.run(OutstargramApplication.class, args);
    }

}
