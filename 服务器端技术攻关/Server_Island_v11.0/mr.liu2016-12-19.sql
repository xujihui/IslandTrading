/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : mr.liu

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2016-12-19 21:34:30
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
  `Activity_Img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Activity_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_activity
-- ----------------------------
INSERT INTO `islandtrading_activity` VALUES ('1', '还是相亲', '第三极', '2016-12-16 21:03:04', '一期三号楼', '活动名称', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=2');
INSERT INTO `islandtrading_activity` VALUES ('2', '相亲内容', '刘鑫', '2016-12-14 20:28:46', '科技楼90楼', '相亲', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=1');
INSERT INTO `islandtrading_activity` VALUES ('3', '活动内容三', '徐继辉', '2016-12-19 14:29:15', '科技楼一座', '活动名称一二三', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=1');
INSERT INTO `islandtrading_activity` VALUES ('4', '活动内容四', '韩晨', '2016-12-20 14:29:59', '科技楼四座', '活动名称四', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=1');

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
INSERT INTO `islandtrading_classify` VALUES ('1', '女装', '');
INSERT INTO `islandtrading_classify` VALUES ('2', '男装', null);
INSERT INTO `islandtrading_classify` VALUES ('3', '自行车', null);
INSERT INTO `islandtrading_classify` VALUES ('4', '手机', null);
INSERT INTO `islandtrading_classify` VALUES ('5', '电脑', null);
INSERT INTO `islandtrading_classify` VALUES ('6', '3C数码', null);
INSERT INTO `islandtrading_classify` VALUES ('7', '鞋包', null);
INSERT INTO `islandtrading_classify` VALUES ('8', '化妆品', null);
INSERT INTO `islandtrading_classify` VALUES ('9', '文具', null);
INSERT INTO `islandtrading_classify` VALUES ('10', '图书', null);
INSERT INTO `islandtrading_classify` VALUES ('11', '技能服务', null);
INSERT INTO `islandtrading_classify` VALUES ('12', '其他', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_feedback
-- ----------------------------
INSERT INTO `islandtrading_feedback` VALUES ('1', '中文可', '15236', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('2', '中文可', '15236', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('3', '中文可', '15236', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('4', '中文可以', '1523015666', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('5', '中文可以', '1523015666', '2016-12-09 20:20:39', '0');
INSERT INTO `islandtrading_feedback` VALUES ('14', '', '', '2016-12-19 17:21:01', '0');
INSERT INTO `islandtrading_feedback` VALUES ('15', '', '', '2016-12-19 17:21:03', '0');
INSERT INTO `islandtrading_feedback` VALUES ('16', '', '', '2016-12-19 17:21:33', '0');
INSERT INTO `islandtrading_feedback` VALUES ('17', '', '', '2016-12-19 17:22:40', '0');
INSERT INTO `islandtrading_feedback` VALUES ('18', '', '', '2016-12-19 17:24:55', '0');
INSERT INTO `islandtrading_feedback` VALUES ('19', '', '', '2016-12-19 17:26:23', '0');
INSERT INTO `islandtrading_feedback` VALUES ('20', '', '', '2016-12-19 17:28:16', '0');
INSERT INTO `islandtrading_feedback` VALUES ('21', '哈哈哈哈', '111', '2016-12-19 17:30:46', '0');

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
INSERT INTO `islandtrading_order` VALUES ('2', '河北师大', '2016-12-08 20:46:55', '0');
INSERT INTO `islandtrading_order` VALUES ('3', '河北师大', '2016-12-08 20:46:55', '1');

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
  `Product_Image_Url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Product_Id`,`Product_Name`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_product
-- ----------------------------
INSERT INTO `islandtrading_product` VALUES ('1', '三星S7', '50', 'BAO炸手机', '1.jpg', '2016-12-14 19:49:55', '发布地点韩国和中国', '366', '88', '1', '给商家留言', '1', '114.524492', '38.000094', '手机', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=1');
INSERT INTO `islandtrading_product` VALUES ('2', 'Apple', '5699', '流畅无卡顿，简约而不简单', '2.jpg', '2016-12-31 20:04:02', '美国乔布斯发布', '996', '699', '1', '第三极要买', '1', '114.528975', '38.000183', '手机', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=2');
INSERT INTO `islandtrading_product` VALUES ('3', 'Oppo', '200', '商品描述', '3.jpg', '2016-12-11 21:58:35', '发布位置！！', '100312313', '50', '0', '商品信息', '1', '114.521514', '38.004288', '手机', 'http://10.7.88.37:8080/IslandTrading/analysis/downloadImg?Product_Id=3');
INSERT INTO `islandtrading_product` VALUES ('4', 'Find7', '5699', '垃圾联想', null, '2016-12-01 20:14:03', '河北师范大学软件学院', '66', '88', '0', '还行吧，我买了', '0', '330.3', '3.33', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('35', '女装001', '2000', '中文商品描述', 'IMG_20161219_203145.jpg', '2016-12-31 10:46:10', '发布地点', '50', '40', '1', null, '0', '350', '340', '女装', 'http://192.168.194.2:8080/IslandTrading/analysis/downloadImg?Product_Id=35');
INSERT INTO `islandtrading_product` VALUES ('36', '女装002', '2000', '中文商品描述', 'IMG_20161208_173011.jpg', '2016-12-31 10:46:10', '发布地点', '50', '40', '1', null, '0', '350', '340', '女装', 'http://192.168.194.2:8080/IslandTrading/analysis/downloadImg?Product_Id=36');
INSERT INTO `islandtrading_product` VALUES ('37', '女装003', '2000', '中文商品描述', null, '2016-12-31 10:46:10', '发布地点', '50', '40', '1', null, '0', '350', '340', '女装', null);
INSERT INTO `islandtrading_product` VALUES ('41', '男装001', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '男装', null);
INSERT INTO `islandtrading_product` VALUES ('42', '自行车001', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '自行车', null);
INSERT INTO `islandtrading_product` VALUES ('43', '3C数码001', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '3C数码', null);
INSERT INTO `islandtrading_product` VALUES ('44', '鞋包001', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '0', null, '1', '350', '340', '鞋包', null);
INSERT INTO `islandtrading_product` VALUES ('45', '法兰琳卡', '5222', '华硕电脑', null, '2016-12-15 10:59:19', '上海发布', '88', '78', '1', '商家留言', '0', '333.3', '3.33', '化妆品', null);
INSERT INTO `islandtrading_product` VALUES ('47', 'C++', '5333', '中文商品描述', null, null, null, null, null, null, null, null, null, null, '图书', null);
INSERT INTO `islandtrading_product` VALUES ('48', '其他', null, '中文商品描述', null, null, null, null, null, null, null, null, null, null, '其他分类', null);
INSERT INTO `islandtrading_product` VALUES ('49', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('50', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('51', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('52', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('53', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('54', '华为mate9', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('55', '三星S5 G', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('56', '三星S5 G', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('57', '三星S5 G9008V', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('58', '三星S5', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('59', '三星S51', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('60', '三星S52', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('61', '三星S53', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('62', '三星S54', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);
INSERT INTO `islandtrading_product` VALUES ('63', '三星S15', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机Phone', null);
INSERT INTO `islandtrading_product` VALUES ('79', '乌拉拉啦啦啦', '2000', '中文商品描述', null, '2016-12-15 10:46:10', '软件学院', '50', '40', '1', null, '0', '350', '340', '手机', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2016011909 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_user
-- ----------------------------
INSERT INTO `islandtrading_user` VALUES ('700', '第三极', '孙铖铖', '1123abc', null, '1', '111111222222', '152', 'huanxinName', '123456abc');
INSERT INTO `islandtrading_user` VALUES ('800', '用户800', '用户800', '123', null, null, '1111', '10086', 'a123', '123');
INSERT INTO `islandtrading_user` VALUES ('2016011907', '韩寒', '韩寒的用户名', '1234', null, '0', '15686565', '15230153136', 'a12345', '12345');
INSERT INTO `islandtrading_user` VALUES ('2016011908', null, null, null, null, null, null, null, null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_collect_product_user
-- ----------------------------
INSERT INTO `re_collect_product_user` VALUES ('1', '2', '2014011905');
INSERT INTO `re_collect_product_user` VALUES ('2', '3', '2014011905');
INSERT INTO `re_collect_product_user` VALUES ('3', '1', '2014011905');

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
INSERT INTO `re_product_classify` VALUES ('1', '4');
INSERT INTO `re_product_classify` VALUES ('2', '4');
INSERT INTO `re_product_classify` VALUES ('3', '4');
INSERT INTO `re_product_classify` VALUES ('4', '4');
INSERT INTO `re_product_classify` VALUES ('35', '1');
INSERT INTO `re_product_classify` VALUES ('36', '1');
INSERT INTO `re_product_classify` VALUES ('37', '1');
INSERT INTO `re_product_classify` VALUES ('41', '2');
INSERT INTO `re_product_classify` VALUES ('42', '3');
INSERT INTO `re_product_classify` VALUES ('43', '6');
INSERT INTO `re_product_classify` VALUES ('44', '7');
INSERT INTO `re_product_classify` VALUES ('45', '8');
INSERT INTO `re_product_classify` VALUES ('47', '10');
INSERT INTO `re_product_classify` VALUES ('48', '12');

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
INSERT INTO `re_product_user` VALUES ('1', '800');
INSERT INTO `re_product_user` VALUES ('2', '800');
INSERT INTO `re_product_user` VALUES ('3', '800');
INSERT INTO `re_product_user` VALUES ('38', '2016011905');
INSERT INTO `re_product_user` VALUES ('44', '700');
INSERT INTO `re_product_user` VALUES ('49', '700');
INSERT INTO `re_product_user` VALUES ('54', '700');
INSERT INTO `re_product_user` VALUES ('55', '700');
INSERT INTO `re_product_user` VALUES ('58', '700');
INSERT INTO `re_product_user` VALUES ('59', '700');
INSERT INTO `re_product_user` VALUES ('60', '700');
INSERT INTO `re_product_user` VALUES ('61', '700');
INSERT INTO `re_product_user` VALUES ('62', '700');
INSERT INTO `re_product_user` VALUES ('63', '700');

-- ----------------------------
-- Table structure for `re_user_order_product`
-- ----------------------------
DROP TABLE IF EXISTS `re_user_order_product`;
CREATE TABLE `re_user_order_product` (
  `User_Id` int(11) NOT NULL DEFAULT '0',
  `Order_Id` int(11) NOT NULL DEFAULT '0',
  `Product_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Order_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_user_order_product
-- ----------------------------
INSERT INTO `re_user_order_product` VALUES ('800', '1', '1');
INSERT INTO `re_user_order_product` VALUES ('700', '2', '2');
INSERT INTO `re_user_order_product` VALUES ('700', '3', '1');
INSERT INTO `re_user_order_product` VALUES ('700', '4', '3');
