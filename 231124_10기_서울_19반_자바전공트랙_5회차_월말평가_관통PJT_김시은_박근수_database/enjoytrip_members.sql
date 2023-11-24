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
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `userId` varchar(16) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `userPassword` varchar(500) NOT NULL,
  `emailId` varchar(20) DEFAULT NULL,
  `emailDomain` varchar(45) DEFAULT NULL,
  `joinDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('111','111','84f7e5c123f796feaa9657e9ab985d0db1f5f9e8cd35347b145ba5ef74ac72e7','amolang','ssafy.com','2023-11-20 01:08:02'),('222','et','272467d26cbe4892b9848df6a2bd6ec9a645f44b3b10aabd8a89f6069f390ae9','et','ssafy.com','2023-11-20 01:10:29'),('anonymous','익명','',NULL,NULL,'2023-11-23 01:25:44'),('asdf','asdfasdf','ab48e70ee120450cd5873471b5959fb140dbf1e9c4622e625e595f4c359756b2','asdfsadf','naver.com','2023-11-10 07:16:26'),('asdf1','moonjar','32a7c628a1f0f157626afad9460fd42d1e95126d22531a7a7734b5e15ec3b8c0','asdf','ssafy.com','2023-11-19 01:31:03'),('asdf12','asdff','2b596f4c700b0140af92ffb6d5e84ee1662a4843aa478e5df5fdd58c4b4306cc','asdf','google.com','2023-11-19 02:13:28'),('asdf1234','asdfasdf','9922b5a1581ee6c90b2458169d6b8dbc9108dd75dbb842fdcffc7e7c0e71955e','asdfasdf','google.com','2023-11-18 14:56:55'),('asdf12344','asdfasdf','fb36bad43d0f3201aa2e52253b9defbf104fdfecc8c170ecd07e276efd5f0f6f','asdfasdf','naver.com','2023-11-18 15:13:12'),('dr.hong','홍박사','82c222d42c5610d0202e0aabe5f7fda62ca508c198a24db91b5b7298d453f4be','dr.hong','naver.com','2023-11-20 01:04:36'),('etetet','et','f0aadcf2a9317ee17975d83922a8cb536d99975ceec59f1123df7a787a63e52a','sieun043','ssafy.com','2023-11-20 04:13:44'),('hong','홍박사','527217651bab02f56e48d8791e0b6424f364fb7a9c2ad69daac72539e95ed868','hong','ssafy.com','2023-11-20 01:12:45'),('hong10','홍박사','8a8fe820cd28592e4ea4bd10586ed42480dcb1324d32b3ea2080c612ffb3a7b7','hong10!!!','ssafy.com','2023-11-23 01:20:02'),('hong3','홍박사','e0adcb9c34249becc4f14df63843ff5997aafbdedf5c2ac262aaf3c609d354f2','hong','ssafy.com','2023-11-20 07:56:12'),('hong5','홍박사','3b2a99a3749c57045b3507d222c46cbd3835e72aac79721a7d0c6fed69589511','hong','kakao.com','2023-11-21 04:24:15'),('honghong','홍박사','f446ebb518e872969c3ca221452075a8f87f2727d40d4989dff625e4025999ca','hong','ssafy.com','2023-11-20 01:14:15'),('kimssafy','김싸피','dbc255970faea59b34d2381f857ded070e3591601d6d8e9df192ac8fd18f04b8','kim','ssafy.com','2023-11-20 01:18:53'),('rla','rla','d133dd723147b1d512dade61b36a60664cdc0dd2a824a6518c7468c44e7bce7c','rla','ssafy.com','2023-11-20 08:15:57'),('rla1234','rla1234','74b02b4872e9d5efa0ebe54fc5fed7d8ed0d560b0024cffb7a130861cc9471a9','rla','ssafy.com','2023-11-20 08:26:13'),('seiun043','김시은','8e78b66d5e6437a41961d6d7f6e2d287ce8be906a8b286c0b66bcdfb84505978','sieun043','google.com','2023-11-16 06:28:41'),('sieun','김시은','d388590d85483a1a4c1d999db55b5cefa0f6b59d1188c4b111f275ba11a424a6','sieun043','google.com','2023-11-16 06:31:17'),('sieun000','김시은','f3c33eb9f5462036da93455add013bb5a1f95fd0da9c937db8fce7e9ad596777','sieun043','google.com','2023-11-20 00:30:53'),('sieun043','시은','9cceebe009edd2c44a077e5637aca20233fc45cd4aad27d047ce4e992cc02d1f','sieun043','google.com','2023-11-16 06:27:19'),('ssafy','김싸피','4d638826a2d7b1ca5ba24f43086f2bb7285750eef2743c8d5e416efe4b8f7683','ssafy','ssafy.com','2023-11-13 00:44:03'),('ssafy000','김싸피','2b63aa88e2a54943ecac27bdc016614c8438f09b1ab9b8ef86ef80b5e9707256','ssafy','kakao.com','2023-11-20 01:02:32'),('ssafy100','김싸피','921b2db503498fd0f9e53b9c4624cb6ae9476053b2692fc25389783b4b902961','ssafy100###','ssafy.com','2023-11-23 23:59:57'),('ssafy200','ssafy200','dfd11c651e0cb1f2c1c5a776535d15fddafe5dee4b8769783dafb70463fad2fb','ssafy200!!','ssafy.com','2023-11-23 07:32:58'),('ssafykim','김시은','226983fd39556ef447bab3ff623771f36f0c52c5f618e52f51c0c3ad29f687a8','ssafy','ssafy.com','2023-11-20 04:26:15'),('ssafykim06','ssafy','4bfda98a345ffb6dcfc35b99766fd19e4b6e32d0b8cd63d09a027a23c724ce74','ssafy','ssafy.com','2023-11-20 07:15:24'),('ssafykim2','ssafykim2','792d771190c91b79123b84ff14c8b29aefd43875a02d41ed6a6591de2df0d5e2','ssafy','ssafy.com','2023-11-20 05:08:33'),('ssafykim3','김싸피','1a79258873f9fbf016f84acf9fd37244a940f36c68b9a647c6d8162bead44d8b','ssafy','google.com','2023-11-20 05:15:18'),('ssafykim4','김싸피','ed8ed7a675def9dc5e4d5d0a927dd1650bd52b51a006fa9fa8859d8aee35c301','ssafy','ssafy.com','2023-11-20 06:31:30'),('ssafykim5','김싸피','8390f4e12246897b8cadd411caa290e8b49856c822510fbd61df09a5c0fe1ff6','ssafy','ssafy.com','2023-11-20 06:42:46'),('test','김시은','08025e19323cd20c438a1abea32026c44465ac885efacace5956e845901dfea9','test','ssafy.com','2023-11-20 08:06:18'),('utdutd','sieun','ff884b9092b7df86063c85df8dcc2dba59b6726bf7a1985310d8015e83c46459','sieun043','ssafy.com','2023-11-20 01:18:00');
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
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

-- Dump completed on 2023-11-24  9:16:57
