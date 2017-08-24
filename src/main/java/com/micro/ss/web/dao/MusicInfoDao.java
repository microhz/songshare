package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.MusicInfo;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface MusicInfoDao extends JpaRepository<MusicInfo, Long> {
	List<MusicInfo> getByIdIn(List<Long> idList);
}
