package com.micro.ss.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.Message;

/**
 * @author micro
 * @date 2017年8月24日
 * @description : 
 */
public interface MessageDao extends JpaRepository<Message, Long>{

}
