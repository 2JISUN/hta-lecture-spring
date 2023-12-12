package com.jisun.outstargram.Service;



import com.jisun.outstargram.entity.Subscribe;
import com.jisun.outstargram.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;

    @Transactional
    public void 구독하기_서비스(int fromMemberId, int toMemberId) {
        subscribeRepository.구독하기_레포지토리(fromMemberId, toMemberId); //내가 만든 쿼리~~~
    }

    @Transactional
    public int 구독자수_서비스(int memberId){
        return subscribeRepository.구독자수_레포지토리(memberId);
    }

    @Transactional
    public void 구독취소_서비스(int id, int urlId) {
        subscribeRepository.구독취소_레포지토리(id, urlId);
    }
}
