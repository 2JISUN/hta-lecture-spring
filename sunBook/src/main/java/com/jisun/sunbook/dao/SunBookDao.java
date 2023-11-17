package com.jisun.sunbook.dao;


import com.jisun.sunbook.dto.SunBookDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SunBookDao {
    /*write*/
    public Integer insertWriteSunBook(SunBookDto sunBookDto);

    /*modify*/
    public Integer insertModifySunBook(SunBookDto sunBookDto);




}
