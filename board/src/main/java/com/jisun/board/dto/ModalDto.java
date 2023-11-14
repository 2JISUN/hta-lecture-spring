package com.jisun.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ModalDto {
    private String isState; //성공여부 : success
    private String title; //모달제목
    private String msg; //모달메시지
    private String btnMsgClose; //버튼메시지 : 닫기, 취소, 아니오
    private String btnMsgOpen;  //버튼메시지 : 열기, 확인, 네
}
