/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.27 : Database - myblog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myblog` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myblog`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `bl_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) DEFAULT NULL,
  `content` text,
  `outline` varchar(200) DEFAULT NULL,
  `flag` varchar(5) DEFAULT NULL,
  `recommend` tinyint(1) DEFAULT '1',
  `commentabled` tinyint(1) DEFAULT '1',
  `published` tinyint(1) DEFAULT '1',
  `creatdate` datetime DEFAULT NULL,
  `finaldate` datetime DEFAULT NULL,
  `views` int(11) DEFAULT NULL,
  `ty_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`bl_id`),
  KEY `ty_id` (`ty_id`),
  CONSTRAINT `blog_ibfk_1` FOREIGN KEY (`ty_id`) REFERENCES `type` (`ty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

/*Data for the table `blog` */

insert  into `blog`(`bl_id`,`title`,`content`,`outline`,`flag`,`recommend`,`commentabled`,`published`,`creatdate`,`finaldate`,`views`,`ty_id`) values (1,'7891100111299000','<p>这里有一些内容。。。。。。这是文章一，拉拉拉1211</p>\n<p>3</p>','这有内容。。。简介123','123',0,1,1,'2019-06-23 13:08:23','2019-11-08 11:30:01',100,3),(2,'标题2','这里有一些内容。。。。。。这是文章，拉拉拉','这有内容。。。简介',NULL,1,1,0,'2019-06-23 13:08:23','2019-06-23 13:08:23',200,2),(3,'标题3','这里有一些内容。。。。。。这是文章，拉拉拉','这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',300,3),(4,'标题4','这里有一些内容。。。。。。这是文章，拉拉拉','这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',200,4),(5,'标题5','这里有一些内容。。。。。。这是文章，拉拉拉','这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',100,5),(6,'6666666标题6666666','内容6666666666','outline666','flag',0,0,1,'2019-06-23 13:08:23','2019-11-11 11:11:11',100,1),(7,'title','content','outline','flag',0,0,1,'2019-06-23 13:08:23','2019-01-11 00:00:00',100,5),(8,'title','content','outline','flag',0,0,1,'2019-06-23 13:08:23','2019-01-11 00:00:00',200,5),(10,'标题10',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',300,1),(11,'标题11',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',500,1),(12,'标题12',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',600,1),(13,'title','content','outline','flag',0,0,0,'2019-06-23 13:08:23','2019-01-11 00:00:00',700,5),(14,'标题14',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',800,2),(15,'title','content','outline','flag',0,0,0,'2019-06-23 13:08:23','2019-01-11 00:00:00',900,5),(16,'标题16',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',1000,2),(17,'标题17',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',1100,2),(18,'标题18',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',2000,2),(19,'标题19',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',3000,2),(20,'title','content','outline','flag',0,0,0,'2019-06-23 13:08:23','2019-01-11 00:00:00',4040,5),(21,'标题21',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',5000,3),(23,'标题23',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',6000,3),(24,'标题24',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',7000,3),(25,'标题25',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',10000,3),(26,'标题26',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',8000,3),(27,'标题27',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',6000,3),(28,'标题28',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',5000,3),(29,'标题29',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',100,3),(30,'标题30',NULL,'这有内容。。。简介',NULL,1,1,1,'2019-06-23 13:08:23','2019-06-23 13:08:23',800,3),(32,'title','content','outline','flag',0,0,0,'2019-01-11 00:00:00','2019-01-11 00:00:00',1,5),(33,'title','content','outline','flag',0,0,0,'2019-01-11 00:00:00','2019-01-11 00:00:00',1,5),(34,'title','content','outline','flag',0,0,0,'2019-01-11 00:00:00','2019-01-11 00:00:00',1,5),(35,'title','content','outline','flag',0,0,0,'2019-01-11 00:00:00','2019-01-11 00:00:00',1,5),(36,'99999999999','<p>9999999999999</p>','999999999','123',0,1,1,'2019-10-30 18:57:40','2019-10-30 18:57:40',NULL,4),(37,'99999999999','<p>9999999999999</p>','999999999','123',0,0,1,'2019-10-30 18:58:51','2019-10-30 18:58:51',NULL,4),(38,'测试','<p>测试</p>','999999999','123',0,0,1,'2019-10-30 18:59:35','2019-10-30 18:59:35',NULL,4),(39,'测试啊','<p>123</p>','','123',0,0,1,'2019-10-30 19:14:14','2019-10-30 19:14:14',NULL,1),(40,'测试嗯嗯嗯','<p>123</p>','','123',0,0,1,'2019-10-30 19:14:42','2019-10-30 19:14:42',NULL,1),(41,'55555555555','<p>5555555555555</p>','55555555','123',0,1,1,'2019-10-30 19:32:09','2019-10-30 19:32:09',NULL,3),(42,'999999','<p>9999999</p>','999999999','123',0,0,0,'2019-10-30 19:36:27','2019-10-30 19:36:27',NULL,2);

/*Table structure for table `blog_tag` */

DROP TABLE IF EXISTS `blog_tag`;

CREATE TABLE `blog_tag` (
  `bl_id` int(11) NOT NULL,
  `ta_id` int(11) NOT NULL,
  KEY `bl_id` (`bl_id`),
  KEY `ta_id` (`ta_id`),
  CONSTRAINT `blog_tag_ibfk_1` FOREIGN KEY (`bl_id`) REFERENCES `blog` (`bl_id`),
  CONSTRAINT `blog_tag_ibfk_2` FOREIGN KEY (`ta_id`) REFERENCES `tag` (`ta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `blog_tag` */

insert  into `blog_tag`(`bl_id`,`ta_id`) values (2,2),(2,3),(3,1),(8,1),(8,2),(20,1),(20,2),(20,3),(36,4),(37,4),(38,4),(41,3),(41,5),(42,5),(6,1),(7,1),(8,1),(10,1),(11,1),(12,1),(13,1),(14,1),(15,1),(16,1),(17,1),(18,1),(19,1),(20,1),(1,3);

/*Table structure for table `comment` */

DROP TABLE IF EXISTS `comment`;

CREATE TABLE `comment` (
  `co_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `content` varchar(120) DEFAULT NULL,
  `avatar` varchar(64) DEFAULT NULL,
  `creatdate` datetime DEFAULT NULL,
  `bl_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `ip_id` int(11) DEFAULT NULL,
  `adminComment` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`co_id`),
  KEY `bl_id` (`bl_id`),
  KEY `comment_ibfk_2` (`parent_id`),
  KEY `comment_ibfk_3` (`ip_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`bl_id`) REFERENCES `blog` (`bl_id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`co_id`),
  CONSTRAINT `comment_ibfk_3` FOREIGN KEY (`ip_id`) REFERENCES `t_ip` (`ip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `comment` */

insert  into `comment`(`co_id`,`nickname`,`email`,`content`,`avatar`,`creatdate`,`bl_id`,`parent_id`,`ip_id`,`adminComment`) values (1,'游客one','1333333@qq.com','文章不错点赞！',NULL,'2019-06-23 13:09:35',1,NULL,NULL,1),(2,'游客two','456798@qqcom','随便说点什么吧！！！',NULL,'2019-06-23 13:10:09',1,1,NULL,0),(3,'雷浩','123456@qq.com','内容内容内容内容内容内容','avatar','2019-06-23 00:00:00',1,NULL,NULL,0),(4,'测试','123@qq.com','ceshi',NULL,'2019-11-16 05:51:01',1,1,NULL,0),(5,'测试二','111@qq.com','哈哈',NULL,'2019-11-16 05:51:01',1,1,NULL,0),(6,'王五','555@qq.com','回复雷浩',NULL,'2019-11-09 12:25:05',1,3,NULL,0),(7,'阿斯','2910819096@qq.com','66666',NULL,'2019-11-09 12:28:51',1,3,NULL,0),(8,'77','775736156@qq.com','777',NULL,'2019-11-09 12:30:43',1,3,NULL,0),(9,'888','365651975@qq.com','回复77',NULL,'2019-11-09 12:31:57',1,8,NULL,0),(12,'阿飞','775736156@qq.com','啊哈哈',NULL,'2019-11-10 01:12:44',1,NULL,NULL,0),(13,'爱迪生','365651975@qq.com','6',NULL,'2019-11-10 01:13:42',1,12,NULL,0),(14,'阿斯达','365651975@qq.com','你好爱迪生',NULL,'2019-11-10 01:13:56',1,13,1,0),(15,'爱迪生','365651975@qq.com','你也好',NULL,'2019-11-10 01:14:17',1,14,1,0),(17,'55','555555','牛皮',NULL,'2019-11-10 04:53:19',1,13,1,0),(18,'8888888888888','1143038749@qq.com','88888888888',NULL,'2019-11-10 05:03:55',1,6,1,0),(19,'55','555555555','5555',NULL,'2019-11-10 05:13:46',1,NULL,1,0),(20,'666','66666666','666666666',NULL,'2019-11-10 05:14:38',1,NULL,1,0);

/*Table structure for table `t_ip` */

DROP TABLE IF EXISTS `t_ip`;

CREATE TABLE `t_ip` (
  `ip_id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(64) NOT NULL,
  `avatar` varchar(128) NOT NULL,
  PRIMARY KEY (`ip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_ip` */

insert  into `t_ip`(`ip_id`,`address`,`avatar`) values (1,'0:0:0:0:0:0:0:1','http://b-ssl.duitang.com/uploads/item/201802/21/20180221223815_xkkyq.jpg');

/*Table structure for table `tag` */

DROP TABLE IF EXISTS `tag`;

CREATE TABLE `tag` (
  `ta_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ta_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `tag` */

insert  into `tag`(`ta_id`,`name`) values (1,'标签1'),(2,'标签2'),(3,'标签3'),(4,'标签4'),(5,'标签5'),(6,'标签6');

/*Table structure for table `type` */

DROP TABLE IF EXISTS `type`;

CREATE TABLE `type` (
  `ty_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `type` */

insert  into `type`(`ty_id`,`name`) values (1,'类型one'),(2,'类型two'),(3,'类型three'),(4,'类型four'),(5,'类型five');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `us_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(20) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `power` int(3) DEFAULT NULL,
  `about` varchar(150) DEFAULT NULL,
  `location` varchar(20) DEFAULT NULL,
  `vx` varchar(20) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `finaldate` datetime DEFAULT NULL,
  `avatar` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`us_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`us_id`,`nickname`,`username`,`password`,`email`,`power`,`about`,`location`,`vx`,`qq`,`finaldate`,`avatar`) values (1,'鹏鹏强','admin','123456','1345@qq.com',1,'我是管理员哈哈哈！！！','北京朝阳区','365651975','1143038749','2019-06-23 13:13:15','http://cdn.duitang.com/uploads/item/201510/06/20151006213544_dCZat.thumb.700_0.jpeg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
