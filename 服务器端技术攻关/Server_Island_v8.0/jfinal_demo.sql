/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : jfinal_demo

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2016-12-01 08:41:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activities`
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities` (
  `id` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `desciption` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `organizer` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `time` date DEFAULT NULL,
  `site` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES ('1', 'activity01', 'it\'s the desciption about the activity!', 'organizer01', '2016-11-28', 'where to hold the activity!');
INSERT INTO `activities` VALUES ('2', 'activity02', 'it\'s the desciption about the activity!', 'organizer01', '2016-11-29', 'where to hold the activity!');
INSERT INTO `activities` VALUES ('3', 'activity03', 'it\'s the desciption about the activity!', 'organizer01', '2016-11-30', 'where to hold the activity!');
INSERT INTO `activities` VALUES ('4', '活动四', '活动描述四', '组织者四', '2016-12-01', '图书馆吧！');
INSERT INTO `activities` VALUES ('5', '活动五', '活动描述五', '组织者五', '2016-12-02', '软件学院！');

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here');
INSERT INTO `blog` VALUES ('2', 'test 1', 'test 1');
INSERT INTO `blog` VALUES ('3', 'test 2', 'test 2');
INSERT INTO `blog` VALUES ('4', 'test 3', 'test 3');
INSERT INTO `blog` VALUES ('5', 'test 4', 'test 4');

-- ----------------------------
-- Table structure for `collection`
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `id_goods` int(11) NOT NULL DEFAULT '0',
  `id_user` int(11) NOT NULL DEFAULT '0',
  `status` bit(1) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_goods`,`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('1', '20161128', '', '2016-11-28 19:24:33');
INSERT INTO `collection` VALUES ('2', '20161129', '', '2016-11-29 19:24:38');
INSERT INTO `collection` VALUES ('3', '20161130', '', '2016-11-30 19:24:41');
INSERT INTO `collection` VALUES ('4', '20161131', '', '2016-11-27 23:24:47');
INSERT INTO `collection` VALUES ('5', '20161132', '', '2016-11-26 16:24:53');
INSERT INTO `collection` VALUES ('6', '20161130', '', '2016-11-05 20:56:23');

-- ----------------------------
-- Table structure for `feedback`
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES ('1', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('2', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('3', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('4', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('5', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('6', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('7', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('8', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('9', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('10', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('11', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('12', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('13', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('14', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('15', '2', '你好', '2016-01-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('16', '2', '你好', '2016-10-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('17', '2', '你好', '2016-10-25 00:00:00', '');
INSERT INTO `feedback` VALUES ('18', '2', '你好', '2016-10-25 11:09:08', '');
INSERT INTO `feedback` VALUES ('200', '2', '你好', '2016-11-25 11:13:01', '');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `name` varchar(20) NOT NULL DEFAULT '',
  `img` varchar(100) DEFAULT NULL,
  `str` varchar(100) DEFAULT NULL,
  `seller` varchar(100) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('HuaWei', 'http://10.7.1.4/201408suncheng/Img/phone01.jpg', 'it\'s very beautifu', 'JD', '3688');
INSERT INTO `goods` VALUES ('HuaWeiP8', 'http://10.7.1.4/201408suncheng/Img/phone03.jpg', 'it\'s unique.', 'Tmall', '3588');
INSERT INTO `goods` VALUES ('HuaweiP9', 'http://10.7.1.4/201408suncheng/Img/phone02.jpg', 'it has double camaras.', 'JD', '3788');
INSERT INTO `goods` VALUES ('HuaWeiP9 Plus', 'http://10.7.1.4/201408suncheng/Img/phone04.jpg', 'Well, Super Amode', 'JD', '3888');
INSERT INTO `goods` VALUES ('Lenovo', 'http://10.7.1.4/201408suncheng/Img/phone05.jpg', 'it\'s expensive!', 'Lenovo', '2288');
INSERT INTO `goods` VALUES ('LenovoX2', 'http://10.7.1.4/201408suncheng/Img/phone07.jpg', 'Camaras!', 'Lenovo', '2344');
INSERT INTO `goods` VALUES ('LenovoX3', 'http://10.7.1.4/201408suncheng/Img/phone06.jpg', 'Double Camaras.', 'JD', '2369');
INSERT INTO `goods` VALUES ('LenovoX4', 'http://10.7.1.4/201408suncheng/Img/phone08.jpg', 'Funny!', 'Lenovo', '2388');

-- ----------------------------
-- Table structure for `goos_details`
-- ----------------------------
DROP TABLE IF EXISTS `goos_details`;
CREATE TABLE `goos_details` (
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `price` int(11) DEFAULT NULL,
  `details` varchar(200) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of goos_details
-- ----------------------------
INSERT INTO `goos_details` VALUES ('Lenovo_Y40', '4999', '全新的GTX860M独立显卡和超高性能的i5处理器也让各路玩家更期待它的游戏表现');
INSERT INTO `goos_details` VALUES ('Lenovo_Y50', '5699', '我们今天要说的这台Y50采用了更轻薄的机身，整体设计与之前的Y系列相比有很大变化');
INSERT INTO `goos_details` VALUES ('Lenovo_Y700', '5799', '联想Y50采用了联想此前从未在Y系列上使用过的轻薄设计风格，流线型机身看起来像是一部稳稳停住的超跑');
INSERT INTO `goos_details` VALUES ('YOGA710-14', '6999', '2G独显翻转IPS触摸屏 ');
INSERT INTO `goos_details` VALUES ('小新Air 13 Pro', '5499', '你们期待的8G内存和I7来了！');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` varchar(50) CHARACTER SET utf8 NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `telphone` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `osum` double DEFAULT NULL,
  `pid` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  PRIMARY KEY (`oid`,`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('', '1', null, null, null, '');
INSERT INTO `order` VALUES ('161129212955', '700', '软件学院', '1234566666', '30', '');
INSERT INTO `order` VALUES ('161129213132', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213135', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213223', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213237', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213426', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213429', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213431', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213432', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213433', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161129213434', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('161130100245', '700', '软件学院', '1234566666', '30', '7878788');
INSERT INTO `order` VALUES ('201611292010', '1', '中国', '15230153136', null, '');
INSERT INTO `order` VALUES ('201611292011', '2', 'aaaa', '15230', null, '');
INSERT INTO `order` VALUES ('201611292013', '3', 'aaaaaaa', '15230153', null, '');

-- ----------------------------
-- Table structure for `t_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_detail`;
CREATE TABLE `t_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `price` float DEFAULT NULL,
  `number` float DEFAULT NULL,
  `oid` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_detail
-- ----------------------------
INSERT INTO `t_detail` VALUES ('1', '红富士', '3.5', '10', 'A20160816001');
INSERT INTO `t_detail` VALUES ('2', '牛奶', '3.2', '10', 'A20160816001');
INSERT INTO `t_detail` VALUES ('3', '酸奶', '2.2', '4', 'A20160816001');
INSERT INTO `t_detail` VALUES ('4', '牛奶', '3.2', '5', 'A20160816002');
INSERT INTO `t_detail` VALUES ('5', '酸奶', '2.2', '5', 'A20160816002');
INSERT INTO `t_detail` VALUES ('6', '酸奶', '2.2', '1', '161118194743');
INSERT INTO `t_detail` VALUES ('7', '红富士', '3.5', '1', '161118195453');
INSERT INTO `t_detail` VALUES ('8', '香蕉', '2.7', '1', '161118195453');
INSERT INTO `t_detail` VALUES ('9', '牛奶', '3.2', '1', '161118195453');
INSERT INTO `t_detail` VALUES ('10', '红富士', '3.5', '1', '161118203644');
INSERT INTO `t_detail` VALUES ('11', '香蕉', '2.7', '1', '161118203644');
INSERT INTO `t_detail` VALUES ('12', '红富士', '3.5', '1', '161118203712');
INSERT INTO `t_detail` VALUES ('13', '香蕉', '2.7', '2', '161118203712');
INSERT INTO `t_detail` VALUES ('14', '牛奶', '3.2', '3', '161118203712');
INSERT INTO `t_detail` VALUES ('15', 'Apple', '10', '2', '161122080738');
INSERT INTO `t_detail` VALUES ('16', '我你好', '100', '1', '161122080738');

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` varchar(12) NOT NULL,
  `date` datetime DEFAULT NULL,
  `total` float DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('161118194743', '2016-11-18 19:47:43', '299');
INSERT INTO `t_order` VALUES ('161118195453', '2016-11-18 19:54:53', '5799');
INSERT INTO `t_order` VALUES ('161118203644', '2016-11-18 20:36:44', '2000');
INSERT INTO `t_order` VALUES ('161118203712', '2016-11-18 20:37:12', '4100');
INSERT INTO `t_order` VALUES ('161122080115', '2016-11-22 08:01:15', '5799');
INSERT INTO `t_order` VALUES ('161122080427', '2016-11-22 08:04:27', '5799');
INSERT INTO `t_order` VALUES ('161122080738', '2016-11-22 08:07:38', '40');
INSERT INTO `t_order` VALUES ('A20160816001', '2016-08-16 14:00:00', '176');
INSERT INTO `t_order` VALUES ('A20160816002', '2016-08-17 04:00:00', '76');

-- ----------------------------
-- Table structure for `t_product`
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `price` float DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `type` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `location` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`pid`,`name`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('1', 'Apple', '10', '中文描述one', 'phone', '中文发布地点', '2016-11-11');
INSERT INTO `t_product` VALUES ('2', 'Lenovo', '20', '中文描述two', '电脑', '中文发布地点', '2016-11-12');
INSERT INTO `t_product` VALUES ('3', 'Asus', '100', '中文描述three', '电脑', '中文发布地点', '2016-11-13');
INSERT INTO `t_product` VALUES ('8', '浣犲ソ锛孞ava', '100', '中文描述four', '水果', '中文发布地点', '2016-11-14');
INSERT INTO `t_product` VALUES ('9', '华硕', '55', '中文描述five', '电脑', '中文发布地点', '2016-11-15');
INSERT INTO `t_product` VALUES ('89', '华为', '55', '中文描述seven', '手机', '中文发布地点', '2016-11-17');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `nickname` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `password` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `power` int(11) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('default nick', '周杰伦', '123abcdefg', '0');
INSERT INTO `user` VALUES ('第三极', '喊喊', '123aaaa', null);
INSERT INTO `user` VALUES ('第三极', '孙', '123aaaa', null);
INSERT INTO `user` VALUES ('第三极', '孙铖', '123aaaa', null);
INSERT INTO `user` VALUES ('第三极', '孙铖铖', '123aaaa', null);
INSERT INTO `user` VALUES ('default nick', '杰伦', '123abcdefg', '0');
INSERT INTO `user` VALUES ('中文昵称hello', '用户名一', '123aaa', '1');
INSERT INTO `user` VALUES ('中文昵称002', '用户名三', '123aaa', '0');
INSERT INTO `user` VALUES ('中文昵称001', '用户名二', '123aaa', '0');
INSERT INTO `user` VALUES ('中文昵称4', '用户名五', '123aaa', '0');
INSERT INTO `user` VALUES ('中文昵称5', '用户名六', '123aaa', '1');
INSERT INTO `user` VALUES ('中文昵称3', '用户名四', '123aaa', '0');
