package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.MusicInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicInfoMapper {
    int countByExample(MusicInfoExample example);

    int deleteByExample(MusicInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusicInfo record);

    int insertSelective(MusicInfo record);

    List<MusicInfo> selectByExample(MusicInfoExample example);

    MusicInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusicInfo record, @Param("example") MusicInfoExample example);

    int updateByExample(@Param("record") MusicInfo record, @Param("example") MusicInfoExample example);

    int updateByPrimaryKeySelective(MusicInfo record);

    int updateByPrimaryKey(MusicInfo record);
}