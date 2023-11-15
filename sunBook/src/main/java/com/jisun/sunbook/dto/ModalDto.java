package com.jisun.sunbook.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class ModalDto {
    private String isState;
    private String titleMsg;
    private String contentMsg;
    private String btnYes;
    private String btnNo;
}
