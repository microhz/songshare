package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.UserGroupRelation;
import com.micro.ss.web.data.model.UserGroupRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserGroupRelationMapper {
    int countByExample(UserGroupRelationExample example);

    int deleteByExample(UserGroupRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserGroupRelation record);

    int insertSelective(UserGroupRelation record);

    List<UserGroupRelation> selectByExample(UserGroupRelationExample example);

    UserGroupRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserGroupRelation record, @Param("example") UserGroupRelationExample example);

    int updateByExample(@Param("record") UserGroupRelation record, @Param("example") UserGroupRelationExample example);

    int updateByPrimaryKeySelective(UserGroupRelation record);

    int updateByPrimaryKey(UserGroupRelation record);
}