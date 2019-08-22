/*
Navicat MySQL Data Transfer

Source Server         : 1、127.0.0.1（本地测试）
Source Server Version : 100036
Source Host           : 127.0.0.1:3306
Source Database       : db_sszh

Target Server Type    : MYSQL
Target Server Version : 100036
File Encoding         : 65001

Date: 2019-08-22 09:45:05
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
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `parent_id` int(11) NOT NULL DEFAULT '1' COMMENT '父菜单ID，取值：t_sys_menu.id，一级菜单为0',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `url` varchar(200) NOT NULL DEFAULT '#' COMMENT '菜单URL，如：sys/menu',
  `perms` varchar(500) NOT NULL DEFAULT '#' COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(4) NOT NULL DEFAULT '1' COMMENT '类型   1：菜单 2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `status` int(4) NOT NULL DEFAULT '-1' COMMENT '菜单状态：-1 禁用  1启用',
  `system_mark` int(4) NOT NULL DEFAULT '1' COMMENT '系统标识：1 运营  2 商户PC',
  `order_num` int(4) NOT NULL DEFAULT '1' COMMENT '排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8 COMMENT='运营系统-系统菜单表';

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1000', '0', '系统管理', '#', '#', '1', null, '1', '1', '1', '2019-08-16 09:33:38');
INSERT INTO `t_sys_menu` VALUES ('1001', '1000', '菜单管理', 'sysMenu/index', '#', '1', null, '1', '1', '1', '2019-08-16 09:34:58');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` varchar(225) NOT NULL COMMENT '主键ID',
  `account` varchar(255) NOT NULL COMMENT '登陆账号',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `pass_word` varchar(255) NOT NULL COMMENT '密码：（由原始密码+随机串组合）MD5值组合',
  `ran_str` varchar(255) NOT NULL COMMENT '随机字符串：由创建密码时随机生成的UUID',
  `mobile_phone` varchar(20) NOT NULL COMMENT '手机号',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `photo_url` varchar(1000) DEFAULT NULL COMMENT '头像地址',
  `status` int(4) NOT NULL DEFAULT '0' COMMENT '用户状态(0:未激活，1:正常使用,2:冻结用户,3：用户被删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1000', 'admin', '张三', '123456', '2f514dd792b248f8956cde24e12236dc', '15388888888', '123@sszh.com', null, '0');
