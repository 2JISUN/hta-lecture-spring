package com.jisun.board.dao;

import com.jisun.board.dto.IsotopeDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IsotopeDao {
    int insertIsotope(IsotopeDto isotopeDto);
    List<IsotopeDto> getAllList();
}

