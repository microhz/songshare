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

	List<MusicInfo> getUploadMusic(Long userId, Integer page, Integer size);
	
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
	
	/**
	 * 最新推荐n首音乐
	 */
	List<MusicInfo> getRecentRecommendMusicList(Integer limit);
	
	/**
	 * 模糊查询音乐
	 */
	List<MusicInfo> getMusicByName(String name);
	
	/**
	 * 标签查询音乐
	 */
	List<MusicInfo> getMusicListByTag(Long tagId, Integer page, Integer size);
	
	/**
	 * 分页获取用户推荐
	 */
	List<MusicInfo> getRecommendMusicList(Long userId, Integer page,Integer size);
	
	/**
	 * 分页查看用户分享（上传）的音乐列表
	 */
	List<MusicInfo> getShareMusicList(Long userId, Integer page, Integer size);
	
	/**
	 * 分页获取最新上传音乐
	 */
	List<MusicInfo> searchRecentUploadMusic(Integer page, Integer size);
}

