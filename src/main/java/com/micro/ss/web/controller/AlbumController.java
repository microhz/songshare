package com.micro.ss.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.enums.ErrorMsgEnum;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.AlbumDetail;
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

	@RequestMapping("create.do")
	@ResponseBody
	@LogCheck
	public String create(@RequestParam("albumName") String albumName,
			@RequestParam("icoImage") String icoImage) {
		Album album = new Album();
		album.setAlbumName(albumName);
		album.setCreateTime(new Date());
		album.setIcoImage(icoImage);
		album.setUserId(curUserId());
		album.setStatus(StatusEnum.NORMAL.getStatus());
		ServiceResult<Object> result = albumService.insertAlbum(album);
		if (result.isSuccess()) {
			return ok();
		}
		return fail(result.getMsg());
	}
	
	@RequestMapping("addMusic.do")
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
	
	@RequestMapping("delAlbum.do")
	@ResponseBody
	@LogCheck
	public String delAblum(@RequestParam("albumId") Long albumId) {
		if (albumService.getAlbumById(albumId).getData() == null) {
			return fail(ErrorMsgEnum.ALBUM_NOT_EXITS);
		}
		if (albumService.delAlbum(albumId).isSuccess()) {
			return ok();
		}
		return fail();
	}
	
	@RequestMapping("getAblumDetail.do")
	@ResponseBody
	public String getDetail(@RequestParam("albumId") Long albumId) {
		ServiceResult<AlbumDetail> result = albumService.getAlbumDetail(albumId);
		if (result.isSuccess()) {
			return ok(result.getData());
		}
		return fail();
	}
	
	@RequestMapping("search.do")
	@ResponseBody
	public String searchAlbum() {
		return null;
	}
}
