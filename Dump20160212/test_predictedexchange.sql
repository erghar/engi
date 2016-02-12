-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: test
-- ------------------------------------------------------
-- Server version	5.6.26-log

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
-- Table structure for table `predictedexchange`
--

DROP TABLE IF EXISTS `predictedexchange`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `predictedexchange` (
  `id` bigint(20) NOT NULL,
  `date` date DEFAULT NULL,
  `predictedValue` varchar(255) DEFAULT NULL,
  `predictedValueMean` varchar(255) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsyksbibm9fp5eh4yenqey41rs` (`companyId`),
  CONSTRAINT `FKsyksbibm9fp5eh4yenqey41rs` FOREIGN KEY (`companyId`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `predictedexchange`
--

LOCK TABLES `predictedexchange` WRITE;
/*!40000 ALTER TABLE `predictedexchange` DISABLE KEYS */;
INSERT INTO `predictedexchange` VALUES (14831,'2015-12-28','1.88','1.88',1),(14833,'2015-12-28','388.36','388.36',491),(14837,'2015-12-28','0.98','0.98',2),(15334,'2015-12-29','1.87','1.87',1),(15836,'2015-12-30','1.78','1.78',1),(15838,'2015-12-30','1.03','1.03',2),(15839,'2015-12-30','387.63','387.63',491),(16336,'2015-12-31','1.76','1.76',1),(16834,'2016-01-04','1.76','1.76',1),(16835,'2016-01-04','1.02','1.02',2),(16836,'2016-01-04','389.59','389.59',491),(17333,'2016-01-05','386.12','386.12',491),(17334,'2016-01-05','1.75','1.75',1),(17335,'2016-01-05','1.02','1.02',2),(17832,'2016-01-07','391.39','391.39',491),(17833,'2016-01-07','1.82','1.82',1),(17834,'2016-01-07','1.03','1.03',2),(18331,'2016-01-08','1.81','1.81',1),(18332,'2016-01-08','1.03','1.03',2),(18833,'2016-01-11','384.09','384.09',491),(18834,'2016-01-11','1.81','1.81',1),(18835,'2016-01-11','0.99','0.99',2),(18836,'2016-01-11','72.29','72.29',12334),(19333,'2016-01-13','1.82','1.82',1),(19334,'2016-01-13','1.01','1.01',2),(19335,'2016-01-13','383.22','383.22',491),(19832,'2016-01-14','73.79','73.79',12334),(19833,'2016-01-14','1.76','1.76',1),(19834,'2016-01-14','1.03','1.03',2),(19835,'2016-01-14','383.22','383.22',491),(20332,'2016-01-15','1.81','1.81',1),(20333,'2016-01-15','1.01','1.01',2),(20334,'2016-01-15','388.64','388.64',491),(20335,'2016-01-15','73.53','73.53',12334),(20832,'2016-01-19','1.8','1.8',1),(20833,'2016-01-19','1.03','1.03',2),(20834,'2016-01-19','73.77','73.77',12334),(20835,'2016-01-19','383.41','383.41',491),(21332,'2016-01-20','1.78','1.78',1),(21333,'2016-01-20','1.03','1.03',2),(21334,'2016-01-20','73.09','73.09',12334),(21335,'2016-01-20','375.72','375.72',491),(21832,'2016-01-22','1.78','1.78',1),(21833,'2016-01-22','1.03','1.03',2),(21834,'2016-01-22','73.25','73.25',12334),(21835,'2016-01-22','391.88','391.88',491),(22334,'2016-01-25','1.8','1.8',1),(22335,'2016-01-25','1.02','1.02',2),(22336,'2016-01-25','73.86','73.86',12334),(22337,'2016-01-25','386.32','386.32',491),(22338,'2016-01-25','56.88','56.88',36),(22826,'2016-02-02','1.75','1.75',1),(22827,'2016-02-02','1.02','1.02',2),(22828,'2016-02-02','57.43','57.43',36),(22829,'2016-02-02','376.26','376.26',491),(23316,'2016-02-03','1.78','1.78',1),(23317,'2016-02-03','1.03','1.03',2),(23318,'2016-02-03','57.32','57.32',36),(23319,'2016-02-03','386.23','386.23',491),(23806,'2016-02-04','1.76','1.76',1),(23807,'2016-02-04','1.02','1.02',2),(23808,'2016-02-04','83.66','83.66',12334),(23809,'2016-02-04','57.21','57.21',36),(23810,'2016-02-04','373.96','373.96',491),(24303,'2016-02-05','84.18','84.18',12334),(24304,'2016-02-05','1.76','1.76',1),(24305,'2016-02-05','1.02','1.02',2),(24306,'2016-02-05','386.17','386.17',491),(24307,'2016-02-05','57.34','57.34',36),(24796,'2016-02-08','378.68','378.68',491),(24797,'2016-02-08','1.8','1.8',1),(24798,'2016-02-08','1.03','1.03',2),(24799,'2016-02-08','57.29','57.29',36);
/*!40000 ALTER TABLE `predictedexchange` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-12 19:52:08
