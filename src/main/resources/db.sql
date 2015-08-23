/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : szmilitary

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-07-20 17:50:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `broadcastlist`
-- ----------------------------
DROP TABLE IF EXISTS `broadcastlist`;
CREATE TABLE `broadcastlist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `broadcast_name` varchar(255) DEFAULT NULL COMMENT '串联单名称',
  `broadcast_ctime` varchar(255) DEFAULT NULL COMMENT '串联单创建时间',
  `weapon_order` int(11) DEFAULT NULL COMMENT '串联单顺序',
  `weapon_id` int(11) DEFAULT NULL COMMENT '武器id',
  `weapon_attr` varchar(10000) DEFAULT NULL COMMENT '武器属性',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `sz_user`
-- ----------------------------
DROP TABLE IF EXISTS `sz_user`;
CREATE TABLE `sz_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `locked` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sz_user
-- ----------------------------
INSERT INTO `sz_user` VALUES ('2', 'admin', '6e2ca1de0bccdafd2a75dc35740317a1', '5109f061b1b2002adecf8814a4680ae8', null, null);

-- ----------------------------
-- Table structure for `weapon`
-- ----------------------------
DROP TABLE IF EXISTS `weapon`;
CREATE TABLE `weapon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `weapon_name` varchar(255) DEFAULT NULL COMMENT '武器名称',
  `weapon_country` varchar(255) DEFAULT NULL COMMENT '武器制造国',
  `weapon_category` varchar(255) DEFAULT NULL COMMENT '武器类别',
  `weapon_attr` varchar(10000) DEFAULT NULL COMMENT '武器属性',
  `weapon_thumbnail` varchar(255) DEFAULT NULL COMMENT '武器缩略图地址',
  `weapon_model` varchar(255) DEFAULT NULL COMMENT '武器模型地址',
  `weapon_texture` varchar(255) DEFAULT NULL,
  `weapon_gh_path` varchar(255) DEFAULT NULL COMMENT 'gh路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
