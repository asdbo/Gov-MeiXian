-- MySQL dump 10.13  Distrib 5.5.42, for osx10.6 (i386)
--
-- Host: 192.168.0.31    Database: java
-- ------------------------------------------------------
-- Server version	5.5.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `regtime` bigint(10) NOT NULL DEFAULT '0',
  `logintime` bigint(10) NOT NULL DEFAULT '0',
  `username` char(240) NOT NULL,
  `password` char(240) NOT NULL,
  `name` char(240) NOT NULL,
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,0,1475893628,'admin','21232f297a57a5a743894a0e4a801fc3','admin',0),(25,1475893654,1475893654,'1411110000','b0184470a7dc7a8343bd10020ed5d2e8','1411110000',1),(28,1475893680,1475893680,'1234567','fcea920f7412b5da7be0cf42b8c93759','1234567',1),(35,1476089342,1476089342,'xiaoxao1','7213c4464ab8b8afd6718fba742a07f1','poiuytre',1),(36,1476090201,1476090201,'yonghu','b05004cbc0badc65d3db340fae8dc74f','yonghu',1),(37,1476090245,1476090245,'jfiaejfja','cd17d02e8aa83cc4a946d67b690171be','vaefe',1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_log`
--

DROP TABLE IF EXISTS `admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminid` bigint(11) NOT NULL COMMENT '用户ID',
  `logintime` bigint(11) NOT NULL COMMENT '登录时间',
  `username` char(240) NOT NULL COMMENT '用户名',
  `operationtype` char(240) NOT NULL COMMENT '操作类型',
  `operationresult` char(240) NOT NULL COMMENT '操作结果',
  `ip` char(240) NOT NULL COMMENT '登录ip',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_log`
--

LOCK TABLES `admin_log` WRITE;
/*!40000 ALTER TABLE `admin_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carousel`
--

DROP TABLE IF EXISTS `carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `link` char(240) NOT NULL COMMENT '图片链接',
  `sortid` int(11) NOT NULL COMMENT '排序id',
  `title` char(240) NOT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carousel`
--

LOCK TABLES `carousel` WRITE;
/*!40000 ALTER TABLE `carousel` DISABLE KEYS */;
/*!40000 ALTER TABLE `carousel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `channel`
--

DROP TABLE IF EXISTS `channel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` char(180) NOT NULL,
  `pid` int(11) NOT NULL,
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `channel`
--

LOCK TABLES `channel` WRITE;
/*!40000 ALTER TABLE `channel` DISABLE KEYS */;
INSERT INTO `channel` VALUES (1,'首页',0,0),(2,'走进梅江',0,0),(3,'梅江动态',0,0),(4,'民生事实',2,0),(6,'事件发生',0,0),(7,'政务公开',0,0),(8,'民生服务',0,0),(9,'网上办事',0,0),(10,'互动交流',0,0),(11,'回应关切',0,0),(12,'梅江要闻',3,0),(13,'政务动态',3,0),(14,'乡镇部门动态',3,0),(15,'政务文件',7,0),(16,'梅江情况',7,0),(17,'梅江宣传',7,0),(18,'组织动态',7,0),(19,'通知公告',3,0),(20,'区政府文件',15,0),(21,'区委区府办文件',15,0),(22,'梅江概况',2,0),(23,'经济建设',2,0),(24,'城市建设',2,0),(25,'文化教育',2,0),(26,'客家风情',2,0),(27,'旅游',2,0),(28,'统计数据',7,0),(29,'统计月报',28,0),(30,'统计年鉴',28,0),(31,'统计分析',28,0),(32,'人事工作',7,0),(33,'人事任免',32,0),(34,'任前公示',32,0),(35,'干部选拔',32,0),(36,'执行公开',7,0),(37,'执行过程公开',36,0),(38,'督查审计公开',36,0),(39,'决策公开',7,0),(40,'决策预公开',39,0),(41,'决策后公开',39,0),(42,'服务公开',7,0),(43,'中介服务事项清单',42,0),(44,'公共企事业单位公开',42,0),(45,'管理公开',7,0),(46,'权责清单公开',45,0),(47,'行政执法公开',45,0),(48,'结果公开',7,0),(49,'落实情况公开',48,0),(50,'跟踪反馈公开',48,0),(51,'开发数据',7,0),(52,'回应关切',7,0),(53,'政策解读',52,0),(54,'新闻发布会',52,0);
/*!40000 ALTER TABLE `channel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `letter`
--

DROP TABLE IF EXISTS `letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `people_name` char(100) DEFAULT NULL,
  `phone` char(50) DEFAULT NULL,
  `email` char(100) DEFAULT NULL,
  `address` char(100) DEFAULT NULL,
  `postalcode` char(100) DEFAULT NULL,
  `lettle_type` char(100) DEFAULT NULL,
  `letter_dept` char(100) DEFAULT NULL,
  `theme` char(100) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `state` char(10) DEFAULT NULL,
  `receive_time` char(50) DEFAULT NULL,
  `reply_time` char(50) DEFAULT NULL,
  `reply_content` varchar(500) DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `letter`
--

LOCK TABLES `letter` WRITE;
/*!40000 ALTER TABLE `letter` DISABLE KEYS */;
INSERT INTO `letter` VALUES (3,'小r','18814383288','898777634@qq.com','广东东莞莞城','123456','投诉','梅江区物价局','123456','123456','已回复','2016-09-28','2016-10-10','eee',NULL),(4,'小t','18814383288','898777634@qq.com','广东东莞莞城','123456','表扬','梅江区统计局','123456','123456','已回复','2016-09-23','2016-05-28','ccc',1),(5,'小y','18814383288','898777634@qq.com','广东东莞莞城','123456','建议','梅江区物价局','123456','123456','已回复','2016-09-28','2016-09-28','eee',1),(6,'小i','18814383288','898777634@qq.com','广东东莞莞城','123456','投诉','梅江区西阳镇','123456','123456','已回复','2016-09-28','2016-09-28','ddd',2),(7,'小o','18814383299','898777634@qq.com','广东东莞莞城','243222','建议','梅江区妇联','243222','243222','已接收','2016-09-28',NULL,NULL,NULL),(8,'小g','18814383289','898777634@qq.com','广东东莞莞城','123456','咨询','梅江区纪检监察','123456','123456','已接收','2016-09-28',NULL,NULL,NULL);
/*!40000 ALTER TABLE `letter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `letter_dept`
--

DROP TABLE IF EXISTS `letter_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter_dept` (
  `id` int(11) NOT NULL,
  `depts` char(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `letter_dept`
--

LOCK TABLES `letter_dept` WRITE;
/*!40000 ALTER TABLE `letter_dept` DISABLE KEYS */;
INSERT INTO `letter_dept` VALUES (1,'梅江区纪检监察'),(2,'梅江区农业局'),(3,'梅江区妇联'),(4,'梅江区统计局'),(5,'梅江区城北镇'),(6,'梅江区西阳镇'),(7,'梅江区物价局'),(8,'梅江区民政局');
/*!40000 ALTER TABLE `letter_dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `letter_type`
--

DROP TABLE IF EXISTS `letter_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter_type` (
  `id` int(11) NOT NULL,
  `types` char(80) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `letter_type`
--

LOCK TABLES `letter_type` WRITE;
/*!40000 ALTER TABLE `letter_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `letter_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL COMMENT '栏目id',
  `title` char(240) NOT NULL COMMENT '新闻标题',
  `content` mediumtext NOT NULL COMMENT '内容',
  `TIME` bigint(20) NOT NULL,
  `attach` char(240) NOT NULL COMMENT '附件下载链接',
  `aid` int(11) NOT NULL COMMENT '发布人id',
  `looks` int(11) NOT NULL COMMENT '浏览次数',
  `video` char(240) NOT NULL COMMENT '视频播放链接',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (2,12,'test1','<p>test1</p>',1476071272,'',1,14,''),(3,13,'test2','<p>test2</p>',1476071295,'',1,1,''),(4,14,'test3','<p>test3</p>',1476071310,'',1,1,''),(5,20,'test4','<p>test4</p>',1476071338,'',1,1,''),(6,21,'test5','<p>test5</p>',1476071352,'',1,9,''),(7,16,'test6','<p>asddsf</p>',1476071384,'',1,0,''),(8,17,'test7','<p>saffws</p>',1476071408,'',1,0,''),(9,18,'test0','<p>sadfwq</p>',1476071428,'',1,0,''),(10,19,'ggg','<p>ggg</p>',1476071440,'',1,0,'');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-11 15:04:56
