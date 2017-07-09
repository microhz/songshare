package com.micro.ss.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.AlbumExample;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.data.model.MusicAlbumExample;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.MusicInfoExample;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.AlbumDetail;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.service.AlbumService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author micro
 * @date 2017年7月7日
 * @description : 
 */
public class AlbumServiceImpl extends ServiceSupport implements AlbumService {

	public ServiceResult<Object> insertAlbum(Album album) {
		albumMapper.insert(album);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Object> addMusicToAlbum(MusicAlbum musicAlbum) {
		// ----是否重复----
		MusicAlbumExample musicAlbumExample = new MusicAlbumExample();
		musicAlbumExample.or().andMusicIdEqualTo(musicAlbum.getMusicId()).andAlbumIdEqualTo(musicAlbum.getAlbumId()).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		if (musicAlbumMapper.countByExample(musicAlbumExample) > 0) {
			return ServiceResult.getErrorResult("重复添加");
		}
		musicAlbumMapper.insert(musicAlbum);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Album> getAlbumById(Long albumId) {
		return ServiceResult.getSuccess(albumMapper.selectByPrimaryKey(albumId));
	}

	public ServiceResult<AlbumDetail> getAlbumDetail(Long albumId) {
		Album album = getAlbumById(albumId).getData();
		if (album == null) {
			return ServiceResult.getSuccess(null);
		}
		AlbumDetail albumDetail = new AlbumDetail();
		albumDetail.setAlbum(album);
		MusicAlbumExample musicAlbumExample = new MusicAlbumExample();
		musicAlbumExample.or().andAlbumIdEqualTo(albumId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<MusicAlbum> musicAlbumList = musicAlbumMapper.selectByExample(musicAlbumExample);
		if (musicAlbumList != null && musicAlbumList.size() > 0) {
			
			List<Long> musicIdList = new ArrayList<Long>();
			for (MusicAlbum musicAlbum : musicAlbumList) {
				if (musicAlbum.getMusicId() != null) {
					musicIdList.add(musicAlbum.getMusicId());
				}
			}
			if (musicIdList.size() > 0) {
				MusicInfoExample musicInfoExample = new MusicInfoExample();
				musicInfoExample.or().andIdIn(musicIdList);
				List<MusicInfo> musicInfoList = musicInfoMapper.selectByExample(musicInfoExample);
				albumDetail.setMusicInfoList(musicInfoList);
			}
		}
		return ServiceResult.getSuccess(albumDetail);
	}

	public ServiceResult<Object> delAlbum(Long albumId) {
		MusicAlbumExample musicAlbumExample = new MusicAlbumExample();
		musicAlbumExample.or().andAlbumIdEqualTo(albumId);
		musicAlbumMapper.deleteByExample(musicAlbumExample);
		albumMapper.deleteByPrimaryKey(albumId);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<List<Album>> searchAlbum(String name, Integer page, Integer size) {
		AlbumExample albumExample = new AlbumExample();
		albumExample.or().andAlbumNameLike("%" + name + "%");
		albumExample.setStart(page * size);
		albumExample.setEnd(size);
		return ServiceResult.getSuccess(albumMapper.selectByExample(albumExample));
	}

}
