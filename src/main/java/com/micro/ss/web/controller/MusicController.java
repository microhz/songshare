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

import com.micro.ss.web.annotations.LogCheck;
import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.UserCollection;
import com.micro.ss.web.data.model.UserListenRecord;
import com.micro.ss.web.enums.FileTypeEnum;
import com.micro.ss.web.enums.MusicCommentaryEnum;
import com.micro.ss.web.enums.MusicStatusEnum;
import com.micro.ss.web.enums.ErrorMsgEnum;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.MusicDetail;
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
	public String getCurUploadList(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
		if (curUser() == null) {
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}
		return ok(musicService.getUploadMusic(curUserId(), page, size));
	}
	
	@RequestMapping("getMusicDetailById.do")
	@ResponseBody
	public String getMusic(@RequestParam("musicId") Long musicId) {
		MusicInfo musicInfo = musicService.getMusicById(musicId);
		if (musicInfo == null) return fail(ErrorMsgEnum.MUSIC_NOT_EXITS);
		MusicDetail musicDetail = musicService.getMusicDetailById(musicId);
		if (musicDetail != null) {
			// 转换map
			return ok(musicDetail);
		}
		return fail(ErrorMsgEnum.MUSIC_NOT_EXITS);
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
		Long lyricsId = null;
		if (StringUtils.isNoneBlank(lyrics)) {
			lyricsId = musicService.addLyrics(lyrics);
		}
		MusicInfo musicInfo = new MusicInfo();
		musicInfo.setUserId(curUserId() != null ? curUserId() : -1);
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
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}
		MusicInfo musicInfo = musicService.getMusicById(musicId);
		if (musicInfo == null) {
			return fail(ErrorMsgEnum.MUSIC_NOT_EXITS);
		}
		if (musicInfo.getUserId() != curUserId()) {
			return fail(ErrorMsgEnum.FORBIDEN);
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
			return fail(ErrorMsgEnum.NOT_LOGIN);
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
			return fail(ErrorMsgEnum.NOT_LOGIN);
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
	public String share(@RequestParam("musicId") Long musicId) {
		MusicInfo musicInfo = musicService.getMusicById(musicId);
		if (musicInfo == null) {
			return fail(ErrorMsgEnum.MUSIC_NOT_EXITS);
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
			return fail(ErrorMsgEnum.NOT_LOGIN);
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
	@LogCheck
	public String comment(@RequestParam("musicId") Long musicId,@RequestParam("commentary") String commentary) {
		/*if (curUser() == null) {
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}*/
		if (StringUtils.isBlank(commentary)) {
			return fail(ErrorMsgEnum.PARAM_ERROR);
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
	@LogCheck
	public String upload(@RequestParam("file") MultipartFile multipartFile) {
		/*if (curUser() == null) {
			return fail(ErrorMsgEnum.NOT_LOGIN);
		}*/
		try {
			InputStream inputStream = multipartFile.getInputStream();
			String url = fileService.upload(inputStream, FileTypeEnum.MUSIC);
			return ok(url);
		} catch (IOException e) {
			getExceptionLogger().error("upload file error ,", e);
		}
		return fail(ErrorMsgEnum.SYSTEM_ERROR);
	}
	
	/**
	 * 音乐播放记录
	 */
	@RequestMapping("record.do")
	@ResponseBody
	public String record(@RequestParam("musicId") Long musicId) {
		UserListenRecord userListenRecord = new UserListenRecord();
		if (curUser() != null) userListenRecord.setUserId(curUserId());
		userListenRecord.setListenTime(new Date());
		userListenRecord.setMusicId(musicId);
		userListenRecord.setStatus(StatusEnum.NORMAL.getStatus());
		musicService.addUserListenRecord(userListenRecord);
		return ok();
	}
	
	/**
	 * 获取最新推荐音乐列表
	 */
	@RequestMapping("getRentRecommendList.do")
	@ResponseBody
	public String getRecentRecommendMusicList(@RequestParam(value = "count", required = false) Integer count) {
		return ok(musicService.getRecentRecommendMusicList(count));
	}
	
	/**
	 * 根据名称查询音乐
	 */
	@RequestMapping("searchMusic.do")
	@ResponseBody
	public String searchMusic(@RequestParam("likeName") String likeName) {
		return ok(musicService.getMusicByName(likeName));
	}
	
	/**
	 * 根据标签查询音乐列表
	 */
	@RequestMapping("searchMusicListByTag.do")
	@ResponseBody
	public String searchTagMusic(@RequestParam("tagId") Long tagId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "20") Integer size) {
		return ok(musicService.getMusicListByTag(tagId, page, size));
	}
	
	/**
	 * 获取某个用户的推荐列表，分页
	 */
	@RequestMapping("searchRecommend.do")
	@ResponseBody
	public String searchRecoomend(@RequestParam("userId") Long userId,@RequestParam(value = "page", required = false) Integer page,@RequestParam(value = "size", required = false) Integer size) {
		return ok(musicService.getRecommendMusicList(userId, page, size));
	}
	
	/**
	 * 查看某个用户分享（上传）的音乐
	 */
	@RequestMapping("searchList.do")
	@ResponseBody
	public String searchList(@RequestParam("userId") Long userId, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
		if (userId == null) {
			return fail(ErrorMsgEnum.PARAM_ERROR);
		}
		return ok(musicService.getShareMusicList(userId, page, size));
	}
	
	/**
	 * 获取最新上传音乐
	 */
	@RequestMapping("getRecentUpload.do")
	@ResponseBody
	public Object getRecentUpload(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "20") Integer size) {
		return ok(musicService.searchRecentUploadMusic(page, size));
	}
	
}




