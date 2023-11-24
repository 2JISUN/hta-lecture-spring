package com.jisun.board.exception;

import com.jisun.board.code.ErrorCode;
import com.jisun.board.dto.ErrorDto;
import com.jisun.board.exception.BoardException;
import com.jisun.board.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

//@RestControllerAdvice // 이게 있으면 굳이 @ResponseBody 달 필요 없음
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    //에러 집합소~
    @ExceptionHandler(BoardException.class)
    public String runtimeHandle(BoardException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getDetailMessage())
                .build();
        return "/errors/error";
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody //만약 이게 없으면 파일을 리턴해야함 -> 리스폰스바디는 ㄹㅇ 텍스트를 리턴함!!
    public ErrorDto duplicateMember(SQLException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.DUPLICATE_MEMBER)
                .errorMessage("알 수 없는 에러로 회원가입이 되지 않았습니다.")
                .build();
        return response;
    }

    /*이메일 중복 확인*/
    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public ErrorDto memberHandler(MemberException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(e.getErrorCode())
                .errorMessage(e.getMessage())
                .build();
        return response;
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseBody
    public ErrorDto notFound(UsernameNotFoundException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.NOT_FOUND)
                .errorMessage(ErrorCode.NOT_FOUND.getMessage())
                .build();
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ErrorDto anonymousException(RuntimeException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
        return response;
    }


/*    @ExceptionHandler(MemberException.class)
    @ResponseBody
    public ErrorDto badName(MemberException e) {
        ErrorDto response = ErrorDto.builder()
                .errorCode(ErrorCode.BAD_NAME)
                .errorMessage(ErrorCode.BAD_NAME.getMessage())
                .build();
        return response;
    }*/




}
