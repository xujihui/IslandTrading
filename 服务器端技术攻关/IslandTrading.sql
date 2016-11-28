/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : market_db

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2016-11-27 19:18:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `island_activity_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_activity_b`;
CREATE TABLE `island_activity_b` (
  `id` int(20) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `content` varchar(255) DEFAULT '',
  `organizer` varchar(255) DEFAULT '',
  `time` datetime DEFAULT NULL,
  `site` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_activity_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_classify_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_classify_b`;
CREATE TABLE `island_classify_b` (
  `id` int(20) NOT NULL,
  `type` varchar(255) DEFAULT '',
  `image` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_classify_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_collect_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_collect_b`;
CREATE TABLE `island_collect_b` (
  `product_id` int(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT '',
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `island_user_b` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `island_product_b` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_collect_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_fb_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_fb_b`;
CREATE TABLE `island_fb_b` (
  `id` int(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `content` varchar(255) DEFAULT '',
  `contact` varchar(255) DEFAULT '',
  `time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `user_id_1` (`user_id`),
  CONSTRAINT `user_id_1` FOREIGN KEY (`user_id`) REFERENCES `island_user_b` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_fb_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_order_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_order_b`;
CREATE TABLE `island_order_b` (
  `id` int(20) NOT NULL,
  `user_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT '',
  `contact` int(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT '',
  `price` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `product_order` (`product_id`),
  CONSTRAINT `product_order` FOREIGN KEY (`product_id`) REFERENCES `island_product_b` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_order_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_product_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_product_b`;
CREATE TABLE `island_product_b` (
  `id` int(20) NOT NULL,
  `name` varchar(255) DEFAULT '',
  `price` float DEFAULT NULL,
  `describe` varchar(255) DEFAULT '',
  `image` varchar(255) DEFAULT '',
  `classify_id` int(20) DEFAULT NULL,
  `site` varchar(255) DEFAULT '',
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_type` (`classify_id`),
  CONSTRAINT `classify_id` FOREIGN KEY (`classify_id`) REFERENCES `island_classify_b` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of island_product_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_slide_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_slide_b`;
CREATE TABLE `island_slide_b` (
  `id` int(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `slide_id` FOREIGN KEY (`id`) REFERENCES `island_product_b` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_slide_b
-- ----------------------------

-- ----------------------------
-- Table structure for `island_user_b`
-- ----------------------------
DROP TABLE IF EXISTS `island_user_b`;
CREATE TABLE `island_user_b` (
  `id` int(20) NOT NULL,
  `nickname` varchar(255) DEFAULT '',
  `username` varchar(255) DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `garvatar` varchar(255) DEFAULT '',
  `power` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of island_user_b
-- ----------------------------

-- ----------------------------
-- Table structure for `t_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_detail`;
CREATE TABLE `t_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `number` float DEFAULT NULL,
  `oid` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_detail
-- ----------------------------
INSERT INTO `t_detail` VALUES ('1', '红富士', '3.5', '10', 'A20160816001');
INSERT INTO `t_detail` VALUES ('2', '牛奶', '3.2', '10', 'A20160816001');
INSERT INTO `t_detail` VALUES ('3', '酸奶', '2.2', '4', 'A20160816001');
INSERT INTO `t_detail` VALUES ('4', '牛奶', '3.2', '5', 'A20160816002');
INSERT INTO `t_detail` VALUES ('5', '酸奶', '2.2', '5', 'A20160816002');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` varchar(12) NOT NULL DEFAULT '',
  `date` datetime DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('A20160816001', '2016-08-16 14:23:50', '176');
INSERT INTO `t_order` VALUES ('A20160816002', '2016-08-17 04:20:50', '76');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `pid` varchar(6) NOT NULL DEFAULT '',
  `name` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('123458', '牛奶', '3.2');
