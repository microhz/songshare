package com.micro.ss.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.data.model.MusicInfo;
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
@Service
public class AlbumServiceImpl extends ServiceSupport implements AlbumService {

	public ServiceResult<Object> insertAlbum(Album album) {
		albumDao.save(album);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Object> addMusicToAlbum(MusicAlbum musicAlbum) {
		// ----是否重复----
		if (musicAlbumDao.getByMusicIdAndAlbumIdAndStatus(musicAlbum.getMusicId(), musicAlbum.getAlbumId(), musicAlbum.getStatus()) != null) {
			return ServiceResult.getErrorResult("重复添加");
		}
		musicAlbumDao.save(musicAlbum);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<Album> getAlbumById(Long albumId) {
		return ServiceResult.getSuccess(albumDao.findOne(albumId));
	}

	public ServiceResult<AlbumDetail> getAlbumDetail(Long albumId) {
		Album album = getAlbumById(albumId).getData();
		if (album == null) {
			return ServiceResult.getSuccess(null);
		}
		AlbumDetail albumDetail = new AlbumDetail();
		albumDetail.setAlbum(album);
		List<MusicAlbum> musicAlbumList = musicAlbumDao.getByAlbumIdAndStatus(albumId, StatusEnum.NORMAL.getStatus());
		if (musicAlbumList != null && musicAlbumList.size() > 0) {
			
			List<Long> musicIdList = new ArrayList<Long>();
			for (MusicAlbum musicAlbum : musicAlbumList) {
				if (musicAlbum.getMusicId() != null) {
					musicIdList.add(musicAlbum.getMusicId());
				}
			}
			if (musicIdList.size() > 0) {
				List<MusicInfo> musicInfoList = musicInfoDao.getByIdIn(musicIdList);
				albumDetail.setMusicInfoList(musicInfoList);
			}
		}
		return ServiceResult.getSuccess(albumDetail);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public ServiceResult<Object> delAlbum(Long albumId) {
		musicAlbumDao.deleteByAlbumId(albumId);
		albumDao.delete(albumId);
		return ServiceResult.getSuccess();
	}

	public ServiceResult<List<Album>> searchAlbum(String name, Integer page, Integer size) {
		return ServiceResult.getSuccess(albumDao.getAlbumLikeName(name, (page - 1) * size, size));
	}

}
