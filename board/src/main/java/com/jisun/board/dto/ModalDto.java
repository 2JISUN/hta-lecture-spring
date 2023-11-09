package com.jisun.board.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ModalDto {
    private String isState;
    private String title;
    private String msg;

}
