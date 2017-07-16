/*
 Navicat MySQL Data Transfer

 Source Server         : st
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 116.62.108.220
 Source Database       : songshare

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : utf-8

 Date: 07/02/2017 13:03:59 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `album`
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(64) DEFAULT NULL,
  `ico_image` varchar(128) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `album_name` (`album_name`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `group`
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(128) DEFAULT NULL,
  `image` varchar(64) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  `creator_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `group_topics`
-- ----------------------------
DROP TABLE IF EXISTS `group_topics`;
CREATE TABLE `group_topics` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `group_id` bigint(128) DEFAULT NULL,
  `status` int(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `log`
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(512) DEFAULT NULL COMMENT 'mongo_id',
  `user_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `title` (`title`,`user_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `message` varchar(256) DEFAULT NULL COMMENT '是否读取，是否失效',
  `sender_id` bigint(128) DEFAULT NULL,
  `recieve_id` bigint(128) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sender_id` (`sender_id`,`recieve_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `music_ablum`
-- ----------------------------
DROP TABLE IF EXISTS `music_ablum`;
CREATE TABLE `music_ablum` (
  `id` bigint(128) NOT NULL,
  `music_id` bigint(128) DEFAULT NULL,
  `album_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator_id` bigint(128) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `music_id` (`music_id`,`album_id`,`creator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `music_commentary`
-- ----------------------------
DROP TABLE IF EXISTS `music_commentary`;
CREATE TABLE `music_commentary` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `target_id` bigint(128) NOT NULL COMMENT '专辑或者音乐id',
  `commentary` varchar(256) DEFAULT NULL,
  `user_id` bigint(128) NOT NULL,
  `status` int(16) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `type` int(16) NOT NULL COMMENT '音乐还是专辑',
  PRIMARY KEY (`id`),
  KEY `target` (`target_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `music_info`
-- ----------------------------
DROP TABLE IF EXISTS `music_info`;
CREATE TABLE `music_info` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `singer` varchar(64) DEFAULT NULL,
  `singer_album` varchar(64) DEFAULT NULL,
  `file_url` varchar(256) DEFAULT NULL,
  `comment` varchar(256) DEFAULT NULL,
  `lyrics_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `ext` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `music` (`name`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `music_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `music_recommend`;
CREATE TABLE `music_recommend` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `music_id` bigint(128) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `music_id` (`music_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `music_tag`
-- ----------------------------
DROP TABLE IF EXISTS `music_tag`;
CREATE TABLE `music_tag` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(64) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `music_tag_relation`
-- ----------------------------
DROP TABLE IF EXISTS `music_tag_relation`;
CREATE TABLE `music_tag_relation` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `tag_id` bigint(128) DEFAULT NULL,
  `music_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tag_id` (`tag_id`,`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `topics_commentary`
-- ----------------------------
DROP TABLE IF EXISTS `topics_commentary`;
CREATE TABLE `topics_commentary` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `topics_id` bigint(128) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `commentary` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `topics_id` (`topics_id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_collection`
-- ----------------------------
DROP TABLE IF EXISTS `user_collection`;
CREATE TABLE `user_collection` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `music_id` bigint(128) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL COMMENT '收藏，推荐',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_group_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_group_relation`;
CREATE TABLE `user_group_relation` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(128) DEFAULT NULL,
  `group_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_home_commentary`
-- ----------------------------
DROP TABLE IF EXISTS `user_home_commentary`;
CREATE TABLE `user_home_commentary` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(128) DEFAULT NULL,
  `comment_user_id` bigint(128) DEFAULT NULL,
  `commentary_content` varchar(256) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`comment_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `province_id` int(16) DEFAULT NULL,
  `age` int(16) DEFAULT NULL,
  `photo` varchar(128) DEFAULT NULL,
  `portrait_url` varchar(128) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `wechat` varchar(64) DEFAULT NULL,
  `password` varchar(128) DEFAULT NULL,
  `job` varchar(64) DEFAULT NULL,
  `qq` varchar(64) DEFAULT NULL,
  `page_home` varchar(128) DEFAULT NULL,
  `level` int(64) DEFAULT NULL,
  `register_time` datetime DEFAULT NULL,
  `ext` varchar(256) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`,`province_id`,`age`,`job`,`level`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_listen_record`
-- ----------------------------
DROP TABLE IF EXISTS `user_listen_record`;
CREATE TABLE `user_listen_record` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(128) DEFAULT NULL,
  `music_id` bigint(128) DEFAULT NULL,
  `listen_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`music_id`,`listen_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_music_score`
-- ----------------------------
DROP TABLE IF EXISTS `user_music_score`;
CREATE TABLE `user_music_score` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `music_id` bigint(128) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `score` int(16) DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `music_id` (`music_id`,`user_id`,`score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_relation`
-- ----------------------------
DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `target_user_id` bigint(128) DEFAULT NULL,
  `user_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `target_user_id` (`target_user_id`,`user_id`,`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `user_tag`
-- ----------------------------
DROP TABLE IF EXISTS `user_tag`;
CREATE TABLE `user_tag` (
  `id` bigint(128) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(128) DEFAULT NULL,
  `tag_id` bigint(128) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `status` int(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
