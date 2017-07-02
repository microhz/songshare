package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.UserMusicScore;
import com.micro.ss.web.data.model.UserMusicScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMusicScoreMapper {
    int countByExample(UserMusicScoreExample example);

    int deleteByExample(UserMusicScoreExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserMusicScore record);

    int insertSelective(UserMusicScore record);

    List<UserMusicScore> selectByExample(UserMusicScoreExample example);

    UserMusicScore selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserMusicScore record, @Param("example") UserMusicScoreExample example);

    int updateByExample(@Param("record") UserMusicScore record, @Param("example") UserMusicScoreExample example);

    int updateByPrimaryKeySelective(UserMusicScore record);

    int updateByPrimaryKey(UserMusicScore record);
}