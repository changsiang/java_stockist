CREATE DATABASE  IF NOT EXISTS `stockist` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `stockist`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: stockist
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `PartID` int(11) NOT NULL,
  `Description` longtext,
  `UnitPrice` decimal(18,2) DEFAULT NULL,
  `Color` longtext,
  `Dimension` longtext,
  `Manufacturer` longtext,
  `ReorderLevel` int(11) DEFAULT NULL,
  `MinReorderQty` int(11) DEFAULT NULL,
  `ShelfLocation` varchar(45) DEFAULT NULL,
  `SupplierID` varchar(45) NOT NULL,
  `UnitsInStock` int(11) DEFAULT NULL,
  `UnitsOnOrder` int(11) DEFAULT NULL,
  `Discontinued` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`PartID`),
  KEY `fk_Products_Suppliers1_idx` (`SupplierID`),
  CONSTRAINT `fk_Products_Suppliers1` FOREIGN KEY (`SupplierID`) REFERENCES `suppliers` (`SupplierID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (123,'light bulb',1.23,'white','2cm','toyota',20,15,'1A','S1',13,15,0),(234,'Steelmate TPMS TP-S3E',168.00,'Black','NULL','Toyota',50,10,'4C','S1',45,8,0),(1876,'Low Beam H7 12V',0.99,'White','NULL','Honda',60,30,'2B','S1',89,4,0),(2346,'Pair Tie Rod Ball Joint ',10.55,'Silver','81mm','Honda',45,20,'2C','S1',80,0,0),(2593,'Mercedes Benz Steering Gear',45.34,'Black','NULL','Merzedez Benz',35,10,'2C','S1',53,0,0),(2786,'Iridium Tough Spark Plug - VK20',31.56,'Silver','NULL','Toyota',40,25,'2A','S1',49,0,0),(3265,'GM Rear Liftgate Hands Closing Module',75.90,'Silver','NULL','General Motors',30,10,'9F','S2',36,0,0),(3456,'Cellink B3 battery pack',54.00,'Black','NULL','Toyota',20,35,'5D','S2',92,0,0),(4564,'Thinkware X550-2CH',160.33,'Silver','2.7 LCD\"','Toyota',15,10,'1A','S2',10,15,0),(9342,'Plasmacluster Ion Generator ',98.50,'Gold','5cm (h) x 6.5cm (base) x 7.6cm (top)','Toyota',25,30,'4C','S2',65,0,0);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `suppliers`
--

DROP TABLE IF EXISTS `suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `suppliers` (
  `SupplierID` varchar(45) NOT NULL,
  `CompanyName` longtext NOT NULL,
  `ContactNumber` varchar(45) DEFAULT NULL,
  `Address` longtext,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `suppliers`
--

LOCK TABLES `suppliers` WRITE;
/*!40000 ALTER TABLE `suppliers` DISABLE KEYS */;
INSERT INTO `suppliers` VALUES ('S1','Borneo Motoring Pte Ltd','60627888','No. 15 Sim Min Ave, Singapore 329118'),('S10','Zion Auto Gallery Pte Ltd','64876627','Blk 105, Gangsa Road, #02-103, Singapore 2367'),('S11','Think One Automobile','61337446','Blk 671, Woodlands Dr 71 #04-51, Singapore 0512'),('S12','Zenith Automobile','60627330','Blk 56, #08-161 Telok Blangah Heights, Singapore 0410'),('S13','Wunder Auto Pte Ltd','66396294','Blk 267 Sembawang Drive #08-349 Singapore 2369'),('S2','Global Carz Pte Ltd','62788944','Blk 99, Balestier Road, #12-168, Singapore 1232'),('S22','ISS NUS','8888888','NUS'),('S3','Hoe Beng Auto Trading','61378791','Blk 20, Eunos Crescent, #04-2965, Singapore 1400'),('S4','Hong Seh Motors Pte Ltd','61772299','Blk 61, Upper Paya Lebar Road, Singapore 1953'),('S5','Index Credit Pte Ltd','63353246','Blk 89, Zion Road, #16-137, Singapore 0316'),('S6','Kao Lian Enterprise','67134432','Block 88 Demsey Road #01-01, Singapore 1234'),('S7','Karz Automobile Pte Ltd','64521637','Blk 749, Pasir Ris St 71, #09-66, Singapore 1651'),('S8','Ken Jie Cars','66248675','Blk 12, Dover Close East, #08-208, Singapore 1027'),('S9','HTS Motor','67347064','Blk 27, Marine Crescent, #05-05, Singapore 2345');
/*!40000 ALTER TABLE `suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usagerecorddetails`
--

DROP TABLE IF EXISTS `usagerecorddetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usagerecorddetails` (
  `TransDetailID` int(11) NOT NULL AUTO_INCREMENT,
  `TransID` varchar(255) NOT NULL,
  `Products_PartID` int(11) NOT NULL,
  `UsedQty` int(11) NOT NULL,
  PRIMARY KEY (`TransDetailID`),
  KEY `fk_UsageRecordDetails_Products1_idx` (`Products_PartID`),
  KEY `fk_usagerecorddetails_usagerecords1_idx` (`TransID`),
  CONSTRAINT `fk_UsageRecordDetails_Products1` FOREIGN KEY (`Products_PartID`) REFERENCES `products` (`PartID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_usagerecorddetails_usagerecords1` FOREIGN KEY (`TransID`) REFERENCES `usagerecords` (`TransID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usagerecorddetails`
--

LOCK TABLES `usagerecorddetails` WRITE;
/*!40000 ALTER TABLE `usagerecorddetails` DISABLE KEYS */;
INSERT INTO `usagerecorddetails` VALUES (1,'1512914136069',123,1),(2,'1512914136069',234,1),(3,'1512914339929',123,1),(4,'1512914339929',234,1),(5,'1512914582600',123,1),(6,'1512914582600',234,1),(7,'1512915000885',123,1),(8,'1512915000885',234,1),(9,'1512915619978',123,5),(10,'1512915619978',234,3),(23,'1513093958837',123,4),(24,'1513093958837',234,6),(25,'1513093958837',1876,1),(26,'1513093958837',2346,7),(27,'1513093958837',2593,10),(28,'1513094076200',4564,13),(29,'1513094076200',9342,2),(30,'1513094076200',3456,4),(31,'1513094076200',2786,7),(32,'1513094126002',2593,5),(33,'1513094126002',2786,7),(34,'1513094126002',3265,4),(35,'1513094126002',3456,2),(36,'1513094126002',1876,8),(37,'1513094192908',2593,7),(38,'1513094192908',2786,1),(39,'1513094192908',4564,1),(40,'1513094234594',123,1),(41,'1513094234594',234,3),(42,'1513099820811',4564,1);
/*!40000 ALTER TABLE `usagerecorddetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usagerecords`
--

DROP TABLE IF EXISTS `usagerecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usagerecords` (
  `TransID` varchar(255) NOT NULL,
  `CustomerName` longtext,
  `DateUsed` date DEFAULT NULL,
  `UserID` int(11) DEFAULT NULL,
  PRIMARY KEY (`TransID`),
  KEY `fk_usagerecords_users1_idx` (`UserID`),
  CONSTRAINT `fk_usagerecords_users1` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usagerecords`
--

LOCK TABLES `usagerecords` WRITE;
/*!40000 ALTER TABLE `usagerecords` DISABLE KEYS */;
INSERT INTO `usagerecords` VALUES ('1512914136069','Thet Naing Aye','2016-07-10',3),('1512914339929','Peter Parker','2017-08-10',1),('1512914582600','Bruce Wayne','2017-09-20',2),('1512915000885','Tony Stark ','2017-10-10',2),('1512915619978','Clerk Kent','2017-11-10',2),('1513093958837','The Nameless Graduate Student','2017-12-12',6),('1513094076200','Cecilia','2017-12-12',6),('1513094126002','Brian S. Smith','2017-12-12',6),('1513094192908','Tajel','2017-12-12',6),('1513094234594','Sangeeta Singh','2017-12-12',6),('1513099820811','Shaldon Cooper','2017-12-13',6);
/*!40000 ALTER TABLE `usagerecords` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `UserRole` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UserName_UNIQUE` (`UserName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Jason','12345','inactive'),(2,'Naing','1234','mechanic'),(3,'Sum','1234','mechanic'),(4,'ChangSiang','1234','mechanic'),(5,'Alice','1234','admin'),(6,'admin','1234','admin');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-12-13  1:34:01
