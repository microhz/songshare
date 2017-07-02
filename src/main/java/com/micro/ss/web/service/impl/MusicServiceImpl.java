package com.micro.ss.web.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.MusicInfoExample;
import com.micro.ss.web.service.MusicService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author mapc
 * @date 2017年7月2日
 */
@Service
public class MusicServiceImpl extends ServiceSupport implements MusicService {

	public List<MusicInfo> getUploadMusic(Long userId) {
		MusicInfoExample musicInfoExample = new MusicInfoExample();
		musicInfoExample.or().andUserIdEqualTo(userId);
		return musicInfoMapper.selectByExample(musicInfoExample);
	}

}
