package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicCommentaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicCommentaryMapper {
    int countByExample(MusicCommentaryExample example);

    int deleteByExample(MusicCommentaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusicCommentary record);

    int insertSelective(MusicCommentary record);

    List<MusicCommentary> selectByExample(MusicCommentaryExample example);

    MusicCommentary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusicCommentary record, @Param("example") MusicCommentaryExample example);

    int updateByExample(@Param("record") MusicCommentary record, @Param("example") MusicCommentaryExample example);

    int updateByPrimaryKeySelective(MusicCommentary record);

    int updateByPrimaryKey(MusicCommentary record);
    
}