package com.micro.ss.web.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.support.ControllerSupport;

/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */

@RequestMapping("album")
@RestController
public class AlbumController extends ControllerSupport {

	@RequestMapping("create")
	@ResponseBody
	@LogCheck
	public String create(@RequestParam("albumName") String albumName,
			@RequestParam("icoImage") String icoImage) {
		Album album = new Album();
		album.setAlbumName(albumName);
		album.setCreateTime(new Date());
		album.setIcoImage(icoImage);
		album.setStatus(StatusEnum.NORMAL.getStatus());
		ServiceResult<Object> result = albumService.insertAlbum(album);
		if (result.isSuccess()) {
			return ok();
		}
		return fail(result.getMsg());
	}
	
	@RequestMapping("addMusic")
	@ResponseBody
	@LogCheck
	public String addMusicToAlbum(@RequestParam("musicId") Long musicId,
			@RequestParam("albumId") Long albumId) {
		MusicAlbum musicAlbum = new MusicAlbum();
		musicAlbum.setAlbumId(albumId);
		musicAlbum.setMusicId(musicId);
		musicAlbum.setCreatorId(curUserId());
		musicAlbum.setCreateTime(new Date());
		musicAlbum.setStatus(StatusEnum.NORMAL.getStatus());
		ServiceResult<Object> result = albumService.addMusicToAlbum(musicAlbum);
		if (result.isSuccess()) {
			return ok();
		}
		return fail(result.getMsg());
	}
}
