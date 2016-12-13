/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : islandtrading_database

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2016-12-12 23:40:08
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
  `Activity_Time` varchar(255) DEFAULT NULL,
  `Activity_Site` varchar(255) DEFAULT NULL,
  `Activity_Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Activity_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_activity
-- ----------------------------
INSERT INTO `islandtrading_activity` VALUES ('1', 'ä¸èµ·å¤´èé£æ´', 'å²ä¹°å²åé¡¹ç®ç»', '5453535大气污染', 'æ²³åå¸èå¤§å­¦', 'è®¨è®ºé¡¹ç®');

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
INSERT INTO `islandtrading_classify` VALUES ('2', 'æé¥°', 'www.baidu.comnnaiah');

-- ----------------------------
-- Table structure for `islandtrading_collect`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_collect`;
CREATE TABLE `islandtrading_collect` (
  `Collect_Id` int(11) NOT NULL DEFAULT '0',
  `Collect_Status` varchar(255) DEFAULT NULL,
  `Collect_Time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Collect_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_collect
-- ----------------------------
INSERT INTO `islandtrading_collect` VALUES ('1', '112313qDQ', '31313242');

-- ----------------------------
-- Table structure for `islandtrading_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_feedback`;
CREATE TABLE `islandtrading_feedback` (
  `Fb_Id` int(11) NOT NULL DEFAULT '0',
  `Fb_Content` varchar(255) DEFAULT NULL,
  `Fb_Contact` varchar(255) DEFAULT NULL,
  `Fb_Time` datetime DEFAULT NULL,
  `Fb_Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Fb_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_feedback
-- ----------------------------
INSERT INTO `islandtrading_feedback` VALUES ('1', 'BUG提交', '15232157618', '2016-12-11 21:53:32', '1');

-- ----------------------------
-- Table structure for `islandtrading_order`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_order`;
CREATE TABLE `islandtrading_order` (
  `Order_Id` int(11) NOT NULL DEFAULT '0',
  `Order_Site` varchar(255) DEFAULT NULL,
  `Order_Time` datetime DEFAULT NULL,
  `Order_Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Order_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_order
-- ----------------------------
INSERT INTO `islandtrading_order` VALUES ('1', '河北师范大学软件学院', '2016-12-11 21:54:25', '1');

-- ----------------------------
-- Table structure for `islandtrading_product`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_product`;
CREATE TABLE `islandtrading_product` (
  `Product_Id` int(11) NOT NULL DEFAULT '0',
  `Product_Name` varchar(255) DEFAULT NULL,
  `Product_Price` float DEFAULT NULL,
  `Product_Describe` varchar(255) DEFAULT NULL,
  `Product_Image` varchar(255) DEFAULT NULL,
  `Product_Time` varchar(255) DEFAULT NULL,
  `Product_Site` varchar(255) DEFAULT NULL,
  `Product_View` varchar(255) DEFAULT NULL,
  `Product_Positive` varchar(255) DEFAULT NULL,
  `Product_Status` varchar(255) DEFAULT NULL,
  `Product_Message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Product_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_product
-- ----------------------------
INSERT INTO `islandtrading_product` VALUES ('1', 'Ã©ÂÂ®Ã§ÂÂ', '200', 'Ã¨Â¾Â¾Ã¥Â°ÂÃ¤Â¼Â', 'www.baidu.com', '2016-12-11 21:58:35', 'Ã¦Â²Â³Ã¥ÂÂÃ¥Â¸ÂÃ¨ÂÂÃ¥Â¤Â§Ã¥Â­Â¦', '100312313', '50', '你好吗', 'Ã¥ÂÂÃ¥ÂÂÃ¤Â¸ÂÃ©ÂÂ');
INSERT INTO `islandtrading_product` VALUES ('3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3');

-- ----------------------------
-- Table structure for `islandtrading_user`
-- ----------------------------
DROP TABLE IF EXISTS `islandtrading_user`;
CREATE TABLE `islandtrading_user` (
  `User_Id` int(11) NOT NULL DEFAULT '0',
  `User_Nickname` varchar(255) DEFAULT NULL,
  `User_Username` varchar(255) DEFAULT NULL,
  `User_Password` varchar(255) DEFAULT NULL,
  `User_Image` varchar(255) DEFAULT NULL,
  `User_Power` varchar(255) DEFAULT NULL,
  `User_TakingId` varchar(255) DEFAULT NULL,
  `User_Tel` int(30) DEFAULT NULL,
  `Hx_Username` varchar(255) DEFAULT NULL,
  `Hx_Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of islandtrading_user
-- ----------------------------
INSERT INTO `islandtrading_user` VALUES ('21231', 'æµè¯', '3131', '3133', '133', '3131', '313', '31313', '你好啊', '3131');
INSERT INTO `islandtrading_user` VALUES ('31224234', '42424', '242', '42424', '4242', '4234', '424', '424', '424', '4234');

-- ----------------------------
-- Table structure for `re_collect_product_user`
-- ----------------------------
DROP TABLE IF EXISTS `re_collect_product_user`;
CREATE TABLE `re_collect_product_user` (
  `Collect_Id` int(11) DEFAULT NULL,
  `Product_Id` int(11) DEFAULT NULL,
  `User_Id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_collect_product_user
-- ----------------------------

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
