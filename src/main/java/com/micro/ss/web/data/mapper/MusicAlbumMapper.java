package com.micro.ss.web.data.mapper;

import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.data.model.MusicAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MusicAlbumMapper {
    int countByExample(MusicAlbumExample example);

    int deleteByExample(MusicAlbumExample example);

    int deleteByPrimaryKey(Long id);

    int insert(MusicAlbum record);

    int insertSelective(MusicAlbum record);

    List<MusicAlbum> selectByExample(MusicAlbumExample example);

    MusicAlbum selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") MusicAlbum record, @Param("example") MusicAlbumExample example);

    int updateByExample(@Param("record") MusicAlbum record, @Param("example") MusicAlbumExample example);

    int updateByPrimaryKeySelective(MusicAlbum record);

    int updateByPrimaryKey(MusicAlbum record);
}