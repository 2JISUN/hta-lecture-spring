package com.jisun.outstargram.api;

import com.jisun.outstargram.Service.CommentsService;
import com.jisun.outstargram.dto.CommentsDto;
import com.jisun.outstargram.dto.CustomUserDetails;
import com.jisun.outstargram.entity.Comments;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentsApiController {

    private final CommentsService commentsService;

    @PostMapping("/comment")
    public Map<String, Object> 댓글쓰기_프로세스 (CommentsDto commentsDto,
                                                @AuthenticationPrincipal CustomUserDetails customUserDetails){
        Comments comments = commentsService.댓글쓰기_서비스(commentsDto.getContent(), commentsDto.getImageId(), customUserDetails.getLoggedMember().getId());
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("insert", "ok");
        resultMap.put("comments", comments);
        return resultMap;
    }

    @DeleteMapping("/comment/{id}")
    public Map<String, Object> 댓글삭제_프로세스 (@PathVariable int id,
                                                @AuthenticationPrincipal CustomUserDetails customUserDetails){
        commentsService.댓글삭제_서비스(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("delete", "ok");
        return resultMap;
    }


}
