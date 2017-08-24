package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.ss.web.data.model.UserHomeCommentary;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface UserHomeCommentaryDao extends JpaRepository<UserHomeCommentary, Long> {

	@Query(nativeQuery = true , value = "SELECT * FROM user_home_commentary WHERE user_id = :userId ORDER BY id DESC")
	public List<UserHomeCommentary> getUserHomeCommentaryByUserId(@Param("userId") Long userId);
}
