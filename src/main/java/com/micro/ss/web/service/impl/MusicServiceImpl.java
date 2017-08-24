package com.micro.ss.web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.micro.ss.web.data.model.Album;
import com.micro.ss.web.data.model.AlbumExample;
import com.micro.ss.web.data.model.MusicAlbum;
import com.micro.ss.web.data.model.MusicAlbumExample;
import com.micro.ss.web.data.model.MusicCommentary;
import com.micro.ss.web.data.model.MusicCommentaryExample;
import com.micro.ss.web.data.model.MusicInfo;
import com.micro.ss.web.data.model.MusicInfoExample;
import com.micro.ss.web.data.model.MusicRecommend;
import com.micro.ss.web.data.model.MusicRecommendExample;
import com.micro.ss.web.data.model.MusicTag;
import com.micro.ss.web.data.model.MusicTagExample;
import com.micro.ss.web.data.model.MusicTagRelation;
import com.micro.ss.web.data.model.MusicTagRelationExample;
import com.micro.ss.web.data.model.UserCollection;
import com.micro.ss.web.data.model.UserCollectionExample;
import com.micro.ss.web.data.model.UserInfo;
import com.micro.ss.web.data.model.UserInfoExample;
import com.micro.ss.web.data.model.UserListenRecord;
import com.micro.ss.web.data.model.UserListenRecordExample;
import com.micro.ss.web.data.model.UserMusicScore;
import com.micro.ss.web.data.model.UserMusicScoreExample;
import com.micro.ss.web.enums.MusicCommentaryEnum;
import com.micro.ss.web.enums.MusicStatusEnum;
import com.micro.ss.web.enums.StatusEnum;
import com.micro.ss.web.pojo.MusicDetail;
import com.micro.ss.web.pojo.ServiceResult;
import com.micro.ss.web.service.MusicService;
import com.micro.ss.web.support.ServiceSupport;

/**
 * @author mapc
 * @date 2017年7月2日
 */
@Service
public class MusicServiceImpl extends ServiceSupport implements MusicService {

	public List<MusicInfo> getUploadMusic(Long userId, Integer page, Integer size) {
		MusicInfoExample musicInfoExample = new MusicInfoExample();
		musicInfoExample.or().andUserIdEqualTo(userId).andStatusEqualTo(MusicStatusEnum.NORMAL.getCode());
		if (page != null && size != null) {
			musicInfoExample.setStart(page * size);
			musicInfoExample.setEnd(size);
		}
		return musicInfoMapper.limitSelectByExample(musicInfoExample);
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
	
	public ServiceResult<Boolean> recommnd(Long userId, Long musicId) {
		MusicInfoExample musicInfoExample = new MusicInfoExample();
		musicInfoExample.or().andIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<MusicInfo> musicInfoList = musicInfoMapper.selectByExample(musicInfoExample);
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
		if (musicInfoList == null || musicInfoList.size() == 0) {
			result.setMsg("音乐不存在");
			result.setSuccess(false);
		} else {
			MusicRecommendExample musicRecommendExample = new MusicRecommendExample();
			musicRecommendExample.or().andUserIdEqualTo(userId).andMusicIdEqualTo(musicId);
			if (musicRecommendMapper.countByExample(musicRecommendExample) > 0) {
				// 重复推荐
				result.setMsg("重复推荐");
				result.setSuccess(false);
			} else {
				MusicRecommend musicRecommend = new MusicRecommend();
				musicRecommend.setUserId(userId);
				musicRecommend.setMusicId(musicId);
				musicRecommend.setCreateTime(new Date());
				musicRecommend.setStatus(StatusEnum.NORMAL.getStatus());
				musicRecommendMapper.insert(musicRecommend);
				result.setSuccess(true);
			}
		}
		return result;
	}

	public ServiceResult<String> collectionMusic(UserCollection userCollection) {
		if (userCollection.getMusicId() != null) {
			MusicInfo musicInfo = getMusicById(userCollection.getMusicId());
			if (musicInfo == null) {
				return ServiceResult.getErrorResult("音乐不存在");
			}
		}
		UserCollectionExample userCollectionExample = new UserCollectionExample();
		userCollectionExample.or().andUserIdEqualTo(userCollection.getUserId()).andMusicIdEqualTo(userCollection.getMusicId());
		if (userCollectionMapper.countByExample(userCollectionExample) > 0) return ServiceResult.getSuccess();
		userCollectionMapper.insert(userCollection);
		return ServiceResult.getSuccess();
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

	public boolean comment(MusicCommentary musicCommentary) {
		// 检查是否已经存在评论
		MusicCommentaryExample musicCommentaryExample = new MusicCommentaryExample();
		musicCommentaryExample.or().andUserIdEqualTo(musicCommentary.getUserId()).andTargetIdEqualTo(musicCommentary.getTargetId()).andTypeEqualTo(musicCommentary.getType());
		if (musicCommentaryMapper.countByExample(musicCommentaryExample) > 0) return true;
		musicCommentaryMapper.insert(musicCommentary);
		return true;
	}

	public boolean addUserListenRecord(UserListenRecord userListenRecord) {
		userListenRecordMapper.insert(userListenRecord);
		return true;
	}

	public MusicDetail getMusicDetailById(Long musicId) {
		if (musicId == null) return null;
		MusicInfo musicInfo = musicInfoMapper.selectByPrimaryKey(musicId);
		if (musicInfo == null) return null;
		MusicDetail musicDetail = new MusicDetail();
		musicDetail.setAlbumList(getAlbumListByMusicId(musicId));
		musicDetail.setAvgScore(getAvgByMusicId(musicId));
		musicDetail.setCollectUserList(getCollectUserListByMusicId(musicId));
		musicDetail.setCommentaryList(getMusicCommentaryByMusicId(musicId));
		musicDetail.setCreateTime(musicInfo.getCreateTime());
		musicDetail.setListenCount(getListenCountByMusicId(musicId));
		musicDetail.setMusicName(musicInfo.getName());
		musicDetail.setRecommendUserList(getRecommandUserListByMusicId(musicId));
		musicDetail.setShareUser(getUserById(musicInfo.getUserId()));
		musicDetail.setTagNameList(getMusicTagListByMusicId(musicId));
		musicDetail.setUrl(musicInfo.getFileUrl());
		return musicDetail;
	}
	
	private UserInfo getUserById(Long userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
	}
	
	private List<MusicTag> getMusicTagListByMusicId(Long musicId){
		if (musicId == null) return null;
		MusicTagRelationExample musicTagRelationExample = new MusicTagRelationExample();
		musicTagRelationExample.or().andMusicIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<MusicTagRelation> musicTagRelationList = musicTagRelationMapper.selectByExample(musicTagRelationExample);
		if (musicTagRelationList != null && musicTagRelationList.size() > 0) {
			List<Long> tagIdList = new ArrayList<Long>();
			for (MusicTagRelation musicTagRelation : musicTagRelationList) {
				if (musicTagRelation.getTagId() != null) {
					tagIdList.add(musicTagRelation.getTagId());
				}
			}
			if (tagIdList.size() > 0) {
				MusicTagExample musicTagExample = new MusicTagExample();
				musicTagExample.or().andIdIn(tagIdList);
				return musicTagMapper.selectByExample(musicTagExample);
			}
		}
		return null;
	}	
	
	private List<UserInfo> getRecommandUserListByMusicId(Long musicId) {
		if (musicId == null) return null;
		MusicRecommendExample musicRecommendExample = new MusicRecommendExample();
		musicRecommendExample.or().andMusicIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<MusicRecommend> musicRecommendList = musicRecommendMapper.selectByExample(musicRecommendExample);
		if (musicRecommendList != null && musicRecommendList.size() > 0) {
			List<Long> userIdList = new ArrayList<Long>();
			for (MusicRecommend musicRecommend : musicRecommendList) {
				if (musicRecommend.getUserId() != null) {
					userIdList.add(musicRecommend.getUserId());
				}
			}
			if (userIdList.size() > 0) {
				UserInfoExample userInfoExample = new UserInfoExample();
				userInfoExample.or().andIdIn(userIdList);
				return userInfoMapper.selectByExample(userInfoExample);
			}
		}
		return null;
	}
	
	private Integer getListenCountByMusicId(Long musicId) {
		UserListenRecordExample userListenRecordExample = new UserListenRecordExample();
		userListenRecordExample.or().andMusicIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		return userListenRecordMapper.countByExample(userListenRecordExample);
	}
	
	private List<MusicCommentary> getMusicCommentaryByMusicId(Long musicId) {
		if (musicId == null) {
			getExceptionLogger().debug("music id is null , musicId " + musicId);
		}
		MusicCommentaryExample musicCommentaryExample = new MusicCommentaryExample();
		musicCommentaryExample.or().andTargetIdEqualTo(musicId).andTypeEqualTo(MusicCommentaryEnum.SIGLE_MUSIC.getTypeCode()).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<MusicCommentary> musicCommentariyList = musicCommentaryMapper.selectByExample(musicCommentaryExample);
		if (musicCommentariyList != null && musicCommentariyList.size() > 0) {
			return musicCommentariyList;
		}
		return null;
	}
	
	private List<UserInfo> getCollectUserListByMusicId(Long musicId) {
		if (musicId == null) {
			getExceptionLogger().debug("music Id is null , musicId : " + musicId);
		}
		UserCollectionExample userCollectionExample = new UserCollectionExample();
		userCollectionExample.or().andMusicIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<UserCollection> userCollectionList = userCollectionMapper.selectByExample(userCollectionExample);
		if (userCollectionList != null && userCollectionList.size() > 0) {
			List<Long> userIdList = new ArrayList<Long>();
			for (UserCollection userCollection : userCollectionList) {
				if (userCollection.getUserId() != null) {
					userIdList.add(userCollection.getUserId());
				}
			}
			if (userIdList.size() > 0) {
				UserInfoExample userInfoExample = new UserInfoExample();
				userInfoExample.or().andIdIn(userIdList);
				return userInfoMapper.selectByExample(userInfoExample);
			}
		}
		return null;
	}
	
	private Double getAvgByMusicId(Long musicId) {
		List<UserMusicScore> userMusicScoreList = getMusciScoreRecordListByMusicId(musicId);
		if (userMusicScoreList != null && userMusicScoreList.size() > 0) {
			Double count = 0.0d;
			for (UserMusicScore userMusicScore : userMusicScoreList) {
				count += (userMusicScore.getScore() == null ? 0.0d : userMusicScore.getScore());
			}
			return count / userMusicScoreList.size();
		}
		return 0.0d;
	}
	
	private List<UserMusicScore> getMusciScoreRecordListByMusicId(Long musicId) {
		if (musicId == null) {
			getExceptionLogger().debug("musicId : " + musicId + " is null");
			return null;
		}
		UserMusicScoreExample userMusicScoreExample = new UserMusicScoreExample();
		userMusicScoreExample.or().andMusicIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		return userMusicScoreMapper.selectByExample(userMusicScoreExample);
	}
	
	private List<Album> getAlbumListByMusicId(Long musicId) {
		MusicAlbumExample musicAlbumExample = new MusicAlbumExample();
		musicAlbumExample.or().andMusicIdEqualTo(musicId).andStatusEqualTo(StatusEnum.NORMAL.getStatus());
		List<MusicAlbum> musicAlbumList = musicAlbumMapper.selectByExample(musicAlbumExample);
		
		if (musicAlbumList != null && musicAlbumList.size() > 0) {
			List<Long> albumIdList = new ArrayList<Long>();
			for (MusicAlbum musicAlbum : musicAlbumList) {
				if (musicAlbum.getAlbumId() != null) {
					albumIdList.add(musicId);
				}
			}
			if (albumIdList.size() > 0) {
				AlbumExample albumExample = new AlbumExample();
				albumExample.or().andIdIn(albumIdList);
				List<Album> albumList = albumMapper.selectByExample(albumExample);
				if (albumList != null && albumList.size() > 0) {
					return albumList;
				}
			}
		}
		return new ArrayList<Album>();
	}

	public List<MusicInfo> getRecentRecommendMusicList(Integer limit) {
		MusicRecommendExample musicRecommendExample = new MusicRecommendExample();
		musicRecommendExample.setOrderByClause("create_time DESC");
		musicRecommendExample.setStart(0);
		musicRecommendExample.setEnd(limit != null ? limit : recommendLimit);
		List<MusicRecommend> musicRecommendList = musicRecommendMapper.limitSelectByExample(musicRecommendExample);
		if (musicRecommendList != null && musicRecommendList.size() > 0) {
			final List<Long> musicIdList = new ArrayList<Long>();
			for (MusicRecommend musicRecommend : musicRecommendList) {
				if (musicRecommend.getMusicId() != null && !musicIdList.contains(musicRecommend.getMusicId())) {
					musicIdList.add(musicRecommend.getId());
				}
			}
			if (musicIdList.size() > 0) {
				MusicInfoExample musicInfoExample = new MusicInfoExample();
				musicInfoExample.or().andIdIn(musicIdList);
				List<MusicInfo> musicInfoList = musicInfoMapper.selectByExample(musicInfoExample);
				// 保证被推荐的顺序
				Collections.sort(musicInfoList, new Comparator<MusicInfo>() {

					public int compare(MusicInfo o1, MusicInfo o2) {
						return musicIdList.lastIndexOf(o1.getId()) - musicIdList.lastIndexOf(o2.getId());
					}
					
				});
				return musicInfoList;
			}
		}
		return null;
	}

	public List<MusicInfo> getMusicByName(String name) {
		MusicInfoExample musicInfoExample = new MusicInfoExample();
		musicInfoExample.or().andNameLike("%" + name + "%");
		List<MusicInfo> musicInfoList = musicInfoMapper.selectByExample(musicInfoExample);
		return musicInfoList;
	}

	public List<MusicInfo> getMusicListByTag(Long tagId, Integer page, Integer size) {
		MusicTagRelationExample musicTagRelationExample = new MusicTagRelationExample();
		musicTagRelationExample.or().andIdEqualTo(tagId);
		musicTagRelationExample.setStart(page * size);
		musicTagRelationExample.setEnd(size);
		List<MusicTagRelation> musicTagRelationList = musicTagRelationMapper.selectByExample(musicTagRelationExample);
		if (musicTagRelationList != null && musicTagRelationList.size() > 0) {
			List<Long> musicIdList = new ArrayList<Long>();
			for (MusicTagRelation musicTagRelation : musicTagRelationList) {
				if (musicTagRelation.getMusicId() != null) {
					musicIdList.add(musicTagRelation.getMusicId());
				}
			}
			if (musicIdList.size() > 0) {
				MusicInfoExample musicInfoExample = new MusicInfoExample();
				musicInfoExample.or().andIdIn(musicIdList);
				return musicInfoMapper.selectByExample(musicInfoExample);
			}
		}
		return null;
	}

	public List<MusicInfo> getRecommendMusicList(Long userId, Integer page, Integer size) {
		MusicRecommendExample musicRecommendExample = new MusicRecommendExample();
		musicRecommendExample.or().andUserIdEqualTo(userId);
		if (page != null && size != null && page > 0 && size > 0) {
			musicRecommendExample.setStart(page * size);
			musicRecommendExample.setEnd(size);
		}
		List<MusicRecommend> musicRecommendList = musicRecommendMapper.limitSelectByExample(musicRecommendExample);
		if (musicRecommendList != null && musicRecommendList.size() > 0) {
			List<Long> musicIdList = new ArrayList<Long>();
			for (MusicRecommend musicRecommend : musicRecommendList) {
				if (musicRecommend.getMusicId() != null) {
					musicIdList.add(musicRecommend.getMusicId());
				}
			}
			if (musicIdList.size() > 0) {
				MusicInfoExample musicInfoExample = new MusicInfoExample();
				musicInfoExample.or().andIdIn(musicIdList);
				return musicInfoMapper.selectByExample(musicInfoExample);
			}
		}
		return null;
	}

	public List<MusicInfo> getShareMusicList(Long userId, Integer page, Integer size) {
		MusicInfoExample musicInfoExample = new MusicInfoExample();
		musicInfoExample.or().andUserIdEqualTo(userId);
		if (page != null && size != null && page > 0 && size > 0) {
			musicInfoExample.setStart(page * size);
			musicInfoExample.setEnd(size);
		}
		return musicInfoMapper.limitSelectByExample(musicInfoExample);
	}

	public List<MusicInfo> searchRecentUploadMusic(Integer page, Integer size) {
		MusicInfoExample musicInfoExample = new MusicInfoExample();
		musicInfoExample.setStart(page * size);
		musicInfoExample.setEnd(size);
		musicInfoExample.setOrderByClause("id DESC");
		return musicInfoMapper.limitSelectByExample(musicInfoExample);
	}

}








