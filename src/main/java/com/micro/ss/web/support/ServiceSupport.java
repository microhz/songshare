package com.micro.ss.web.support;
/**
 * @author mapc 
 * @date 2017年7月1日
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.micro.ss.web.data.mapper.AlbumMapper;
import com.micro.ss.web.data.mapper.GroupMapper;
import com.micro.ss.web.data.mapper.LogMapper;
import com.micro.ss.web.data.mapper.MessageMapper;
import com.micro.ss.web.data.mapper.MusicAlbumMapper;
import com.micro.ss.web.data.mapper.MusicCommentaryMapper;
import com.micro.ss.web.data.mapper.MusicInfoMapper;
import com.micro.ss.web.data.mapper.MusicRecommendMapper;
import com.micro.ss.web.data.mapper.MusicTagMapper;
import com.micro.ss.web.data.mapper.MusicTagRelationMapper;
import com.micro.ss.web.data.mapper.UserCollectionMapper;
import com.micro.ss.web.data.mapper.UserHomeCommentaryMapper;
import com.micro.ss.web.data.mapper.UserInfoMapper;
import com.micro.ss.web.data.mapper.UserListenRecordMapper;
import com.micro.ss.web.data.mapper.UserMusicScoreMapper;
import com.micro.ss.web.data.mapper.UserRelationMapper;

public abstract class ServiceSupport extends LoggerSupport {

	@Autowired
	protected UserInfoMapper userInfoMapper;

	@Autowired
	protected MusicInfoMapper musicInfoMapper;

	@Autowired
	protected MusicRecommendMapper musicRecommendMapper;

	@Autowired
	protected UserCollectionMapper userCollectionMapper;

	@Autowired
	protected UserMusicScoreMapper userMusicScoreMapper;

	@Autowired
	protected MusicCommentaryMapper musicCommentaryMapper;

	@Autowired
	protected UserListenRecordMapper userListenRecordMapper;

	@Autowired
	protected AlbumMapper albumMapper;

	@Autowired
	protected MusicAlbumMapper musicAlbumMapper;

	@Autowired
	protected MusicTagRelationMapper musicTagRelationMapper;

	@Autowired
	protected MusicTagMapper musicTagMapper;

	@Autowired
	protected UserRelationMapper userRelationMapper;

	@Autowired
	protected MessageMapper messageMapper;
	
	@Autowired
	protected UserHomeCommentaryMapper userHomeCommentaryMapper;
	
	@Autowired
	protected GroupMapper groupMapper;
	
	@Autowired
	protected LogMapper logMapper;

//	@Value("${com.micro.ss.recommend.limit}")
	protected Integer recommendLimit = 20;

}
