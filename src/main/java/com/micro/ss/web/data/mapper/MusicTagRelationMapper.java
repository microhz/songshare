package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusicTagRelation;
import com.micro.ss.web.data.model.MusicTagRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicTagRelationMapper {
    int countByExample(MusicTagRelationExample example);

    int deleteByExample(MusicTagRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusicTagRelation record);

    int insertSelective(MusicTagRelation record);

    List<MusicTagRelation> selectByExample(MusicTagRelationExample example);

    MusicTagRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusicTagRelation record, @Param("example") MusicTagRelationExample example);

    int updateByExample(@Param("record") MusicTagRelation record, @Param("example") MusicTagRelationExample example);

    int updateByPrimaryKeySelective(MusicTagRelation record);

    int updateByPrimaryKey(MusicTagRelation record);
    
    List<MusicTagRelation> limitSelectByExample(MusicTagRelationExample example);
}