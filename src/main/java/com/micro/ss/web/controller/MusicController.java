package com.micro.ss.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.micro.ss.web.annotations.UnLogCheck;
import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.UserCollection;
import com.micro.ss.web.enums.FileTypeEnum;
import com.micro.ss.web.enums.MusicCommentaryEnum;
import com.micro.ss.web.enums.MusicStatusEnum;
import com.micro.ss.web.enums.ResponseInfoEnum;
import com.micro.ss.web.support.ControllerSupport;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
@RestController
@RequestMapping("music")
public class MusicController extends ControllerSupport {

	@RequestMapping("getUploadList.do")
	@ResponseBody
	public String getCurUploadList() {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		return ok(musicService.getUploadMusic(curUserId()));
	}
	
	/**
	 * 音乐添加
	 */
	@RequestMapping("add.do")
	@ResponseBody
	public String add(@RequestParam("name") String name,
			@RequestParam("singer") String singer,
			@RequestParam("singerAlbum") String album,
			@RequestParam("comment") String comment,
			@RequestParam("fileUrl") String fileUrl,
			@RequestParam(value = "lyrics", required = false) String lyrics) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		Long lyricsId = null;
		if (StringUtils.isNoneBlank(lyrics)) {
			lyricsId = musicService.addLyrics(lyrics);
		}
		MusicInfo musicInfo = new MusicInfo();
		musicInfo.setUserId(curUserId());
		musicInfo.setCreateTime(new Date());
		musicInfo.setStatus(MusicStatusEnum.NORMAL.getCode());
		musicInfo.setName(name);
		musicInfo.setComment(comment);
		musicInfo.setFileUrl(fileUrl);
		musicInfo.setLyricsId(lyricsId);
		musicInfo.setSinger(singer);
		musicInfo.setSingerAlbum(album);
		musicService.addMusic(musicInfo);
		return ok();
	}
	
	/**
	 * 删除音乐
	 */
	@RequestMapping("delete.do")
	@ResponseBody
	public String delete(@RequestParam("musicId") Long musicId) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		MusicInfo musicInfo = musicService.getMusicById(musicId);
		if (musicInfo == null) {
			return fail(ResponseInfoEnum.MUSIC_NOT_EXITS.getInfo());
		}
		if (musicInfo.getUserId() != curUserId()) {
			return fail(ResponseInfoEnum.FORBIDEN.getInfo());
		}
		musicService.deleteMusic(musicId);
		return ok();
	}
	
	/**
	 * 音乐推荐
	 */
	@RequestMapping("recommend.do")
	@ResponseBody
	public String recommend(Long musicId) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		musicService.recommnd(curUserId(), musicId);
		return ok();
	}
	
	/**
	 * 音乐收藏
	 */
	@RequestMapping("collect.do")
	@ResponseBody
	public String collect(@RequestParam("musicId") Long musicId) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		UserCollection userCollection = new UserCollection();
		userCollection.setCreateTime(new Date());
		userCollection.setUserId(curUserId());
		userCollection.setMusicId(musicId);
		userCollection.setStatus(0);
		musicService.collectionMusic(userCollection);
		return ok();
	}
	
	/**
	 * 链接分享
	 */
	@RequestMapping("shareUrl.do")
	@ResponseBody
	@UnLogCheck
	public String share(@RequestParam("musicId") Long musicId) {
		MusicInfo musicInfo = musicService.getMusicById(musicId);
		if (musicInfo == null) {
			return fail(ResponseInfoEnum.MUSIC_NOT_EXITS.getInfo());
		}
		return ok(musicInfo.getFileUrl());
	}
	
	/**
	 * 音乐评分
	 */
	@RequestMapping("score.do")
	@ResponseBody
	public String score(@RequestParam("score") Integer score, @RequestParam("musicId") Long musicId) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		if (score >= 0 && score <= 5 && musicService.score(musicId, curUserId(), score)) {
			return ok();
		}
		return fail("score limit");
	}
	
	/**
	 * 音乐评论
	 */
	@RequestMapping("comment.do")
	@ResponseBody
	public String comment(@RequestParam("musicId") Long musicId,@RequestParam("commentary") String commentary) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		if (StringUtils.isBlank(commentary)) {
			return fail(ResponseInfoEnum.PARAM_ERROR.getInfo());
		}
		MusicCommentary musicCommentary = new MusicCommentary();
		musicCommentary.setUserId(curUserId());
		musicCommentary.setTargetId(musicId);
		musicCommentary.setType(MusicCommentaryEnum.SIGLE_MUSIC.getTypeCode());
		musicCommentary.setCommentary(commentary);
		return ok();
	}
	
	/**
	 * 音乐上传
	 */
	@RequestMapping("upload.do")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile multipartFile) {
		if (curUser() == null) {
			return fail(ResponseInfoEnum.NOT_LOGIN.getInfo());
		}
		try {
			InputStream inputStream = multipartFile.getInputStream();
			String url = fileService.upload(inputStream, FileTypeEnum.MUSIC);
			return ok(url);
		} catch (IOException e) {
			getExceptionLogger().error("upload file error ,", e);
		}
		return fail(ResponseInfoEnum.SYSTEM_ERROR.getInfo());
	}
}
