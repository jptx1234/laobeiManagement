/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.16 : Database - laobeidb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`laobeidb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `laobeidb`;

/*Table structure for table `consume_daily` */

DROP TABLE IF EXISTS `consume_daily`;

CREATE TABLE `consume_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `count` float DEFAULT NULL,
  `stock_type` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `consume_daily` */

/*Table structure for table `purchase_daily` */

DROP TABLE IF EXISTS `purchase_daily`;

CREATE TABLE `purchase_daily` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `unit` varchar(10) DEFAULT NULL,
  `unit_price` float DEFAULT NULL,
  `stock_type` bigint(20) DEFAULT NULL,
  `img_name` varchar(100) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `purchase_daily` */

/*Table structure for table `recipes` */

DROP TABLE IF EXISTS `recipes`;

CREATE TABLE `recipes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `cooking_method` varchar(200) DEFAULT NULL,
  `cooking_time` int(11) DEFAULT '0',
  `price` float DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `recipes` */

/*Table structure for table `recipes_stock` */

DROP TABLE IF EXISTS `recipes_stock`;

CREATE TABLE `recipes_stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recipe_id` bigint(20) DEFAULT NULL,
  `stock_id` bigint(20) DEFAULT NULL,
  `stock_count` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `recipes_stock` */

/*Table structure for table `stock` */

DROP TABLE IF EXISTS `stock`;

CREATE TABLE `stock` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `unit` varchar(10) DEFAULT NULL COMMENT '单位',
  `unit_price` float DEFAULT NULL COMMENT '单价',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stock` */

/*Table structure for table `stock_type` */

DROP TABLE IF EXISTS `stock_type`;

CREATE TABLE `stock_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `stock_type` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `uid` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `is_admin` tinyint(4) DEFAULT '0' COMMENT '0:普通用户，1:管理员',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '0:已禁用，1:已启用',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`uid`,`username`,`password`,`is_admin`,`status`) values (1,'admin','123',1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
