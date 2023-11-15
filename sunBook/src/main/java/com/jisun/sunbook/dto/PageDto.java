package com.jisun.sunbook.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class PageDto {

    private int pageSize = 10;  // 페이지당 게시글 수
    private int blockSize = 10; // 페이지네이션블럭당 버튼의 수

    private int page = 1; //현재 페이지
    private int block = 1; //현재 블럭

    private int totalListCnt; //총 게시글 수 BY DB
    private int totalPageCnt; //총 페이지 수
    private int totalBlockCnt; //총 블럭 수

    private int startPage = 1; //블럭 시작 페이지
    private int endPage = 1; //블럭 마지막 페이지

    private int startIndex = 0; //db에서 어디서부터 select할건지 BY DB

    private int preBlock; //이전 블럭의 마지막 페이지
    private int nextBlock; //다음 블럭의 시작 페이지




    public PageDto(int page, int totalListCnt){
        setPage(page); //현재 페이지

        setTotalListCnt(totalListCnt); //총 게시글 수
        if(totalListCnt == 0) {
            setEndPage(1);
            setPage(1);
            setTotalPageCnt(1);
            return;
        }

        setTotalPageCnt( (int)Math.ceil( (double)(totalListCnt) / pageSize  ) ); //총 페이지 수
        setTotalBlockCnt( (int)Math.ceil( (double)(totalPageCnt) / blockSize ) ); // 총 블럭 수

        setBlock((int)Math.ceil( (double)(page) / blockSize )); // 현재 블럭

        setStartPage( (block-1) * blockSize + 1); //블럭 시작페이지
        setEndPage(startPage + blockSize - 1 ); //블럭 마지막페이지

        if(totalPageCnt<endPage) {
            this.endPage = totalPageCnt; //블럭 마지막페이지는 유동적임
        }

        setPreBlock((block-1) * blockSize); //이전 블럭 (클릭시 10,20,30,...)
        if (preBlock<1){
            this.preBlock =1;
        }

        setNextBlock((block * blockSize)+1); //다음 블럭 (클릭시 11,21,31,...)
        if(nextBlock>totalPageCnt){
            this.nextBlock = totalPageCnt;
        }

        setStartIndex((page-1) * pageSize);

    }

}
