package com.micro.ss.web.pojo;
/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */

import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicInfo;

public class MusicCommentaryModel {

	private MusicCommentary musicCommentary;

	private MusicInfo musicInfo;

	public MusicCommentary getMusicCommentary() {
		return musicCommentary;
	}

	public void setMusicCommentary(MusicCommentary musicCommentary) {
		this.musicCommentary = musicCommentary;
	}

	public MusicInfo getMusicInfo() {
		return musicInfo;
	}

	public void setMusicInfo(MusicInfo musicInfo) {
		this.musicInfo = musicInfo;
	}

}
