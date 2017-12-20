package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.ss.web.data.model.MusicInfo;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface MusicInfoDao extends JpaRepository<MusicInfo, Long> {
	
	List<MusicInfo> getByIdIn(List<Long> idList);
	
	@Query(nativeQuery = true , value = "SELECT * FROM music_info m WHERE m.user_id = :userId AND status = :statusLIMIT :start,:end ")
	List<MusicInfo> getRecentMusic(@Param("userId") Long userId, @Param("start") Integer start, @Param("end") Integer end, @Param("status") Integer status);
	
	List<MusicInfo> getByUserIdAndStatus(Long userId, Integer status);
}
