package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.ss.web.data.model.Album;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface AlbumDao extends JpaRepository<Album, Long>{
	@Query(nativeQuery = true, value = "SELECT * FROM album a WHERE a.name LIKE :name ORDER BY id DESC LIMIT :start,:end")
	public List<Album> getAlbumLikeName(@Param("name")String name, @Param("start") Integer start, @Param("end") Integer end);
}
