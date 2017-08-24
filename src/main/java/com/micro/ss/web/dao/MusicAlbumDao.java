package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.MusicAlbum;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface MusicAlbumDao extends JpaRepository<MusicAlbum, Long>{

	MusicAlbum getByMusicIdAndAlbumIdAndStatus(Long musicId, Long albumId, Integer status);
	
	List<MusicAlbum> getByAlbumIdAndStatus(Long albumId, Integer status);
	
	Integer deleteByAlbumId(Long album);
}
