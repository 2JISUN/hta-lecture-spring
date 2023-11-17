package com.jisun.sunbook.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ModalDto {
    private String isState; //상태
    private String titleMsg; //제목
    private String contentMsg; //내용
    private String btnYes; //확인버튼
    private String btnNo; //취소버튼
}
