package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.GroupTopics;
import com.micro.ss.web.data.model.GroupTopicsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupTopicsMapper {
    int countByExample(GroupTopicsExample example);

    int deleteByExample(GroupTopicsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GroupTopics record);

    int insertSelective(GroupTopics record);

    List<GroupTopics> selectByExample(GroupTopicsExample example);

    GroupTopics selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GroupTopics record, @Param("example") GroupTopicsExample example);

    int updateByExample(@Param("record") GroupTopics record, @Param("example") GroupTopicsExample example);

    int updateByPrimaryKeySelective(GroupTopics record);

    int updateByPrimaryKey(GroupTopics record);
}