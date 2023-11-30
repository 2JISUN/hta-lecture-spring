package com.jisun.jpa.repository;

import com.jisun.jpa.entity.Member02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;





@Repository
public interface MemberRepository extends JpaRepository<Member02, Integer> {

/*
기본적인 쿼리는 존재하지만
복잡한 쿼리같은 경우에 아래처럼 만들어주어야함~

 */
    List<Member02> findByNickName(String nickName);

    List<Member02> findByNickNameOrUserId(String nickName, String userId);

    List<Member02> findByAgeGreaterThanOrderByAgeDesc(int age);
}
