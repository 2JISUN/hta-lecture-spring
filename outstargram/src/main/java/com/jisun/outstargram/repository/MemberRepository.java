package com.jisun.outstargram.repository;

import com.jisun.outstargram.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    //CRUD

    Optional<Member> findByUserId(String userId); // username이 아니라 userId로 내가 변경했기 때문에 반드시 써주기~

}
