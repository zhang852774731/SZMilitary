/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : szmilitary

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-07-09 16:41:04
*/

SET FOREIGN_KEY_CHECKS=0;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;
