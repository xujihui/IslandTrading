/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : mr.liu

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2016-12-15 14:09:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `islandtrading_activity`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_activity`;
CREATE TABLE `islandtrading_activity` (
  `Activity_Id` int(11) NOT NULL DEFAULT '0',
  `Activity_Content` varchar(255) DEFAULT NULL,
  `Activity_Organizer` varchar(255) DEFAULT NULL,
  `Activity_Time` datetime DEFAULT NULL,
  `Activity_Site` varchar(255) DEFAULT NULL,
  `Activity_Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Activity_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_activity
-- ----------------------------
INSERT INTO `islandtrading_activity` VALUES ('1', '还是相亲', '第三极', '2016-12-16 21:03:04', '一期三号楼', '活动名称');
INSERT INTO `islandtrading_activity` VALUES ('2', '相亲内容', '刘鑫', '2016-12-14 20:28:46', '科技楼90楼', '相亲');

-- ----------------------------
-- Table structure for `islandtrading_classify`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_classify`;
CREATE TABLE `islandtrading_classify` (
  `Classify_Id` int(11) NOT NULL DEFAULT '0',
  `Classify_Name` varchar(255) DEFAULT NULL,
  `Classify_Image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Classify_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_classify
-- ----------------------------
INSERT INTO `islandtrading_classify` VALUES ('1', '手机Phone', 'www.baidu.comnnaia');
INSERT INTO `islandtrading_classify` VALUES ('2', '电脑PC', null);

-- ----------------------------
-- Table structure for `islandtrading_collect`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_collect`;
CREATE TABLE `islandtrading_collect` (
  `Collect_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Collect_Status` tinyint(1) DEFAULT NULL,
  `Collect_Time` datetime DEFAULT NULL,
  PRIMARY KEY (`Collect_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_collect
-- ----------------------------
INSERT INTO `islandtrading_collect` VALUES ('1', '0', '2016-12-14 21:10:26');
INSERT INTO `islandtrading_collect` VALUES ('2', '1', '2016-12-14 21:10:23');

-- ----------------------------
-- Table structure for `islandtrading_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_feedback`;
CREATE TABLE `islandtrading_feedback` (
  `Fb_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Fb_Content` varchar(255) DEFAULT NULL,
  `Fb_Contact` varchar(255) DEFAULT NULL,
  `Fb_Time` datetime DEFAULT NULL,
  `Fb_Status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Fb_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_feedback
-- ----------------------------
INSERT INTO `islandtrading_feedback` VALUES ('1', '中文可', '15236', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('2', '中文可', '15236', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('3', '中文可', '15236', '2016-12-09 20:20:39', '0');

-- ----------------------------
-- Table structure for `islandtrading_order`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_order`;
CREATE TABLE `islandtrading_order` (
  `Order_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Order_Site` varchar(255) DEFAULT NULL,
  `Order_Time` datetime DEFAULT NULL,
  `Order_Status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Order_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_order
-- ----------------------------
INSERT INTO `islandtrading_order` VALUES ('1', '河北师范大学软件学院', '2016-12-11 21:54:25', '1');
INSERT INTO `islandtrading_order` VALUES ('101', '河北师大', '2016-12-08 20:46:55', '0');
INSERT INTO `islandtrading_order` VALUES ('1001', '河北师大', '2016-12-08 20:46:55', '1');

-- ----------------------------
-- Table structure for `islandtrading_product`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_product`;
CREATE TABLE `islandtrading_product` (
  `Product_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Product_Name` varchar(255) NOT NULL,
  `Product_Price` float DEFAULT NULL,
  `Product_Describe` varchar(255) DEFAULT NULL,
  `Product_Image` varchar(255) DEFAULT NULL,
  `Product_Time` datetime DEFAULT NULL,
  `Product_Site` varchar(255) DEFAULT NULL,
  `Product_View` int(11) DEFAULT NULL,
  `Product_Positive` int(11) DEFAULT NULL,
  `Product_Status` tinyint(1) DEFAULT NULL,
  `Product_Message` varchar(255) DEFAULT NULL,
  `Product_Top` tinyint(1) DEFAULT NULL,
  `Product_Lagitude` double DEFAULT NULL,
  `Product_Longgitude` double DEFAULT NULL,
  `Product_Type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Product_Id`,`Product_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_product
-- ----------------------------
INSERT INTO `islandtrading_product` VALUES ('1', '三星S7', '50', 'BAO炸手机', 'goods.jpg', '2016-12-14 19:49:55', '发布地点韩国和中国', '366', '88', '1', '给商家留言', '1', '320', '320.3', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('2', 'Apple', '5699', '流畅无卡顿，简约而不简单', null, '2016-12-31 20:04:02', '美国乔布斯发布', '996', '699', '0', '第三极要买', '1', '320.6', '344.4', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('3', '我是刘鑫', '200', '商品描述', 'www.baidu.com', '2016-12-11 21:58:35', '发布位置！！', '100312313', '50', '0', '商品信息', '1', '110.2', '220.3', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('4', 'Lenovo笔记本', '5699', '垃圾联想', null, '2016-12-01 20:14:03', '河北师范大学软件学院', '66', '88', '0', '还行吧，我买了', '0', '330.3', '3.33', '电脑PC');
INSERT INTO `islandtrading_product` VALUES ('35', '手机phone', '2000', '中文商品描述', null, '2016-12-31 10:46:10', '发布地点', '50', '40', '1', null, '0', '350', '340', null);
INSERT INTO `islandtrading_product` VALUES ('36', '手机phone', '2000', '中文商品描述', null, '2016-12-31 10:46:10', '发布地点', '50', '40', '1', null, '0', '350', '340', null);
INSERT INTO `islandtrading_product` VALUES ('37', '手机phone', '2000', '中文商品描述', null, '2016-12-31 10:46:10', '发布地点', '50', '40', '1', null, '0', '350', '340', null);
INSERT INTO `islandtrading_product` VALUES ('39', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', 'ææºPhone');
INSERT INTO `islandtrading_product` VALUES ('40', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', 'ææºPhone');
INSERT INTO `islandtrading_product` VALUES ('41', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('42', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('43', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('44', '三星S8 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '0', null, '1', '350', '340', '手机Phone');
INSERT INTO `islandtrading_product` VALUES ('45', '华硕ASUS', '5222', '华硕电脑', null, '2016-12-15 10:59:19', '上海发布', '88', '78', '1', '商家留言', '0', '333.3', '3.33', '电脑PC');

-- ----------------------------
-- Table structure for `islandtrading_user`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_user`;
CREATE TABLE `islandtrading_user` (
  `User_Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Nickname` varchar(255) DEFAULT NULL,
  `User_Username` varchar(255) DEFAULT NULL,
  `User_Password` varchar(255) DEFAULT NULL,
  `User_Image` varchar(255) DEFAULT NULL,
  `User_Power` int(11) DEFAULT NULL,
  `User_TakingId` varchar(255) DEFAULT NULL,
  `User_Tel` varchar(30) DEFAULT NULL,
  `Hx_Username` varchar(255) DEFAULT NULL,
  `Hx_Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2016011908 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_user
-- ----------------------------
INSERT INTO `islandtrading_user` VALUES ('700', '第三极', '孙铖铖', '1123abc', null, '1', '111111222222', '152', 'huanxinName', '123456abc');
INSERT INTO `islandtrading_user` VALUES ('2016011907', '韩寒', '韩寒的用户名', '1234', null, '0', '15686565', '15230153136', 'a12345', '12345');

-- ----------------------------
-- Table structure for `re_activity_user`
-- ----------------------------
DROP TABLE IF EXISTS `re_activity_user`;
CREATE TABLE `re_activity_user` (
  `Activity_Id` int(11) NOT NULL DEFAULT '0',
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Activity_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_activity_user
-- ----------------------------
INSERT INTO `re_activity_user` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `re_collect_product_user`
-- ----------------------------
DROP TABLE IF EXISTS `re_collect_product_user`;
CREATE TABLE `re_collect_product_user` (
  `Collect_Id` int(11) NOT NULL AUTO_INCREMENT,
  `Product_Id` int(11) DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Collect_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_collect_product_user
-- ----------------------------
INSERT INTO `re_collect_product_user` VALUES ('1', '2', '2014011905');
INSERT INTO `re_collect_product_user` VALUES ('2', '3', '2014011905');

-- ----------------------------
-- Table structure for `re_fb_user`
-- ----------------------------
DROP TABLE IF EXISTS `re_fb_user`;
CREATE TABLE `re_fb_user` (
  `Fb_Id` int(11) NOT NULL DEFAULT '0',
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Fb_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_fb_user
-- ----------------------------

-- ----------------------------
-- Table structure for `re_product_classify`
-- ----------------------------
DROP TABLE IF EXISTS `re_product_classify`;
CREATE TABLE `re_product_classify` (
  `Product_Id` int(11) NOT NULL DEFAULT '0',
  `Classify_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Product_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_product_classify
-- ----------------------------
INSERT INTO `re_product_classify` VALUES ('1', '1');
INSERT INTO `re_product_classify` VALUES ('2', '1');
INSERT INTO `re_product_classify` VALUES ('4', '2');
INSERT INTO `re_product_classify` VALUES ('5', '2');
INSERT INTO `re_product_classify` VALUES ('45', '2');

-- ----------------------------
-- Table structure for `re_product_user`
-- ----------------------------
DROP TABLE IF EXISTS `re_product_user`;
CREATE TABLE `re_product_user` (
  `Product_Id` int(11) NOT NULL DEFAULT '0',
  `User_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Product_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_product_user
-- ----------------------------
INSERT INTO `re_product_user` VALUES ('38', '2016011905');
INSERT INTO `re_product_user` VALUES ('39', '700');
INSERT INTO `re_product_user` VALUES ('44', '700');

-- ----------------------------
-- Table structure for `re_user_order_product`
-- ----------------------------
DROP TABLE IF EXISTS `re_user_order_product`;
CREATE TABLE `re_user_order_product` (
  `User_Id` int(11) NOT NULL DEFAULT '0',
  `Order_Id` int(11) DEFAULT NULL,
  `Product_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_user_order_product
-- ----------------------------
