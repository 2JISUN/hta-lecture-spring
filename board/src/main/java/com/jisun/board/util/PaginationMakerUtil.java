package com.jisun.board.util;


import com.jisun.board.dto.PageDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PaginationMakerUtil {


    private PageDto pageDto; //한 페이지 내에서 게시판 글 개수 : 10

    private int totalPageNum; // 전체 페이지 수

    private int startPage; // 시작 페이지
    private int endPage;   // 끝 페이지

    private final int blockPage = 5; //페이지네이션 단위 5 : [1/2/3/4/5]

    private boolean isPrev; // 페이지네이션 이전페이지 여부
    private boolean isNext; // 페이지네이션 이후페이지 여부


    // 전체 페이지 수
    public void setTotalPageNum(int totalPageNum){
        this.totalPageNum = totalPageNum;
        makePagination();
    }



    private void makePagination(){
        //한 페이지 안에 글 목록을 10개로 셋팅하기
        endPage   = (int)Math.ceil(  (  pageDto.getStartPage() / (double)blockPage  )  ) * blockPage;
        startPage = (endPage - blockPage) + 1;
    }




}
