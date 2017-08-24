package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.ss.web.data.model.UserInfo;

/**
 * @author mapc 
 * @date 2017年7月1日
 */
public interface UserInfoDao extends JpaRepository<UserInfo, Long>{

	List<UserInfo> getByIdIn(List<Long> idList);
}
