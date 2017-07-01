package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.TopicsCommentary;
import com.micro.ss.web.data.model.TopicsCommentaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TopicsCommentaryMapper {
    int countByExample(TopicsCommentaryExample example);

    int deleteByExample(TopicsCommentaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TopicsCommentary record);

    int insertSelective(TopicsCommentary record);

    List<TopicsCommentary> selectByExample(TopicsCommentaryExample example);

    TopicsCommentary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TopicsCommentary record, @Param("example") TopicsCommentaryExample example);

    int updateByExample(@Param("record") TopicsCommentary record, @Param("example") TopicsCommentaryExample example);

    int updateByPrimaryKeySelective(TopicsCommentary record);

    int updateByPrimaryKey(TopicsCommentary record);
}