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

    //@Modifying(clearAutomatically = true)
    @Query(value = "SELECT * FROM IMAGE WHERE MEMBER_ID in (SELECT TOMEMBERID FROM SUBSCRIBE WHERE FROMMEMBERID = :customUserDetailsId)",
            nativeQuery = true)
    Page<Image> 스토리리스트_레포지토리(@Param("customUserDetailsId") int customUserDetailsId, Pageable pageable);



}
