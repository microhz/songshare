package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.UserListenRecord;
import com.micro.ss.web.data.model.UserListenRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserListenRecordMapper {
    int countByExample(UserListenRecordExample example);

    int deleteByExample(UserListenRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserListenRecord record);

    int insertSelective(UserListenRecord record);

    List<UserListenRecord> selectByExample(UserListenRecordExample example);

    UserListenRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserListenRecord record, @Param("example") UserListenRecordExample example);

    int updateByExample(@Param("record") UserListenRecord record, @Param("example") UserListenRecordExample example);

    int updateByPrimaryKeySelective(UserListenRecord record);

    int updateByPrimaryKey(UserListenRecord record);
}