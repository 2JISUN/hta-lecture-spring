package com.jisun.outstargram.repository;

import com.jisun.outstargram.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    //CRUD


    @Modifying(clearAutomatically = true)
    @Query(value = "INSERT into LIKES (id, imageId, memberId, createdate)"
                    + "values (LIKES_SEQ.nextval, :imageId, :customDetailsId, sysdate)"
        , nativeQuery = true)//네이티브 쿼리 쓸 땐 날짜 자동 생성 불가능
    int 좋아요_레포지토리(@Param("imageId")int imageId, @Param("customDetailsId")int customDetailsId);


    @Modifying(clearAutomatically = true)
    @Query(value = "delete from LIKES where imageId = :imageId and memberId = :customDetailsId"
        , nativeQuery = true)
    int 좋아요취소_레포지토리(@Param("imageId")int imageId, @Param("customDetailsId")int customDetailsId);


    //js로 처리
/*    @Query(value="SELECT COUNT(*) FROM LIKES WHERE memberId = :customDetailsId"
            ,nativeQuery = true)
    int 좋아요수_레포지토리(@Param("customDetailsId") int customDetailsId);*/





}
