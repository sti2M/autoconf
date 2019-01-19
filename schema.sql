-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: autoconf1
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) DEFAULT NULL,
  `sip_server` varchar(100) DEFAULT NULL,
  `sip_port` varchar(5) DEFAULT '5060',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'-----',NULL,NULL,'5060'),(8,'0339','fcjdsaktjer','pbx.irgups.ru','5060'),(9,'7775','jfkasljertewio','pbx.irgups.ru','5060'),(10,'0513','fjeowirje','pbx.irgups.ru','5060'),(11,'0189','nfdeksjahr','pbx.irgups.ru','5060'),(12,'NotUsed',NULL,NULL,NULL),(13,'6666','njk3j32sd','pbx.irgups.ru','5060');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mac` varchar(12) NOT NULL,
  `inventory_num` varchar(20) NOT NULL,
  `mol` varchar(100) DEFAULT NULL,
  `room` varchar(30) DEFAULT NULL,
  `sip_server` varchar(50) DEFAULT NULL,
  `sip_port` varchar(5) DEFAULT NULL,
  `ntp_server` varchar(50) DEFAULT NULL,
  `dhcp` tinyint(1) DEFAULT '0',
  `ip_address` varchar(50) DEFAULT NULL,
  `subnet_mask` varchar(20) DEFAULT NULL,
  `default_gw` varchar(50) DEFAULT NULL,
  `dns1` varchar(50) DEFAULT NULL,
  `dns2` varchar(50) DEFAULT NULL,
  `prov_url` varchar(100) DEFAULT NULL,
  `admin_login` varchar(50) DEFAULT NULL,
  `admin_password` varchar(50) DEFAULT NULL,
  `id_vendor` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `mac_UNIQUE` (`mac`),
  UNIQUE KEY `inventory_UNIQUE` (`inventory_num`),
  KEY `idVendor_idx` (`id_vendor`),
  CONSTRAINT `idVendor` FOREIGN KEY (`id_vendor`) REFERENCES `vendor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (22,'001565832F1C','004325934254','','',NULL,NULL,'172.20.79.254',1,'','','','','','','','',19),(24,'000561FDDDEE','436548943','','',NULL,NULL,'',0,'','','','','','','','',41),(25,'AABBCCDDEEFF','4355476765','','',NULL,NULL,'',0,'','','','','','','','',25),(26,'112233445566','4365467','','',NULL,NULL,'',0,'','','','','','','','',22),(27,'123412341234','57432897543','Шишкин Ю.Н.','А-517',NULL,NULL,'172.20.79.254',0,'172.20.64.142','255.255.255.0','172.20.64.1','172.20.64.1','172.20.4.1','tftp://pbx.irgups.ru','admin','jh342uiqhrfas',22),(28,'00268B5C11FE','742353847256832','Шишкин Ю.Н.','А-502',NULL,NULL,'172.20.79.254',1,'','','','','','tftp://pbx.irgups.ru','root','feyhwuir324bfd',25);
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone_account`
--

DROP TABLE IF EXISTS `phone_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `phone_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT '4',
  `line_number` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `phoneId_idx` (`phone_id`),
  KEY `accountId_idx` (`account_id`),
  CONSTRAINT `accountId` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `phoneId` FOREIGN KEY (`phone_id`) REFERENCES `phone` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone_account`
--

LOCK TABLES `phone_account` WRITE;
/*!40000 ALTER TABLE `phone_account` DISABLE KEYS */;
INSERT INTO `phone_account` VALUES (41,24,9,1),(43,24,11,2),(83,25,11,1),(87,25,9,3),(95,22,10,1),(97,22,8,2),(101,26,9,1),(102,26,8,2),(103,26,11,3),(104,27,8,1),(107,27,12,2),(108,27,12,3),(109,28,9,1),(111,28,11,2);
/*!40000 ALTER TABLE `phone_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendor`
--

DROP TABLE IF EXISTS `vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `lines_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendor`
--

LOCK TABLES `vendor` WRITE;
/*!40000 ALTER TABLE `vendor` DISABLE KEYS */;
INSERT INTO `vendor` VALUES (19,'Yealink/SIPT20P',2),(22,'Yealink/SIPT22P',3),(25,'Escene/ES290PN',2),(41,'DLink/DPH150S',2);
/*!40000 ALTER TABLE `vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-18 17:54:40
