-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: ssafy1019mysql.clhnapusjk4s.ap-northeast-2.rds.amazonaws.com    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `article_no` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `hit` int NOT NULL DEFAULT '0',
  `register_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,'sieun','test','this is test text',1,'2023-11-10 06:47:01'),(2,'sieun','김시은','시은이는 오늘도 개발 개발 개발...',2,'2023-11-10 11:35:19'),(4,'sieun','hello','hello',1,'2023-11-10 11:39:51'),(7,'sieun','bye','router please',0,'2023-11-10 12:02:49'),(8,'sieun','type','typing',1,'2023-11-10 12:13:10'),(46,'asdfasdf','asdfasdf','asdfasdfasdf',0,'2023-11-14 05:42:41'),(47,'id','asdf','asdf',1,'2023-11-14 05:57:03'),(48,'ssafy','sdfsdf','asdf',2,'2023-11-15 01:21:29'),(49,'ssafy','sdf111','111111',13,'2023-11-16 05:28:46'),(50,'ssafy','test','test',42,'2023-11-16 05:45:06'),(51,'ssafy','sdfsdf','aaaa',0,'2023-11-16 06:08:43'),(52,'ssafy','aaaa','aaa',0,'2023-11-16 06:08:48'),(53,'ssafy','aaa','aaaa',0,'2023-11-16 06:08:54'),(54,'ssafy','aaaa','aaaa',0,'2023-11-16 06:08:58'),(55,'ssafy','aaaa','aaaa',6,'2023-11-16 06:09:02'),(57,'ssafy','map test','map test',0,'2023-11-17 06:01:52'),(58,'ssafy','해적왕이 될꺼야','해적왕이 되기 바다로 떠났습니다.',5,'2023-11-17 06:31:49'),(59,'ssafy','이번엔 진짜 해적왕이 될꺼야','해적왕이 되기 위하여 2차 여행을 떠났다.',38,'2023-11-17 06:36:10'),(61,'ssafy','test','test',11,'2023-11-19 18:55:28'),(62,'ssafy','hello','hello',11,'2023-11-19 19:06:57'),(63,'ssafy','test2','test2',41,'2023-11-19 19:16:01'),(65,'ssafykim3','sdf','ssssss',8,'2023-11-20 05:16:17'),(67,'anonymous','test hong 10','hong 10\nhong 10',6,'2023-11-23 01:02:44'),(68,'anonymous','hello world','I\'am ssafy kim',4,'2023-11-23 05:18:46'),(70,'anonymous','저는 김싸피 라고 합ㄴ디ㅏ.','잘부탁 드립니다.',2,'2023-11-23 06:03:43');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-24  9:16:56
