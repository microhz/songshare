package com.micro.ss.web.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.MusicInfoExample;
import com.micro.ss.web.data.model.MusicRecommend;
import com.micro.ss.web.data.model.MusicRecommendExample;
import com.micro.ss.web.data.model.UserCollection;
import com.micro.ss.web.data.model.UserCollectionExample;
import com.micro.ss.web.data.model.UserMusicScore;
import com.micro.ss.web.data.model.UserMusicScoreExample;
import com.micro.ss.web.enums.MusicStatusEnum;
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

	public Long addLyrics(String lyrics) {
		/**
		 * TODO store mongo DB
		 */
		return 0L;
	}

	public boolean addMusic(MusicInfo musicInfo) {
		musicInfoMapper.insert(musicInfo);
		return true;
	}

	public MusicInfo getMusicById(Long musicId) {
		return musicInfoMapper.selectByPrimaryKey(musicId);
	}

	public boolean deleteMusic(Long musicId) {
		MusicInfo musicInfo = new MusicInfo();
		musicInfo.setStatus(MusicStatusEnum.DELETED.getCode());
		musicInfo.setId(musicId);
		musicInfoMapper.updateByPrimaryKeySelective(musicInfo);
		return true;
	}

	public boolean recommnd(Long userId, Long musicId) {
		MusicRecommendExample musicRecommendExample = new MusicRecommendExample();
		musicRecommendExample.or().andUserIdEqualTo(userId).andMusicIdEqualTo(musicId);
		if (musicRecommendMapper.countByExample(musicRecommendExample) > 0) {
			// 重复推荐
			return true;
		}
		MusicRecommend musicRecommend = new MusicRecommend();
		musicRecommend.setUserId(userId);
		musicRecommend.setMusicId(musicId);
		musicRecommend.setCreateTime(new Date());
		musicRecommendMapper.insert(musicRecommend);
		return true;
	}

	public boolean collectionMusic(UserCollection userCollection) {
		UserCollectionExample userCollectionExample = new UserCollectionExample();
		userCollectionExample.or().andUserIdEqualTo(userCollection.getUserId()).andMusicIdEqualTo(userCollection.getMusicId());
		if (userCollectionMapper.countByExample(userCollectionExample) > 0) return true;
		userCollectionMapper.insert(userCollection);
		return true;
	}

	public boolean score(Long musicId, Long userId, Integer score) {
		UserMusicScoreExample userMusicScoreExample = new UserMusicScoreExample();
		userMusicScoreExample.or().andUserIdEqualTo(userId).andMusicIdEqualTo(musicId);
		if (userMusicScoreMapper.countByExample(userMusicScoreExample) > 0) return true;
		UserMusicScore userMusicScore = new UserMusicScore();
		userMusicScore.setMusicId(musicId);
		userMusicScore.setScore(score);
		userMusicScore.setStatus(0);
		userMusicScore.setUserId(userId);
		userMusicScore.setDate(new Date());
		userMusicScoreMapper.insert(userMusicScore);
		return true;
	}

}
