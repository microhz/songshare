package com.micro.ss.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.micro.ss.web.data.model.Group;

/**
 * @author micro
 * @date 2017年8月24日
 * @description :
 */
public interface GroupDao extends JpaRepository<Group, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM group WHERE group_name LIKE :name")
	List<Group> getGroupLikeName(@Param("name") String name);
}
