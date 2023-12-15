package com.jisun.outstargram.Service;



import com.jisun.outstargram.dto.SubscribeDto;
import com.jisun.outstargram.entity.Subscribe;
import com.jisun.outstargram.repository.SubscribeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeRepository subscribeRepository;
    private final EntityManager em;

    @Transactional
    public void 구독하기_서비스(int fromMemberId, int toMemberId) {
        subscribeRepository.구독하기_레포지토리(fromMemberId, toMemberId); //내가 만든 쿼리~~~
    }

    @Transactional
    public void 구독취소_서비스(int id, int urlId) {
        subscribeRepository.구독취소_레포지토리(id, urlId);
    }



    @Transactional
    public int 구독자수_서비스(int memberId){
        return subscribeRepository.구독자수_레포지토리(memberId);
    }

    public List<SubscribeDto> 구독자리스트_서비스(int customerDetailsId, int urlId){

        String queryString =
                "SELECT tm.MEMBER_ID AS id , tm.PROFILEIMAGEURL , tm.NAME, " +
                        "NVL2((SELECT 1 FROM SUBSCRIBE WHERE FROMMEMBERID = ? AND TOMEMBERID = tm.MEMBER_ID),TO_CHAR(1),TO_CHAR(0)) " +
                        "AS subscribeState, " +
                        "NVL2((SELECT 1 FROM dual WHERE ? = tm.MEMBER_ID),TO_CHAR(1),TO_CHAR(0)) AS equalState " +
                        "FROM TABLE_MEMBER tm INNER JOIN SUBSCRIBE s " +
                        "ON tm.MEMBER_ID = s.TOMEMBERID " +
                        "WHERE s.FROMMEMBERID = ?";
        Query query = em.createNativeQuery(queryString) //import jakarta.persistence.Query;
                .setParameter(1, customerDetailsId)
                .setParameter(2, customerDetailsId)
                .setParameter(3, urlId);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<SubscribeDto> subscribeDtoList = jpaResultMapper.list(query,SubscribeDto.class);
        return subscribeDtoList;

    }


}
