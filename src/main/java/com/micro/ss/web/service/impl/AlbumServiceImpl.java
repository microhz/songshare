package com.micro.ss.web.service.impl;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.data.model.MusicAlbumExample;
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
		musicAlbumExample.or().andMusicIdEqualTo(musicAlbum.getMusicId()).andAlbumIdEqualTo(musicAlbum.getAlbumId());
		if (musicAlbumMapper.countByExample(musicAlbumExample) > 0) {
			return ServiceResult.getErrorResult("重复添加");
		}
		musicAlbumMapper.insert(musicAlbum);
		return ServiceResult.getSuccess();
	}

}
