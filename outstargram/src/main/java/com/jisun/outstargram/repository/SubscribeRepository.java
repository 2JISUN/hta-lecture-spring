package com.jisun.outstargram.repository;

import com.jisun.outstargram.entity.Member;
import com.jisun.outstargram.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {
    //CRUD


    @Modifying
    @Query(value = "INSERT into subscribe (id, fromMemberId, toMemberId, createdate) values (SUBSCRIBE_SEQ.nextval, :fromMemberId, :toMemberId, sysdate)"
          ,nativeQuery = true)
    void 구독하기_레포지토리(@Param("fromMemberId") int fromMemberId, @Param("toMemberId")int toMemberId);


    @Query(value="SELECT COUNT(*) FROM SUBSCRIBE WHERE fromMemberId = :memberId"
           ,nativeQuery = true)
    int 구독자수_레포지토리(@Param("memberId") int memberId);


    @Query(value="SELECT COUNT(*) FROM SUBSCRIBE WHERE fromMemberId = :loggedMemberId and toMemberId = :urlId"
          ,nativeQuery = true)
    int 구독확인_레포지토리(@Param("loggedMemberId") int loggedMemberId,@Param("urlId") int urlId );

    @Modifying
    @Query(value = "delete from SUBSCRIBE where fromMemberId = :loggedMemberId and toMemberId = :urlId"
            ,nativeQuery = true)
    int 구독취소_레포지토리(@Param("loggedMemberId") int loggedMemberId, @Param("urlId")int urlId);


}
