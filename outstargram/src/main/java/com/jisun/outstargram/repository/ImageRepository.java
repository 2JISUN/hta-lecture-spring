package com.jisun.outstargram.repository;

import com.jisun.outstargram.entity.Image;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    //CRUD

    //@Modifying(clearAutomatically = true) --> db수정시에만 사용함
    @Query(value = "SELECT * FROM IMAGE WHERE MEMBER_ID in (SELECT TOMEMBERID FROM SUBSCRIBE WHERE FROMMEMBERID = :customUserDetailsId)",
            nativeQuery = true)
    Page<Image> 스토리리스트_레포지토리(@Param("customUserDetailsId") int customUserDetailsId, Pageable pageable);


    //@Modifying(clearAutomatically = true) --> db수정시에만 사용함
    @Query(value = "SELECT * FROM image INNER JOIN (SELECT imageid, count(imageid) AS likeCount FROM LIKES GROUP BY imageid) c ON image.id = c.imageid ORDER BY likeCount desc"
            , nativeQuery = true)
    Page<Image> 인기짱스토리리스트_레포지토리(Pageable pageable);

}
