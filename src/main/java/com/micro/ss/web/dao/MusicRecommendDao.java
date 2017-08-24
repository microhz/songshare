package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.MusicRecommend;

/**
 * @author micro
 * @date 2017年8月24日
 * @description :
 */
public interface MusicRecommendDao extends JpaRepository<MusicRecommend, Long> {
	List<MusicRecommend> getByUserIdAndStatus(Long userId, Integer status);
}
