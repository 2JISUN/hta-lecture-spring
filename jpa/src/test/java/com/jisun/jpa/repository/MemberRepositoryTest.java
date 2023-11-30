package com.jisun.jpa.repository;

import com.jisun.jpa.entity.Member02;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//from. repository
//view없이 테스트만 하고 싶을 때...ㅎㄷㄷ
//단위 테스트
//어케하는거얌;;; 신기방기 뿡뿡방기~

@SpringBootTest
@Transactional //db에 직접 넣지 않고 롤백시킴 -> 초기화.. 레전드 후덜덜
class MemberRepositoryTest {

    @Autowired //테스트코드에서는 이방법을 많이 쓴다.
    private  MemberRepository memberRepository;

    @Test
    @DisplayName("1+2는 3이닼")
    public void test(){
        int a=1;
        int b=2;
        int sum=3;
        //Assertions.assertEquals(sum, a+b);                    //import org.junit.jupiter.api.Assertions;
        //Assertions.assertThat(a+b).isNotEqualTo(5); //import org.assertj.core.api.Assertions; -> 가독성이 좋음
        Assertions.assertThat(a+b).isNotEqualTo(5);

    }
    /*
    db에 입력되는지 확인해보고싶은뎀,,,!!
    그러기 위해선 레파지토리가 필요한거지...
     */

    public void joinMember(){
        for(int i=0; i<10; i++){
            Member02 dbInsertMember =  Member02.builder()
                    .userId("jisun"+i)
                    .email("jisun"+i+"@naver.com")
                    .nickName("썬휘혈"+i)
                    .age(10+i)
                    .build();
            memberRepository.save(dbInsertMember);

        }
    }

    @Test
    @DisplayName("이름으로 조회!!!!")
    public void findByNickName(){
        joinMember();
        List<Member02> memberList = memberRepository.findByNickName("썬휘혈3");
        Assertions.assertThat(memberList.size()).isGreaterThan(0);
        //Assertions.assertEquals(1, memberList.size());
/*        for (Member02 item: memberList){
            System.out.println(item.toString());
        }*/
    }

    @Test
    @DisplayName("별명과 아이디 조회")
    public void findByNickNameOrIdTest(){
        joinMember();
        List<Member02> memberList = memberRepository.findByNickNameOrUserId("썬휘혈2","jisun5");
        //Assertions.assertEquals(4, memberList.size());
        for (Member02 item: memberList){
            System.out.println(item.toString());
        }

    }

    @Test
    @DisplayName("15살보다 많은 사람 찾기")
    public void findByAgeGreaterThanTest(){
        joinMember();
        List<Member02> memberList = memberRepository.findByAgeGreaterThanOrderByAgeDesc(15);
        //Assertions.assertEquals(4, memberList.size());
        for (Member02 member: memberList){
            System.out.println(member.toString());
        }

    }

}