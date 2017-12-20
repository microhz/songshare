package com.micro.ss.web.test;


import java.util.Date;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLEngineResult.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.enums.StatusEnum;

/**
 * @author micro
 * @date 2017年7月15日
 * @description : 
 */
@Component
public class AddData {
	
	@PostConstruct
	public void init() {
		/*
		for (int i = 0;i < 30;i ++) {
			MusicInfo musicInfo = new MusicInfo();
			musicInfo.setComment("测试");
			musicInfo.setCreateTime(new Date());
			musicInfo.setExt("扩展信息");
			musicInfo.setFileUrl("http://www.test.com");
			musicInfo.setLyricsId(Long.valueOf(String.valueOf(i)));
			musicInfo.setName("歌名 " + i);
			musicInfo.setSinger("歌手" + i);
			musicInfo.setSingerAlbum("专辑" + i);
			musicInfo.setStatus(StatusEnum.NORMAL.getStatus());
			musicInfo.setUserId(1L);
			musicInfoMapper.insert(musicInfo);
		}*/
		
	}
}
