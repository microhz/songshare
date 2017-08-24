package com.micro.ss.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.UserHomeCommentary;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface UserHomeCommentaryDao extends JpaRepository<UserHomeCommentary, Long> {

}
