package com.jisun.outstargram.Service;

import com.jisun.outstargram.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;

    @Transactional
    public int 좋아요_서비스(int imageId, int customDetailsId){
        int result = likesRepository.좋아요_레포지토리(imageId, customDetailsId);
        return result;
    }



}
