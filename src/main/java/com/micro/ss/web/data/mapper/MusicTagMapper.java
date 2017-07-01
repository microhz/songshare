package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusicTag;
import com.micro.ss.web.data.model.MusicTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicTagMapper {
    int countByExample(MusicTagExample example);

    int deleteByExample(MusicTagExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusicTag record);

    int insertSelective(MusicTag record);

    List<MusicTag> selectByExample(MusicTagExample example);

    MusicTag selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusicTag record, @Param("example") MusicTagExample example);

    int updateByExample(@Param("record") MusicTag record, @Param("example") MusicTagExample example);

    int updateByPrimaryKeySelective(MusicTag record);

    int updateByPrimaryKey(MusicTag record);
}