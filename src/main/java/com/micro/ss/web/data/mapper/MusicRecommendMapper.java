package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusicRecommend;
import com.micro.ss.web.data.model.MusicRecommendExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicRecommendMapper {
    int countByExample(MusicRecommendExample example);

    int deleteByExample(MusicRecommendExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusicRecommend record);

    int insertSelective(MusicRecommend record);

    List<MusicRecommend> selectByExample(MusicRecommendExample example);

    MusicRecommend selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusicRecommend record, @Param("example") MusicRecommendExample example);

    int updateByExample(@Param("record") MusicRecommend record, @Param("example") MusicRecommendExample example);

    int updateByPrimaryKeySelective(MusicRecommend record);

    int updateByPrimaryKey(MusicRecommend record);
    
    List<MusicRecommend> limitSelectByExample(MusicRecommendExample example);
}