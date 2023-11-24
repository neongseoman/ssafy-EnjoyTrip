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
-- Table structure for table `id_info`
--

DROP TABLE IF EXISTS `id_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `id_info` (
  `id` varchar(16) NOT NULL,
  `salt` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `id_info`
--

LOCK TABLES `id_info` WRITE;
/*!40000 ALTER TABLE `id_info` DISABLE KEYS */;
INSERT INTO `id_info` VALUES ('111','380e2e3d-e02d-47fe-b8ee-2998eece12ca'),('222','b2017384-2757-4108-bfa9-17af25a0c7fa'),('asdf','a222bec9-2c54-4dc8-bc92-43d33e2d9c50'),('asdf1','94200cd9-1f70-41db-8e92-73b2dfb172b0'),('asdf12','658d9cb3-4380-41fb-b677-c3e50ffbf890'),('asdf12344','397fef8f-704a-40bd-a2ae-f206ab680490'),('asdfasdf','b8a235dd-08cd-4eb6-b746-65808b436795'),('dr.hong','510a1211-9e87-44c5-9da7-bd9bc6ddc6bb'),('etetet','27f8bf19-445b-4567-a2dd-25bb11e80fba'),('hong','b932b867-acfa-4e20-a85a-87b3058881a0'),('hong10','e5424dd0-b4f2-41ab-8d82-d40a3cf203c8'),('hong3','f2361d7a-4479-4391-a6cc-9ff4b090d112'),('hong5','85d4e275-d664-460a-a0e6-93ba9ba33710'),('honghong','d2a3be84-d9f4-4669-b26a-b10e9f6188b2'),('kimssafy','dea427ed-2331-4164-bbbf-1f0466775116'),('rla','b7c167c6-d468-41d0-8907-ae5b0eabfcfd'),('rla1234','18fd6403-5ad0-41cf-a7fd-539914696887'),('seiun043','e57a683c-c2b9-4501-b7a5-8b4ba767dc7d'),('sieun','d5f2ab09-c9d1-4d31-8a81-30e32070757c'),('sieun000','bb2ed4ae-a1dd-4927-854c-dc83e028f983'),('sieun043','0fcfacdd-630e-4d32-969d-932f5da56170'),('ssafy','91f92a42-8838-421a-9e97-5cbe6ce7bdfd'),('ssafy000','c986ef27-041d-4f8e-9a26-e38347d06229'),('ssafy100','01f04a35-2967-4d19-bc89-ebb9e5392469'),('ssafy200','eb6f581f-7ab0-43ca-a82f-191a0510ca97'),('ssafykim','dedfce58-ebdc-43bf-8a2a-3421212e3599'),('ssafykim06','efc321ff-9861-41f6-94d5-34dd76fa3374'),('ssafykim2','34b852f6-eb8e-4ef7-97f0-3d6cda7f4071'),('ssafykim3','fa1db5b8-2c69-4d55-b95a-9e9ee08f1142'),('ssafykim4','ee8f6327-cc12-4d8f-94e7-6d6bcaaeafdf'),('ssafykim5','eefdbbab-eb00-4cad-a828-1d073df2a900'),('test','e9883253-ac52-4e42-a4c2-f4b4f1312608'),('utdutd','65bfab3e-ac8d-4493-abb8-74f7e21f14c1');
/*!40000 ALTER TABLE `id_info` ENABLE KEYS */;
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
