package com.micro.ss.web.support;
/**
 * @author mapc 
 * @date 2017年7月1日
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.micro.ss.web.dao.AlbumDao;
import com.micro.ss.web.dao.GroupDao;
import com.micro.ss.web.dao.LogDao;
import com.micro.ss.web.dao.MessageDao;
import com.micro.ss.web.dao.MusicAlbumDao;
import com.micro.ss.web.dao.MusicCommentaryDao;
import com.micro.ss.web.dao.MusicInfoDao;
import com.micro.ss.web.dao.MusicRecommendDao;
import com.micro.ss.web.dao.MusicTagDao;
import com.micro.ss.web.dao.MusicTagRelationDao;
import com.micro.ss.web.dao.TopicsCommentaryDao;
import com.micro.ss.web.dao.UserCollectionDao;
import com.micro.ss.web.dao.UserGroupRelationDao;
import com.micro.ss.web.dao.UserInfoDao;
import com.micro.ss.web.dao.UserListenRecordDao;
import com.micro.ss.web.dao.UserMusicScoreDao;
import com.micro.ss.web.dao.UserRelationDao;
import com.micro.ss.web.dao.UserTagDao;

public abstract class ServiceSupport extends LoggerSupport {

	@Autowired
	protected AlbumDao albumDao;
	
	@Autowired
	protected GroupDao groupDao;
	
	@Autowired
	protected LogDao logDao;
	
	@Autowired
	protected MessageDao messageDao;
	
	@Autowired
	protected MusicAlbumDao musicAlbumDao;
	
	@Autowired
	protected MusicCommentaryDao musicCommentaryDao;
	
	@Autowired
	protected MusicInfoDao musicInfoDao;
	
	@Autowired
	protected MusicRecommendDao musicRecommendDao;
	
	@Autowired
	protected MusicTagDao musicTagDao;
	
	@Autowired
	protected MusicTagRelationDao musicTagRelationDao;
	
	@Autowired
	protected TopicsCommentaryDao topicsCommentaryDao;
	
	@Autowired
	protected UserCollectionDao userCollectionDao;
	
	@Autowired
	protected UserInfoDao userInfoDao;
	
	@Autowired
	protected UserListenRecordDao userListenRecordDao;
	
	@Autowired
	protected UserMusicScoreDao userMusicScoreDao;
	
	@Autowired
	protected UserRelationDao userRelationDao;
	
	@Autowired
	protected UserGroupRelationDao userGroupRelationDao;
	
	@Autowired
	protected UserTagDao userTagDao;
//	@Value("${com.micro.ss.recommend.limit}")
	protected Integer recommendLimit = 20;

}
