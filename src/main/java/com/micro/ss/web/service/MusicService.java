package com.micro.ss.web.service;

import java.util.List;

import com.micro.ss.web.data.model.MusicInfo;

/**
 * @author mapc 
 * @date 2017年7月2日
 */
public interface MusicService {

	List<MusicInfo> getUploadMusic(Long userId);
}
