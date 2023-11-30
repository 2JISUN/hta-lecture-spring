package com.jisun.jpa.service;

import com.jisun.jpa.dto.MemberDto;
import com.jisun.jpa.entity.Member02;
import com.jisun.jpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDto join(MemberDto memberDto) {
        Member02 dbJoinMember = Member02.builder()
                .userId(memberDto.getUserId())
                .email(memberDto.getEmail())
                .nickName(memberDto.getNickName())
                .age(memberDto.getAge())
                .build();
        Member02 responseMember = memberRepository.save(dbJoinMember);
        MemberDto responseMemberDto = MemberDto.fromEntity(responseMember);
        return responseMemberDto;
    }

    public List<MemberDto> getAllMember() {
        // stream과 lamda를 쓰면 이걸 간단히 줄일수 있다....
        List<Member02> member02List = memberRepository.findAll();
        List<MemberDto> memberList = new ArrayList<>();
        for(int i=0;i<member02List.size();i++) {
            memberList.add(MemberDto.fromEntity(member02List.get(i)));
        }
        // 반목문 돌려서 dto memberList에 담기...
        return  memberList;
    }
}