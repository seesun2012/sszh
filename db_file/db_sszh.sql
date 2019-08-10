/*
Navicat MySQL Data Transfer

Source Server         : 1、127.0.0.1（本地测试）
Source Server Version : 100036
Source Host           : 127.0.0.1:3306
Source Database       : db_sszh

Target Server Type    : MYSQL
Target Server Version : 100036
File Encoding         : 65001

Date: 2019-08-10 21:05:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) NOT NULL,
  `unit_id` varchar(32) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `create_time` varchar(30) NOT NULL,
  `app_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logger
-- ----------------------------
INSERT INTO `t_logger` VALUES ('6', '3d47494704c537', 'e6c5ed5830519f20ad5b81298d49851f', 'Transaction', 'business code error', '2019-08-10 21:02:28 691', 'sszh-server-sso:7077');
INSERT INTO `t_logger` VALUES ('7', '3d47494704c537', 'b36c101794d256b2da8fbeb90fc8daba', 'Transaction', 'business code error', '2019-08-10 21:02:28 724', 'sszh-server-order:7078');
INSERT INTO `t_logger` VALUES ('8', '3d475eb574c537', 'b36c101794d256b2da8fbeb90fc8daba', 'Transaction', 'business code error', '2019-08-10 21:03:41 634', 'sszh-server-order:7078');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(255) NOT NULL,
  `order_no` varchar(255) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`,`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('01U64GmsjGDqSeHmVFc', 'SH1565434757256', '88888.88');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1000', '张三');
