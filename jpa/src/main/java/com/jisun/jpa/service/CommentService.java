package com.jisun.jpa.service;

import com.jisun.jpa.dto.BoardDto;
import com.jisun.jpa.dto.CommentDto;
import com.jisun.jpa.entity.Board02;
import com.jisun.jpa.entity.Comment02;
import com.jisun.jpa.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void insertComment(Board02 board02, String content) {
        Comment02 comment = Comment02.builder()
                .content(content)
                .createDate(LocalDateTime.now())
                .board02(board02)
                .build();
        commentRepository.save(comment);


    }
}
