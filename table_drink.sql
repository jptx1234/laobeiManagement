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

/*Table structure for table `drink` */

DROP TABLE IF EXISTS `drink`;

CREATE TABLE `drink` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `drink_name` varchar(100) DEFAULT NULL,
  `drink_comment` varchar(1024) DEFAULT NULL,
  `drink_price` float DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `drink` */

insert  into `drink`(`id`,`drink_name`,`drink_comment`,`drink_price`) values (1,'茅台','国酒茅台',1888);
insert  into `drink`(`id`,`drink_name`,`drink_comment`,`drink_price`) values (2,'拉菲','82年的拉菲',18888);
insert  into `drink`(`id`,`drink_name`,`drink_comment`,`drink_price`) values (3,'可乐','可口可乐',4);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
