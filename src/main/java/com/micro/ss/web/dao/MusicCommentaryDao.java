package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.ss.web.data.model.MusicCommentary;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface MusicCommentaryDao extends JpaRepository<MusicCommentary, Long>{

	@Query(nativeQuery = true , value = "SELECT * FROM music_commentary ORDER BY id DESC LIMIT 0,:limit")
	List<MusicCommentary> getByRecentLimit(@Param("limit") Integer limit);
	
	MusicCommentary getByUserIdAndMusicIdAndStatus(Long userId, Long musicId, Integer status);
}
