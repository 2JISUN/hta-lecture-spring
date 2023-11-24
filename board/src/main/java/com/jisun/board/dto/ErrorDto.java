package com.jisun.board.dto;

import com.jisun.board.code.ErrorCode;
import lombok.*;
import org.springframework.util.MultiValueMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {
/*    int status;
    String message;*/
    private ErrorCode errorCode;
    private String errorMessage;

}