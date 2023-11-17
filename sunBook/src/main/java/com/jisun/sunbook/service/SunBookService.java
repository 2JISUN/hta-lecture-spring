package com.jisun.sunbook.service;

import com.jisun.sunbook.dao.SunBookDao;
import com.jisun.sunbook.dto.SunBookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SunBookService {

    public final SunBookDao sunBookDao;



    /*write*/
    public Integer insertWriteSunBook(SunBookDto sunBookDto){
        Integer rsInteger = sunBookDao.insertWriteSunBook(sunBookDto);
        return rsInteger;
    }

    /*modify*/
    public Integer insertModifySunBook(SunBookDto sunBookDto){
        Integer rsInteger = sunBookDao.insertModifySunBook(sunBookDto);
        return rsInteger;
    }

}
