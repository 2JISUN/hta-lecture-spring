package com.jisun.outstargram.repository;

import com.jisun.outstargram.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments,Integer> {
    //CRUD 의 리턴 --> int ()
    //save 의 리턴 --> entity
    //@Query 의 리턴 --> entity




}
