package com.micro.ss.web.service;

import java.util.List;

import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.UserCollection;
import com.micro.ss.web.data.model.UserListenRecord;
import com.micro.ss.web.pojo.MusicDetail;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
public interface MusicService {

	List<MusicInfo> getUploadMusic(Long userId);
	
	/**
	 * 
	 * @param lyrics
	 * @return
	 */
	Long addLyrics(String lyrics);
	
	boolean addMusic(MusicInfo musicInfo);
	
	MusicInfo getMusicById(Long musicId);
	
	MusicDetail getMusicDetailById(Long musicId);
	
	boolean deleteMusic(Long musicId);
	
	/**
	 * 推荐音乐
	 */
	boolean recommnd(Long userId, Long musicId);
	
	/**
	 * 收藏音乐
	 */
	boolean collectionMusic(UserCollection userCollection);
	
	/**
	 * 音乐评分
	 */
	boolean score(Long musicId, Long userId, Integer score);
	
	/**
	 * 音乐评论
	 */
	boolean comment(MusicCommentary musicCommentary);
	
	/**
	 * 音乐试听记录
	 */
	boolean addUserListenRecord(UserListenRecord userListenRecord);
}
