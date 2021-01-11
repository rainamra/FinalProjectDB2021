-- MySQL dump 10.13  Distrib 8.0.20, for macos10.15 (x86_64)
--
-- Host: localhost    Database: kinada_local
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Christy','081267889','Jl. Paus no.13'),(2,'Rama','08121277856','Jl. Tenggiri no.23'),(3,'Sabrina','0812908978','Komplek TBS no.c12'),(4,'Kiki','0812378845','Perumahan Pelita blok.s no.1'),(5,'Lala','0812388543','Jl.Kelapa II blok.A no.15'),(6,'Raka','0812673569','Jl.Raya Bekasi Timur II no.24'),(7,'Bunga','0812389963','Jl.kemuning no.23'),(8,'Glen','0812566439','Perumahan kelapa gading timur selatan blok.D no.2'),(9,'Caca','0812783606','Jl.Maritim III no.7'),(10,'Lisa','08134897677','Komplek Wijaya blok.S no.11'),(11,'Safira','0812789654','Komplek TBS blok.j no.9'),(12,'Callista','081212058449','Komplek Gading Nias blok');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `IngredientID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) DEFAULT NULL,
  `Price` int DEFAULT NULL,
  `SupplierID` int DEFAULT NULL,
  PRIMARY KEY (`IngredientID`),
  KEY `supplierID` (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Salmon 500gr',45000,3),(2,'Beef Slice 500gr',35000,3),(3,'Chicken Thigh 500gr',25000,3),(4,'Mayonesse 500gr',18000,1),(5,'Mozarella',55000,1),(6,'Salmon roe 250gr',45000,2);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientusage`
--

DROP TABLE IF EXISTS `ingredientusage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientusage` (
  `usageID` int NOT NULL AUTO_INCREMENT,
  `ItemID` int DEFAULT NULL,
  `IngredientID` int DEFAULT NULL,
  PRIMARY KEY (`usageID`),
  KEY `ItemID` (`ItemID`),
  CONSTRAINT `ingredientusage_ibfk_1` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientusage`
--

LOCK TABLES `ingredientusage` WRITE;
/*!40000 ALTER TABLE `ingredientusage` DISABLE KEYS */;
INSERT INTO `ingredientusage` VALUES (1,1,1),(2,2,1),(3,3,1),(4,4,2),(5,5,2),(6,6,2),(7,7,3),(8,8,3),(9,9,3),(10,1,4),(11,2,4),(12,3,4),(13,4,4),(14,5,4),(15,6,4),(16,7,4),(17,8,4),(18,9,4),(19,9,5),(20,8,5),(21,7,5),(22,6,5),(23,5,5),(24,4,5),(25,3,5),(26,2,5),(27,1,5),(28,1,6),(29,2,6),(30,3,6),(31,4,6),(32,5,6),(33,6,6),(34,7,6),(35,8,6),(36,9,6);
/*!40000 ALTER TABLE `ingredientusage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ItemID` int NOT NULL AUTO_INCREMENT,
  `ItemName` varchar(50) DEFAULT NULL,
  `Price` int DEFAULT NULL,
  `Cost` int DEFAULT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'salmon mentai mini',35000,25000),(2,'salmon mentai personal',45000,35000),(3,'salmon mentai party',95000,85000),(4,'beef mentai mini',40000,30000),(5,'beef mentai personal',50000,40000),(6,'beef mentai party',98000,88000),(7,'chicken mentai mini',30000,20000),(8,'chicken mentai personal',40000,30000),(9,'chicken mentai party',95000,85000),(10,'shrimp mentai mini',30000,20000),(11,'shrimp mentai personal',40000,30000),(12,'shrimp mentai party',95000,85000),(13,'Chia Yoghurt Mocha',25000,15000),(14,'Chia Yoghurt Coffee',25000,15000),(15,'Chia Yoghurt Grape',25000,15000),(16,'Chia Yoghurt Strawberry',25000,15000),(17,'Chia Yoghurt Soursop',25000,15000);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_details` (
  `OrderDetail` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `ItemID` int DEFAULT NULL,
  `Qty` int DEFAULT NULL,
  PRIMARY KEY (`OrderDetail`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `order_details_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
INSERT INTO `order_details` VALUES (8,3,9,1),(9,3,13,2),(10,3,17,2),(11,4,13,5),(12,4,6,1),(13,4,8,1),(14,5,2,1),(15,5,15,1),(16,6,11,1),(17,6,8,1),(18,6,5,1),(19,6,2,1),(20,7,4,1),(21,7,1,1),(22,7,10,1),(23,7,16,1),(24,7,15,1),(25,7,17,1),(26,8,14,1),(27,8,15,1),(28,8,16,1),(29,8,17,1),(30,8,6,1),(31,8,3,1),(32,9,6,1),(33,9,16,4),(34,10,11,1),(35,10,5,1),(36,11,12,2),(37,11,6,2),(38,11,9,2),(55,19,3,1),(56,19,6,1),(57,20,5,1),(58,20,8,1),(60,22,5,1),(61,22,8,1),(62,23,5,1),(63,23,16,1),(64,24,2,1),(65,25,3,1),(66,26,6,1),(67,27,6,1);
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `OrderID` int NOT NULL AUTO_INCREMENT,
  `CustomerID` int DEFAULT NULL,
  `StaffID` int DEFAULT NULL,
  `OrderDate` date DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  `TotalPrice` int DEFAULT NULL,
  PRIMARY KEY (`OrderID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (3,3,1,'2020-09-21','DELIVERED',195000),(4,4,1,'2020-10-16','DELIVERED',263000),(5,5,2,'2020-10-18','DELIVERED',70000),(6,6,2,'2020-11-12','DELIVERED',175000),(7,3,2,'2020-11-12','DELIVERED',180000),(8,1,2,'2020-12-22','DELIVERED',293000),(9,7,2,'2020-12-22','DELIVERED',198000),(10,8,2,'2021-01-09','DELIVERED',90000),(11,4,1,'2021-01-09','IN THE PROCESS',576000),(19,12,1,'2021-01-10','IN THE PROCESS',193000),(20,11,1,'2021-01-10','IN THE PROCESS',90000),(22,6,1,'2021-01-10','IN THE PROCESS',90000),(23,10,1,'2021-01-10','IN THE PROCESS',75000),(24,2,1,'2021-01-11','IN THE PROCESS',45000),(25,4,1,'2021-01-11','IN THE PROCESS',95000),(26,8,1,'2021-01-11','IN THE PROCESS',98000),(27,9,1,'2021-01-11','IN THE PROCESS',98000);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `owner_insight`
--

DROP TABLE IF EXISTS `owner_insight`;
/*!50001 DROP VIEW IF EXISTS `owner_insight`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `owner_insight` AS SELECT 
 1 AS `Month`,
 1 AS `Income`,
 1 AS `Revenue`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `PaymentID` int NOT NULL AUTO_INCREMENT,
  `OrderID` int DEFAULT NULL,
  `PaymentCtgry` varchar(30) DEFAULT NULL,
  `Status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`PaymentID`),
  KEY `OrderID` (`OrderID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (3,3,'Cash','PAID'),(4,4,'Bank Transfer','PAID'),(5,5,'Bank Transfer','PAID'),(6,6,'Bank Transfer','PAID'),(7,7,'Bank Transfer','PAID'),(8,8,'Cash','PAID'),(9,9,'Cash','PAID'),(10,10,'Cash','PAID'),(11,11,'Bank Transfer','UNPAID'),(20,19,'GoPay','UNPAID'),(21,20,'GoPay','UNPAID'),(23,22,'GoPay','UNPAID'),(24,23,'Bank Transfer','UNPAID'),(25,24,'GoPay','UNPAID'),(26,25,'Bank Transfer','UNPAID'),(27,26,'GoPay','UNPAID'),(28,27,'Bank Transfer','UNPAID');
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `StaffID` int NOT NULL AUTO_INCREMENT,
  `Firstname` varchar(35) DEFAULT NULL,
  `Lastname` varchar(35) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Address` varchar(200) DEFAULT NULL,
  `Password` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`StaffID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Rainamira','Azzahra','081212058449','raina_mira@yahoo.com','jl.Pemuda Komplek Taman Berdikari Sentosa Blok.M No.12','rainamra'),(2,'Radisa','Hussein','0812902346','radisa_hussein@gmail.com','Jl.Kemang Raya no.6','ucen2'),(3,'Rayhan','Ali','08123678445','rayhan_ali@gmail.com','Jl.Bintaro Raya Sektor VIII No.19','ali3');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `SupplierID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(30) DEFAULT NULL,
  `PhoneNumber` varchar(20) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (1,'Andrian Good Food','0827884511','jl. pesanggrahan no.2'),(2,'Amadea Fresh','081446754','jl. kelapa sawit no.9'),(3,'Susan Prima Food','0812212677893','jl. paus blok.s no.5');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `owner_insight`
--

/*!50001 DROP VIEW IF EXISTS `owner_insight`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `owner_insight` AS select monthname(`orders`.`OrderDate`) AS `Month`,sum((`item`.`Price` * `order_details`.`Qty`)) AS `Income`,sum(((`item`.`Price` - `item`.`Cost`) * `order_details`.`Qty`)) AS `Revenue` from ((`item` join `orders`) join `order_details`) where ((`order_details`.`ItemID` = `item`.`ItemID`) and (`order_details`.`OrderID` = `orders`.`OrderID`)) group by `Month` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-11 12:04:07
