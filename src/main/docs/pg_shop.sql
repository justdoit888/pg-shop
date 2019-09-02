/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.6.21-log : Database - pg_shop
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`pg_shop` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `pg_shop`;

/*Table structure for table `pg_cs_order` */

DROP TABLE IF EXISTS `pg_cs_order`;

CREATE TABLE `pg_cs_order` (
  `order_id` bigint(20) NOT NULL COMMENT '订单主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '客服用户主键',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客服用户名',
  `account_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '客服账户名',
  `account_no` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '客服账户号',
  `settle_amount` decimal(18,2) DEFAULT NULL COMMENT '清分金额',
  `rate` decimal(18,2) DEFAULT NULL COMMENT '费率',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `pg_cs_order` */

/*Table structure for table `pg_order` */

DROP TABLE IF EXISTS `pg_order`;

CREATE TABLE `pg_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tb_order_id` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '淘宝订单号',
  `buyer_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '淘宝买家用户名',
  `amount` decimal(18,2) DEFAULT NULL COMMENT '金额',
  `order_state` tinyint(4) DEFAULT NULL COMMENT '订单状体：0-待核对、1-待结算、2-已结算',
  `approval_user_id` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '审批人主键',
  `approval_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '审批人用户名',
  `approve_account_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '审批人账户名',
  `settle_id` bigint(20) DEFAULT NULL COMMENT '结算id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `remark` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `pg_order` */

/*Table structure for table `pg_programmer_order` */

DROP TABLE IF EXISTS `pg_programmer_order`;

CREATE TABLE `pg_programmer_order` (
  `order_id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '服务者用户主键',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '服务者用户名',
  `account_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '服务者账户名',
  `account_no` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '服务者账户号',
  `settle_amount` decimal(18,2) DEFAULT NULL COMMENT '清分金额',
  `rate` decimal(18,2) DEFAULT NULL COMMENT '费率',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `pg_programmer_order` */

/*Table structure for table `pg_settle` */

DROP TABLE IF EXISTS `pg_settle`;

CREATE TABLE `pg_settle` (
  `settle_id` bigint(20) DEFAULT NULL COMMENT '清分主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '收款人用户id',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '收款人用户名',
  `account_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '收款人账户名',
  `account_no` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '收款人账户号',
  `settle_amount` decimal(18,2) DEFAULT NULL COMMENT '结算金额',
  `settle_user_id` bigint(20) DEFAULT NULL COMMENT '结算人ID',
  `settle_user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '结算人用户名',
  `settle_account_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '结算人账户名',
  `settle_account_no` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '结算人账户号',
  `settle_date` datetime DEFAULT NULL COMMENT '结算日期',
  `settle_state` tinyint(4) DEFAULT NULL COMMENT '结算状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `pg_settle` */

/*Table structure for table `pg_user` */

DROP TABLE IF EXISTS `pg_user`;

CREATE TABLE `pg_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键，起始值1000',
  `user_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名（手机号）',
  `account_name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '账户姓名',
  `account_no` varchar(40) COLLATE utf8_bin DEFAULT NULL COMMENT '支付宝结算账户号',
  `address` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '地址',
  `password` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `role` INT (4) DEFAULT NULL COMMENT '角色：1-超级管理员、2-客服、程序员',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新日期',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `pg_user` */

