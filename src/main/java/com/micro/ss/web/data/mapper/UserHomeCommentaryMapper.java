package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.UserHomeCommentary;
import com.micro.ss.web.data.model.UserHomeCommentaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserHomeCommentaryMapper {
    int countByExample(UserHomeCommentaryExample example);

    int deleteByExample(UserHomeCommentaryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserHomeCommentary record);

    int insertSelective(UserHomeCommentary record);

    List<UserHomeCommentary> selectByExample(UserHomeCommentaryExample example);

    UserHomeCommentary selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserHomeCommentary record, @Param("example") UserHomeCommentaryExample example);

    int updateByExample(@Param("record") UserHomeCommentary record, @Param("example") UserHomeCommentaryExample example);

    int updateByPrimaryKeySelective(UserHomeCommentary record);

    int updateByPrimaryKey(UserHomeCommentary record);
}