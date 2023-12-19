package com.jisun.outstargram.Service;


import com.jisun.outstargram.entity.Comments;
import com.jisun.outstargram.entity.Image;
import com.jisun.outstargram.entity.Member;
import com.jisun.outstargram.repository.CommentsRepository;
import com.jisun.outstargram.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Comments 댓글쓰기_서비스(String content, int imageId, int customDetailsId){
        Image image = Image.builder().id(imageId).build();   //이미지는 해당 페이지에서 존재하는 데이터이기 때문에 빌더 패턴으로 가져올 수 있다
        Member memberEntity = memberRepository.findById(customDetailsId)  //멤버는 존재하지 않는 데이터이기 때문에 빌더 패턴을 사용할 수 없음 -> 직접 찾기
                .orElseThrow(()->{
                    throw  new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
                });
/*        Optional<Member> optionalMember = memberRepository.findById(customDetailsId);
        if(!optionalMember.isEmpty()){
            memberEntity = optionalMember.get();
        }*/
        Comments comments = Comments.builder()
                .content(content)
                .image(image) //객체
                .member(memberEntity) //객체
                .build();
        return commentsRepository.save(comments); //save의 리턴값 == 엔티티!!!
    }

    @Transactional
    public void 댓글삭제_서비스(int id) {
        commentsRepository.deleteById(id);
    }

}
