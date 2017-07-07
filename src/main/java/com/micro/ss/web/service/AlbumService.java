package com.micro.ss.web.service;
/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.pojo.ServiceResult;

public interface AlbumService {

	/**
	 * 添加专辑
	 */
	ServiceResult<Object> insertAlbum(Album album);
	
	/**
	 * 添加音乐
	 */
	ServiceResult<Object> addMusicToAlbum(MusicAlbum musicAlbum);
}

