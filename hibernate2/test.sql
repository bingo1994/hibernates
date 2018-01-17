/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2017-12-28 20:23:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admins`
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `address` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'admin', null, '111111', null, '2017-11-23', null);
INSERT INTO `admins` VALUES ('2', '赵三', 'a', '111111', '女', '2017-11-22', '合肥');
INSERT INTO `admins` VALUES ('3', 'aa', 'aa', '111111', '男', '2017-11-03', 'fd');
INSERT INTO `admins` VALUES ('4', '', '', '', '男', null, '');
INSERT INTO `admins` VALUES ('5', '', '', '', '男', null, '');

-- ----------------------------
-- Table structure for `employee`
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sex` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '李雪', '女', '543342234');
INSERT INTO `employee` VALUES ('2', '赵六', '男', '543342234');
INSERT INTO `employee` VALUES ('3', '赵六', '男', '543342234');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `address` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('5', 'd', 'd', 'd', 'M', 'dd');
INSERT INTO `users` VALUES ('6', 'f', 'f', 'f', '', 'f');

-- ----------------------------
-- Procedure structure for `proc_AdminLogin`
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_AdminLogin`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `proc_AdminLogin`(in param1 varchar(64),in param2 varchar(64))
begin
select * from admin where param1= param2;
end
;;
DELIMITER ;
