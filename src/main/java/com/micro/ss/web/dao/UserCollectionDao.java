package com.micro.ss.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.UserCollection;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface UserCollectionDao extends JpaRepository<UserCollection, Long>{

	UserCollection getByUserIdAndMusicIdAndStatus(Long userId, Long musicId, Integer status);
}
