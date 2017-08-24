package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.UserRelation;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface UserRelationDao extends JpaRepository<UserRelation, Long>{

	UserRelation getUserRelationByUserIdAndTargetUserIdAndStatus(Long userId, Long targetUserId, Integer status);
	
	List<UserRelation> getByUserIdAndStatus(Long userId, Integer status);
}
