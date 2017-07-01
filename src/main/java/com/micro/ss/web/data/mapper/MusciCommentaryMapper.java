package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusciCommentary;
import com.micro.ss.web.data.model.MusciCommentaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusciCommentaryMapper {
    int countByExample(MusciCommentaryExample example);

    int deleteByExample(MusciCommentaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusciCommentary record);

    int insertSelective(MusciCommentary record);

    List<MusciCommentary> selectByExample(MusciCommentaryExample example);

    MusciCommentary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusciCommentary record, @Param("example") MusciCommentaryExample example);

    int updateByExample(@Param("record") MusciCommentary record, @Param("example") MusciCommentaryExample example);

    int updateByPrimaryKeySelective(MusciCommentary record);

    int updateByPrimaryKey(MusciCommentary record);
}