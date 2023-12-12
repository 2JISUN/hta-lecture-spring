package com.jisun.outstargram.dto;

import com.jisun.outstargram.constant.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateMemberDto {

    private int id;
    private String userId;
    private String password;
    private String name;
    private String email;
    private String mbti;
    private String description;
    private String phone;

}
