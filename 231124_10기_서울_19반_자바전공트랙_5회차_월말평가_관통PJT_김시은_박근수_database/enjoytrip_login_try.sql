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
-- Table structure for table `login_try`
--

DROP TABLE IF EXISTS `login_try`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_try` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(16) DEFAULT NULL,
  `retry` int DEFAULT '0',
  `last_try_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `last_try_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `client_ip` varchar(15) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_try`
--

LOCK TABLES `login_try` WRITE;
/*!40000 ALTER TABLE `login_try` DISABLE KEYS */;
INSERT INTO `login_try` VALUES (1,'asdf',4,'2023-11-14 05:28:08','2023-11-14 05:28:08','0:0:0:0:0:0:0:1'),(2,'asdf',0,NULL,NULL,'127.0.0.1'),(3,'asd',0,'2023-11-12 07:50:31','2023-11-12 07:50:31','127.0.0.1'),(4,'',0,'2023-11-12 07:57:09','2023-11-12 07:57:09','127.0.0.1'),(5,'ssafy',3,'2023-11-22 02:57:29','2023-11-22 02:57:29','0:0:0:0:0:0:0:1'),(6,'ssasdf',0,'2023-11-14 05:13:07','2023-11-14 05:13:07','0:0:0:0:0:0:0:1'),(7,'asdfsdf',0,'2023-11-14 05:14:29','2023-11-14 05:14:29','0:0:0:0:0:0:0:1'),(8,'asdfff',0,'2023-11-14 05:14:59','2023-11-14 05:14:59','0:0:0:0:0:0:0:1'),(9,'asdfasdf',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(10,'asdfsadf',0,'2023-11-14 05:16:18','2023-11-14 05:16:18','0:0:0:0:0:0:0:1'),(11,'sieun043',0,'2023-11-16 06:28:52','2023-11-16 06:28:52','0:0:0:0:0:0:0:1'),(12,'sieun',1,'2023-11-20 00:31:46','2023-11-20 00:31:46','0:0:0:0:0:0:0:1'),(13,'asdf1234',0,'2023-11-19 01:28:54','2023-11-19 01:28:54','0:0:0:0:0:0:0:1'),(14,'asdf1',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(15,'asdf12',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(16,'ssafy',0,NULL,NULL,'192.168.200.110'),(17,'sieun000',1,'2023-11-20 04:09:42','2023-11-20 04:09:42','0:0:0:0:0:0:0:1'),(18,'ssafy000',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(19,'dr.hong',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(20,'111',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(21,'222',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(22,'hong',34,'2023-11-22 06:14:51','2023-11-22 06:14:51','0:0:0:0:0:0:0:1'),(23,'honghong',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(24,'utdutd',2,'2023-11-20 01:18:29','2023-11-20 01:18:29','0:0:0:0:0:0:0:1'),(25,'kimssafy',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(26,'rlatldms',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(27,'hong123',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(28,'rtrtrtrt',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(29,'0101',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(30,'010101',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(31,'qwert',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(32,'000',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(33,'ssafy kim',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(34,'ssafykim',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(35,'etetet',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(36,'ssafy0101',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(37,'ssafykim2',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(38,'hong2',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(39,'ssafykim3',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(40,'ssafykim4',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(41,'ssafykim5',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(42,'ssafykim06',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(43,'hong3',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(44,'test',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(45,'rla',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(46,'rla1234',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(47,'hong5',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(48,'hong10',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(49,'ssafy1234',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(50,'ssafy100',0,NULL,NULL,'0:0:0:0:0:0:0:1'),(51,'ssafy100###',0,'2023-11-23 06:05:45','2023-11-23 06:05:45','0:0:0:0:0:0:0:1'),(52,'ssafy200',0,NULL,NULL,'0:0:0:0:0:0:0:1');
/*!40000 ALTER TABLE `login_try` ENABLE KEYS */;
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

-- Dump completed on 2023-11-24  9:16:55
