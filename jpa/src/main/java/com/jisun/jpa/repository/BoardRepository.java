package com.jisun.jpa.repository;

import com.jisun.jpa.entity.Board02;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


//dao!!!

@Repository
public interface BoardRepository extends JpaRepository<Board02,Integer> {
    // crud가 이미 존재하고 있음 -> 미쳤다 후덜덜
    // select -> find
    // insert -> save
    // update ->
    // delete ->

}
