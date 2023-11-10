package com.jisun.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//한 페이지 내의 목록의 개수 = 10개
public class PageDto {
    private int boardListStart; //1  ->주어진 순간 getStartPage, getEndPage 생성됨
    private int boardListEnd;   //10


    public PageDto(){
        this.boardListStart = 1;
        this.boardListEnd = 10;
    }

    public int getStartPage(){
        return ( (boardListStart-1)*boardListEnd ) + 1;
    }

    public int getEndPage() {
        return boardListStart * boardListEnd;
    }

}
