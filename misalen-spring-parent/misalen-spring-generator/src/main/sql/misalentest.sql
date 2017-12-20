/*
Navicat MySQL Data Transfer

Source Server         : 118.190.113.219
Source Server Version : 50717
Source Host           : 118.190.113.219:3306
Source Database       : misalentest

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-20 10:11:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for DATA_SOURCE_MANAGE
-- ----------------------------
DROP TABLE IF EXISTS `DATA_SOURCE_MANAGE`;
CREATE TABLE `DATA_SOURCE_MANAGE` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime NOT NULL,
  `DSNAME` varchar(255) NOT NULL,
  `I_PDIZHI` varchar(255) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `PORT` int(11) NOT NULL,
  `USERNAME` varchar(255) NOT NULL,
  `LEIXING` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DATA_SOURCE_MANAGE
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_ADMIN
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ADMIN`;
CREATE TABLE `SYS_ADMIN` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `LOGIN_PWD` varchar(50) NOT NULL COMMENT '登陆密码',
  `NICKNAME` varchar(50) DEFAULT '' COMMENT '昵称',
  `USERNAME` varchar(50) NOT NULL COMMENT '用户名',
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ADMIN
-- ----------------------------
INSERT INTO `SYS_ADMIN` VALUES ('402881e45f430abb015f432105b50006', '2017-10-22 16:09:19', '123456', '管理员', 'admin');

-- ----------------------------
-- Table structure for SYS_ADMIN_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ADMIN_ROLE`;
CREATE TABLE `SYS_ADMIN_ROLE` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `ADMIN_ID` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKM67R6YXQWDN42D4KX5HGPQF48` (`ROLE_ID`),
  KEY `FKBBO1422SB63K53YVNACTL6L9H` (`ADMIN_ID`),
  CONSTRAINT `FKBBO1422SB63K53YVNACTL6L9H` FOREIGN KEY (`ADMIN_ID`) REFERENCES `SYS_ADMIN` (`PRIMARY_KEY`),
  CONSTRAINT `FKM67R6YXQWDN42D4KX5HGPQF48` FOREIGN KEY (`ROLE_ID`) REFERENCES `SYS_ROLE` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ADMIN_ROLE
-- ----------------------------
INSERT INTO `SYS_ADMIN_ROLE` VALUES ('8a80ace36005cb7c016005cd24580000', '2017-11-29 11:23:38', '402881e45f430abb015f432105b50006', '402881e45f430abb015f4321c2270007');

-- ----------------------------
-- Table structure for SYS_CONVERSION
-- ----------------------------
DROP TABLE IF EXISTS `SYS_CONVERSION`;
CREATE TABLE `SYS_CONVERSION` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `CHINESE` varchar(255) NOT NULL,
  `PINYIN` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_CONVERSION
-- ----------------------------
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd0605317270160531b0475000b', '2017-12-14 11:39:28', 'IP地址', 'IPdizhi');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd0605317270160531b0562000c', '2017-12-14 11:39:28', '用户名', 'username');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd0605317270160531b0626000d', '2017-12-14 11:39:28', '数据源名称', 'dsname');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd0605317270160531b06ee000e', '2017-12-14 11:39:28', '端口', 'port');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd060531ea20160531f9b690000', '2017-12-14 11:44:29', '数据交换', 'dataexchange');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd060531ea20160531f9c9b0001', '2017-12-14 11:44:29', '数据源管理', 'dsmanage');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd0605324b1016053250fe90000', '2017-12-14 11:50:26', '密码', 'password');
INSERT INTO `SYS_CONVERSION` VALUES ('8a80acd06053cabb016053ce77d80009', '2017-12-14 14:55:28', '类型', 'leixing');

-- ----------------------------
-- Table structure for SYS_DICTIONARY
-- ----------------------------
DROP TABLE IF EXISTS `SYS_DICTIONARY`;
CREATE TABLE `SYS_DICTIONARY` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `CODE` varchar(50) NOT NULL,
  `TEXT` varchar(50) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_DICTIONARY
-- ----------------------------
INSERT INTO `SYS_DICTIONARY` VALUES ('8a80acd06053cabb016053cc71d30000', '2017-12-14 14:53:16', 'dstype', '数据源类型');

-- ----------------------------
-- Table structure for SYS_DICTIONARY_DATA
-- ----------------------------
DROP TABLE IF EXISTS `SYS_DICTIONARY_DATA`;
CREATE TABLE `SYS_DICTIONARY_DATA` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `CODE` varchar(50) NOT NULL,
  `DT_ID` varchar(255) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKESOFRO9KRQU673OC2C9RFVEK` (`DT_ID`),
  CONSTRAINT `FKESOFRO9KRQU673OC2C9RFVEK` FOREIGN KEY (`DT_ID`) REFERENCES `SYS_DICTIONARY` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_DICTIONARY_DATA
-- ----------------------------
INSERT INTO `SYS_DICTIONARY_DATA` VALUES ('8a80acd06053cabb016053ccb13a0001', '2017-12-14 14:53:32', 'mysql', '8a80acd06053cabb016053cc71d30000', 'MySql');
INSERT INTO `SYS_DICTIONARY_DATA` VALUES ('8a80acd06053cabb016053ccdc250002', '2017-12-14 14:53:43', 'oracle', '8a80acd06053cabb016053cc71d30000', 'Oracle');

-- ----------------------------
-- Table structure for SYS_FLOW_INFO
-- ----------------------------
DROP TABLE IF EXISTS `SYS_FLOW_INFO`;
CREATE TABLE `SYS_FLOW_INFO` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_FLOW_INFO
-- ----------------------------
INSERT INTO `SYS_FLOW_INFO` VALUES ('8a80acc660260a010160261854540000', '2017-12-05 17:53:37', 'test');

-- ----------------------------
-- Table structure for SYS_FLOW_NODE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_FLOW_NODE`;
CREATE TABLE `SYS_FLOW_NODE` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `BIND_FORM` varchar(255) DEFAULT NULL,
  `FLOW_ID` varchar(255) NOT NULL,
  `NODE_NAME` varchar(255) NOT NULL,
  `NODE_TYPE` varchar(255) NOT NULL,
  `STYLE` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for SYS_FLOW_NODE_ATTA
-- ----------------------------
DROP TABLE IF EXISTS `SYS_FLOW_NODE_ATTA`;
CREATE TABLE `SYS_FLOW_NODE_ATTA` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `NODE_ATTA_NAME` varchar(255) NOT NULL,
  `NODE_ID` varchar(255) NOT NULL,
  `TARGET_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKDSMG1WFVNDC4KRCW6V13LAYEL` (`NODE_ID`),
  CONSTRAINT `FKDSMG1WFVNDC4KRCW6V13LAYEL` FOREIGN KEY (`NODE_ID`) REFERENCES `SYS_FLOW_NODE` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_FLOW_NODE_ATTA
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_FORM_FIELD
-- ----------------------------
DROP TABLE IF EXISTS `SYS_FORM_FIELD`;
CREATE TABLE `SYS_FORM_FIELD` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime NOT NULL,
  `DECIMALS` int(11) DEFAULT NULL,
  `DTCODE` varchar(255) DEFAULT NULL,
  `ELEMENT_ID` varchar(255) NOT NULL,
  `FORM_ID` varchar(255) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `LENGTH` int(11) DEFAULT NULL,
  `MANDATORY` bit(1) NOT NULL,
  `MAX` int(11) DEFAULT NULL,
  `MIN` int(11) DEFAULT NULL,
  `PLACEHOLDER` varchar(255) DEFAULT NULL,
  `REGULARITY` varchar(255) DEFAULT NULL,
  `SUFFIX` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKOV23SGPBOE9SM4YUA8QX710LC` (`FORM_ID`),
  CONSTRAINT `FKOV23SGPBOE9SM4YUA8QX710LC` FOREIGN KEY (`FORM_ID`) REFERENCES `SYS_FORM_INFO` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for SYS_FORM_INFO
-- ----------------------------
DROP TABLE IF EXISTS `SYS_FORM_INFO`;
CREATE TABLE `SYS_FORM_INFO` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime NOT NULL,
  `CLASS_NAME` varchar(255) NOT NULL,
  `FUNCTION_NAME` varchar(255) NOT NULL,
  `MODULE_NAME` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for SYS_RESOURCES
-- ----------------------------
DROP TABLE IF EXISTS `SYS_RESOURCES`;
CREATE TABLE `SYS_RESOURCES` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `ICON` varchar(50) DEFAULT NULL,
  `PARENT_ID` varchar(255) DEFAULT NULL,
  `RESOURCE_URL` varchar(255) DEFAULT NULL,
  `SAVE_TYPE` varchar(20) NOT NULL,
  `SEQ` int(11) NOT NULL,
  `RESOURCE_NAME` varchar(50) NOT NULL,
  `USING_STATE` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKPSUI3GNO4D9AUV9AKBM4DS1DH` (`PARENT_ID`),
  CONSTRAINT `FKPSUI3GNO4D9AUV9AKBM4DS1DH` FOREIGN KEY (`PARENT_ID`) REFERENCES `SYS_RESOURCES` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_RESOURCES
-- ----------------------------
INSERT INTO `SYS_RESOURCES` VALUES ('8a80acd06053ba58016053bad5000000', '2017-12-14 14:34:01', null, null, null, 'menu1', '0', '数据交换', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80acd06053ba58016053bad5bf0001', '2017-12-14 14:34:02', '', '8a80acd06053ba58016053bad5000000', '/dataexchange/dsmanage/', 'menu2', '0', '数据源管理', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe27750b80000', '2017-11-22 14:43:11', '&#xe68e;', null, '', 'menu1', '1', '系统设置', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe27799a10001', '2017-11-22 14:43:30', '', '8a80ace35fe2766c015fe27750b80000', '/sys/resources/', 'menu2', '3', '资源管理', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe27804710002', '2017-11-22 14:43:57', '', '8a80ace35fe2766c015fe27750b80000', '/sys/admin/', 'menu2', '1', '用户管理', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe278563e0003', '2017-11-22 14:44:18', '', '8a80ace35fe2766c015fe27750b80000', '/sys/role/', 'menu2', '2', '角色管理', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe27a64ef000c', '2017-11-22 14:46:33', '', '8a80ace35fe2766c015fe27750b80000', '/sys/dictionary/', 'menu2', '4', '字典管理', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe27f6c760012', '2017-11-22 14:52:02', '', null, '', 'menu1', '2', '常用工具', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe27fca870013', '2017-11-22 14:52:27', '', '8a80ace35fe2766c015fe27f6c760012', '/sys/flow/info/', 'menu2', '1', '流程设计', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe2766c015fe2800c0f0014', '2017-11-22 14:52:43', '', '8a80ace35fe2766c015fe27f6c760012', '/sys/form/info/', 'menu2', '2', '表单设计', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace35fe6d463015fe6d5146f0000', '2017-11-23 11:04:05', '', '8a80ace35fe2766c015fe27f6c760012', '/sys/table/info/', 'menu2', '3', '库表管理', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace36001ee95016001f29ddf0000', '2017-11-28 17:26:06', '', null, '', 'menu1', '3', '系统监控', 'available');
INSERT INTO `SYS_RESOURCES` VALUES ('8a80ace36001ee95016001f304430001', '2017-11-28 17:26:32', '', '8a80ace36001ee95016001f29ddf0000', '/sys/druid/', 'menu2', '1', '数据访问', 'available');

-- ----------------------------
-- Table structure for SYS_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `CODE` varchar(50) NOT NULL,
  `ROLE_DESC` varchar(50) DEFAULT NULL,
  `ROLE_NAME` varchar(50) NOT NULL,
  `USING_STATE` varchar(255) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------
INSERT INTO `SYS_ROLE` VALUES ('402881e45f430abb015f4321c2270007', '2017-10-22 16:10:07', 'admin', '拥有所有权限并且不能修改', '超级管理员', 'available');

-- ----------------------------
-- Table structure for SYS_ROLE_RESOURCES
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_RESOURCES`;
CREATE TABLE `SYS_ROLE_RESOURCES` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `RESOURCES_ID` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKTJ0IMCCRQHX8O8CO66LAM2WAV` (`ROLE_ID`),
  KEY `FKDXI7W84YWXISCLR0MMBGYHHOL` (`RESOURCES_ID`),
  CONSTRAINT `FKDXI7W84YWXISCLR0MMBGYHHOL` FOREIGN KEY (`RESOURCES_ID`) REFERENCES `SYS_RESOURCES` (`PRIMARY_KEY`),
  CONSTRAINT `FKTJ0IMCCRQHX8O8CO66LAM2WAV` FOREIGN KEY (`ROLE_ID`) REFERENCES `SYS_ROLE` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_ROLE_RESOURCES
-- ----------------------------
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c043680000', '2017-12-14 14:39:57', '8a80acd06053ba58016053bad5000000', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436d0001', '2017-12-14 14:39:57', '8a80acd06053ba58016053bad5bf0001', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436d0002', '2017-12-14 14:39:57', '8a80ace35fe2766c015fe27750b80000', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436d0003', '2017-12-14 14:39:57', '8a80ace35fe2766c015fe27804710002', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436d0004', '2017-12-14 14:39:57', '8a80ace35fe2766c015fe278563e0003', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436d0005', '2017-12-14 14:39:57', '8a80ace35fe2766c015fe27799a10001', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436e0006', '2017-12-14 14:39:57', '8a80ace35fe2766c015fe27a64ef000c', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436e0007', '2017-12-14 14:39:58', '8a80ace35fe2766c015fe27f6c760012', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436e0008', '2017-12-14 14:39:58', '8a80ace35fe2766c015fe27fca870013', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436e0009', '2017-12-14 14:39:58', '8a80ace35fe2766c015fe2800c0f0014', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436f000a', '2017-12-14 14:39:58', '8a80ace35fe6d463015fe6d5146f0000', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436f000b', '2017-12-14 14:39:58', '8a80ace36001ee95016001f29ddf0000', '402881e45f430abb015f4321c2270007');
INSERT INTO `SYS_ROLE_RESOURCES` VALUES ('8a80acd06053bf55016053c0436f000c', '2017-12-14 14:39:58', '8a80ace36001ee95016001f304430001', '402881e45f430abb015f4321c2270007');

-- ----------------------------
-- Table structure for SYS_TABLE_COLUMN
-- ----------------------------
DROP TABLE IF EXISTS `SYS_TABLE_COLUMN`;
CREATE TABLE `SYS_TABLE_COLUMN` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime NOT NULL,
  `COLUMN_DEF` varchar(255) DEFAULT NULL,
  `LENGTH` int(11) DEFAULT NULL,
  `NAME` varchar(100) NOT NULL,
  `NULLABLE` bit(1) NOT NULL,
  `REMARKS` varchar(50) NOT NULL,
  `SCALE` int(11) DEFAULT NULL,
  `TABLE_ID` varchar(255) NOT NULL,
  `TYPE` varchar(50) NOT NULL,
  PRIMARY KEY (`PRIMARY_KEY`),
  KEY `FKLKS3YO1FPUHPUQD9W7VLQXE0Y` (`TABLE_ID`),
  CONSTRAINT `FKLKS3YO1FPUHPUQD9W7VLQXE0Y` FOREIGN KEY (`TABLE_ID`) REFERENCES `SYS_TABLE_INFO` (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_TABLE_COLUMN
-- ----------------------------

-- ----------------------------
-- Table structure for SYS_TABLE_INFO
-- ----------------------------
DROP TABLE IF EXISTS `SYS_TABLE_INFO`;
CREATE TABLE `SYS_TABLE_INFO` (
  `PRIMARY_KEY` varchar(32) NOT NULL,
  `ADD_TIME` datetime NOT NULL,
  `CLASS_NAME` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `ORM_NAME` varchar(100) NOT NULL,
  `ORM_TYPE` varchar(100) NOT NULL DEFAULT 'TABLE',
  PRIMARY KEY (`PRIMARY_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of SYS_TABLE_INFO
-- ----------------------------
