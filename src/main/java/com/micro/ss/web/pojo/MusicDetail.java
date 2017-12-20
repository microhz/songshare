package com.micro.ss.web.pojo;
/**
 * @author mapc 
 * @date 2017年7月2日
 */

import java.util.Date;
import java.util.List;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicTag;
import com.micro.ss.web.data.model.UserInfo;

public class MusicDetail {

	private String musicName;

	private UserInfo shareUser;

	private String url;

	private Date createTime;

	private Integer listenCount;

	private List<MusicTag> tagNameList;

	private List<Album> albumList;

	private List<UserInfo> collectUserList;

	private List<UserInfo> recommendUserList;

	private List<MusicCommentary> commentaryList;

	private Double avgScore;

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public UserInfo getShareUser() {
		return shareUser;
	}

	public void setShareUser(UserInfo shareUser) {
		this.shareUser = shareUser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getListenCount() {
		return listenCount;
	}

	public void setListenCount(Integer listenCount) {
		this.listenCount = listenCount;
	}

	public List<MusicTag> getTagNameList() {
		return tagNameList;
	}

	public void setTagNameList(List<MusicTag> tagNameList) {
		this.tagNameList = tagNameList;
	}

	public List<Album> getAlbumList() {
		return albumList;
	}

	public void setAlbumList(List<Album> albumList) {
		this.albumList = albumList;
	}

	public List<UserInfo> getCollectUserList() {
		return collectUserList;
	}

	public void setCollectUserList(List<UserInfo> collectUserList) {
		this.collectUserList = collectUserList;
	}

	public List<UserInfo> getRecommendUserList() {
		return recommendUserList;
	}

	public void setRecommendUserList(List<UserInfo> recommendUserList) {
		this.recommendUserList = recommendUserList;
	}

	public List<MusicCommentary> getCommentaryList() {
		return commentaryList;
	}

	public void setCommentaryList(List<MusicCommentary> commentaryList) {
		this.commentaryList = commentaryList;
	}

	public Double getAvgScore() {
		return avgScore;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

}
