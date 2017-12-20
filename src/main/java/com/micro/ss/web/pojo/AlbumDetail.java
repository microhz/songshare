package com.micro.ss.web.pojo;
/**
 * @author micro
 * @date 2017年7月9日
 * @description : 
 */

import java.util.List;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicInfo;

public class AlbumDetail {

	private Album album;
	private List<MusicInfo> musicInfoList;

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public List<MusicInfo> getMusicInfoList() {
		return musicInfoList;
	}

	public void setMusicInfoList(List<MusicInfo> musicInfoList) {
		this.musicInfoList = musicInfoList;
	}

}
