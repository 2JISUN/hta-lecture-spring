SELECT * FROM member;
SELECT * FROM delete_member;
SELECT * FROM board;
SELECT * FROM ISOTOPE;

COMMIT;
ROLLBACK;



/*member멤버------------------------------------------------------------------------*/
DROP TABLE member;
CREATE TABLE member (
      id        NUMBER PRIMARY KEY ,
      userid    varchar2(100) NOT NULL,
      password  varchar2(100) NOT NULL,
      name      varchar2(100) NOT NULL,
      email     varchar2(1000),
      userrole  varchar2(1000),
      status 	NUMBER,
      regdate DATE
);

DROP SEQUENCE member_seq;
CREATE SEQUENCE member_seq
   INCREMENT BY 1
   START WITH 1
   MINVALUE 1
   MAXVALUE 999999;





/*탈퇴member멤버------------------------------------------------------------------------*/
DROP TABLE delete_member;
CREATE TABLE delete_member (
      id        NUMBER PRIMARY KEY,
      userid    varchar2(100) NOT NULL,
      name      varchar2(100) NOT NULL,
      regdate DATE
);



DROP SEQUENCE deletemember_seq;
CREATE SEQUENCE deletemember_seq
   INCREMENT BY 1
   START WITH 1
   MINVALUE 1
   MAXVALUE 999999;





/*board게시판------------------------------------------------------------------------*/
DROP TABLE board;
CREATE TABLE board (
	id 		NUMBER,
	name 	varchar2(100),
	title 	varchar2(100),
	content	varchar2(3000),
	regdate date
	);

DROP SEQUENCE board_seq;
CREATE SEQUENCE board_seq
	INCREMENT BY 1
	START WITH 1
	MINVALUE 1
	MAXVALUE 99999;



-- pl sql이라고 합니다.
-- 게시글 多 생성
DECLARE
 randomNum NUMBER;


BEGIN
	FOR i IN 1..100
	LOOP
		randomNum := trunc(dbms_random.value(1,100));
		INSERT INTO BOARD VALUES (
		board_seq.nextval,
		'jisun '||randomNum,
		'제목 ' ||randomNum,
		'내용 ' ||randomNum,
		sysdate
		);
	END LOOP;
END;


ROLLBACK;
    update board
    set
        name='이름수정',
        title='제목수정',
        content='내용수정'
       WHERE id=38;

SELECT * FROM board WHERE id=38;




/*페이지네이션*/
select * from (
    select rownum as num , b.* from
        (select * from board order by regdate desc) b
)
where num >= 1 and num <= 10;


select count(*) as count
        from board; /*210개*/


/*최신글순으로 10개씩 정렬하기*/
SELECT *
FROM BOARD
ORDER BY id DESC offset 0
ROWS FETCH FIRST 10
ROWS ONLY ;








/*isotope갤러리------------------------------------------------------------------------*/
DROP TABLE isotope;
CREATE TABLE isotope (
      id NUMBER PRIMARY KEY ,
      title varchar2(100) NOT NULL,
      description varchar2(1000) ,
      point NUMBER, --정렬을 위한..
      category varchar2(100),
      original varchar2(1000) NOT NULL, --원본 파일명 : 고윤정.jpg
      renamed varchar2(1000) NOT NULL, --서버에저장된 파일명 : 고윤정_20231117100550.jpg
      regdate DATE
);

DROP SEQUENCE isotope_seq;
CREATE SEQUENCE isotope_seq
   INCREMENT BY 1
   START WITH 1
   MINVALUE 1
   MAXVALUE 999999;

