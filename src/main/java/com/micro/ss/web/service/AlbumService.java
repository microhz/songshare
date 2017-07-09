package com.micro.ss.web.service;
/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */

import java.util.List;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.pojo.AlbumDetail;
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

	/**
	 * 查找专辑
	 */
	ServiceResult<Album> getAlbumById(Long album);

	/**
	 * 查看专辑详细信息
	 */
	ServiceResult<AlbumDetail> getAlbumDetail(Long albumId);

	/**
	 * 删除专辑
	 */
	ServiceResult<Object> delAlbum(Long albumId);

	/**
	 * 模糊查询
	 */
	ServiceResult<List<Album>> searchAlbum(String name, Integer page, Integer size);
}
