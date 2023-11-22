-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: db_system_agro
-- ------------------------------------------------------
-- Server version	11.2.1-MariaDB

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
-- Table structure for table `tb_bills_to_pay`
--

DROP TABLE IF EXISTS `tb_bills_to_pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_bills_to_pay` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_payment_method` int(10) unsigned NOT NULL,
  `id_purchase` int(10) unsigned NOT NULL,
  `expected_payment_date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `payment_date_made` double DEFAULT NULL,
  `amount_paid_` double DEFAULT NULL,
  `installment` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bills_to_pay_FKIndex1` (`id_purchase`),
  KEY `bills_to_pay_FKIndex2` (`id_payment_method`),
  CONSTRAINT `tb_bills_to_pay_ibfk_1` FOREIGN KEY (`id_purchase`) REFERENCES `tb_purchase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_bills_to_pay_ibfk_2` FOREIGN KEY (`id_payment_method`) REFERENCES `tb_payment_method` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bills_to_pay`
--

LOCK TABLES `tb_bills_to_pay` WRITE;
/*!40000 ALTER TABLE `tb_bills_to_pay` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_bills_to_pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_bills_to_receive`
--

DROP TABLE IF EXISTS `tb_bills_to_receive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_bills_to_receive` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_payment_method` int(10) unsigned NOT NULL,
  `id_production_sales` int(10) unsigned NOT NULL,
  `expected_receive_date` date DEFAULT NULL,
  `amount` date DEFAULT NULL,
  `installment` int(10) unsigned DEFAULT NULL,
  `amount_received` double DEFAULT NULL,
  `receive_date_made` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `bills_to_receive_FKIndex1` (`id_production_sales`),
  KEY `bills_to_receive_FKIndex2` (`id_payment_method`),
  CONSTRAINT `tb_bills_to_receive_ibfk_1` FOREIGN KEY (`id_production_sales`) REFERENCES `tb_production_sales` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_bills_to_receive_ibfk_2` FOREIGN KEY (`id_payment_method`) REFERENCES `tb_payment_method` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_bills_to_receive`
--

LOCK TABLES `tb_bills_to_receive` WRITE;
/*!40000 ALTER TABLE `tb_bills_to_receive` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_bills_to_receive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_category`
--

DROP TABLE IF EXISTS `tb_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_coffe_species`
--

DROP TABLE IF EXISTS `tb_coffe_species`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_coffe_species` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  `characteristics` varchar(150) DEFAULT NULL,
  `details` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_coffe_species`
--

LOCK TABLES `tb_coffe_species` WRITE;
/*!40000 ALTER TABLE `tb_coffe_species` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_coffe_species` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_crop`
--

DROP TABLE IF EXISTS `tb_crop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_crop` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_farm` int(10) unsigned NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `year` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `crop_FKIndex1` (`id_farm`),
  CONSTRAINT `tb_crop_ibfk_1` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_crop`
--

LOCK TABLES `tb_crop` WRITE;
/*!40000 ALTER TABLE `tb_crop` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_crop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_farm`
--

DROP TABLE IF EXISTS `tb_farm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_farm` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zipcode` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_farm`
--

LOCK TABLES `tb_farm` WRITE;
/*!40000 ALTER TABLE `tb_farm` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_farm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_farms_owner`
--

DROP TABLE IF EXISTS `tb_farms_owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_farms_owner` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_farm` int(10) unsigned NOT NULL,
  `id_users` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `farms_owner_FKIndex1` (`id_users`),
  KEY `farms_owner_FKIndex2` (`id_farm`),
  CONSTRAINT `tb_farms_owner_ibfk_1` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_farms_owner_ibfk_2` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_farms_owner`
--

LOCK TABLES `tb_farms_owner` WRITE;
/*!40000 ALTER TABLE `tb_farms_owner` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_farms_owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_logbook`
--

DROP TABLE IF EXISTS `tb_logbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_logbook` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_plot` int(10) unsigned NOT NULL,
  `id_farm` int(10) unsigned NOT NULL,
  `date` datetime DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `lighthouse` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `logbook_FKIndex1` (`id_farm`),
  KEY `logbook_FKIndex2` (`id_plot`),
  CONSTRAINT `tb_logbook_ibfk_1` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_logbook_ibfk_2` FOREIGN KEY (`id_plot`) REFERENCES `tb_plot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_logbook`
--

LOCK TABLES `tb_logbook` WRITE;
/*!40000 ALTER TABLE `tb_logbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_logbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_payment_method`
--

DROP TABLE IF EXISTS `tb_payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_payment_method` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_payment_method`
--

LOCK TABLES `tb_payment_method` WRITE;
/*!40000 ALTER TABLE `tb_payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phytosanitary`
--

DROP TABLE IF EXISTS `tb_phytosanitary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_phytosanitary` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_crop` int(10) unsigned NOT NULL,
  `id_farm` int(10) unsigned NOT NULL,
  `id_unit` int(10) unsigned NOT NULL,
  `procedures` varchar(255) DEFAULT NULL,
  `goal` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `phytosanitary_FKIndex1` (`id_unit`),
  KEY `phytosanitary_FKIndex2` (`id_farm`),
  KEY `phytosanitary_FKIndex3` (`id_crop`),
  CONSTRAINT `tb_phytosanitary_ibfk_1` FOREIGN KEY (`id_unit`) REFERENCES `tb_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_phytosanitary_ibfk_2` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_phytosanitary_ibfk_3` FOREIGN KEY (`id_crop`) REFERENCES `tb_crop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phytosanitary`
--

LOCK TABLES `tb_phytosanitary` WRITE;
/*!40000 ALTER TABLE `tb_phytosanitary` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_phytosanitary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_phytosanitary_detail`
--

DROP TABLE IF EXISTS `tb_phytosanitary_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_phytosanitary_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_products` int(10) unsigned NOT NULL,
  `id_pragues` int(10) unsigned NOT NULL,
  `id_phytosanitary` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `phytosanitary_detail_FKIndex1` (`id_phytosanitary`),
  KEY `phytosanitary_detail_FKIndex2` (`id_pragues`),
  KEY `phytosanitary_detail_FKIndex3` (`id_products`),
  CONSTRAINT `tb_phytosanitary_detail_ibfk_1` FOREIGN KEY (`id_phytosanitary`) REFERENCES `tb_phytosanitary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_phytosanitary_detail_ibfk_2` FOREIGN KEY (`id_pragues`) REFERENCES `tb_pragues` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_phytosanitary_detail_ibfk_3` FOREIGN KEY (`id_products`) REFERENCES `tb_products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_phytosanitary_detail`
--

LOCK TABLES `tb_phytosanitary_detail` WRITE;
/*!40000 ALTER TABLE `tb_phytosanitary_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_phytosanitary_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pictures_location_logbook`
--

DROP TABLE IF EXISTS `tb_pictures_location_logbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pictures_location_logbook` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_logbook` int(10) unsigned NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pictures_location_logbook_FKIndex1` (`id_logbook`),
  CONSTRAINT `tb_pictures_location_logbook_ibfk_1` FOREIGN KEY (`id_logbook`) REFERENCES `tb_logbook` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pictures_location_logbook`
--

LOCK TABLES `tb_pictures_location_logbook` WRITE;
/*!40000 ALTER TABLE `tb_pictures_location_logbook` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pictures_location_logbook` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_plot`
--

DROP TABLE IF EXISTS `tb_plot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_plot` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_farm` int(10) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `area` double DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `plot_FKIndex1` (`id_farm`),
  CONSTRAINT `tb_plot_ibfk_1` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_plot`
--

LOCK TABLES `tb_plot` WRITE;
/*!40000 ALTER TABLE `tb_plot` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_plot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_plot_detail`
--

DROP TABLE IF EXISTS `tb_plot_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_plot_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_plot` int(10) unsigned NOT NULL,
  `id_coffe_species` int(10) unsigned NOT NULL,
  `coffe_quantity` double DEFAULT NULL,
  `foot_spacing` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `plot_detail_FKIndex1` (`id_coffe_species`),
  KEY `plot_detail_FKIndex2` (`id_plot`),
  CONSTRAINT `tb_plot_detail_ibfk_1` FOREIGN KEY (`id_coffe_species`) REFERENCES `tb_coffe_species` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_plot_detail_ibfk_2` FOREIGN KEY (`id_plot`) REFERENCES `tb_plot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_plot_detail`
--

LOCK TABLES `tb_plot_detail` WRITE;
/*!40000 ALTER TABLE `tb_plot_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_plot_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_pragues`
--

DROP TABLE IF EXISTS `tb_pragues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_pragues` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_pragues`
--

LOCK TABLES `tb_pragues` WRITE;
/*!40000 ALTER TABLE `tb_pragues` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_pragues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_production_sales`
--

DROP TABLE IF EXISTS `tb_production_sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_production_sales` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_suppliers` int(10) unsigned NOT NULL,
  `id_production_crop` int(10) unsigned NOT NULL,
  `sale_date` date DEFAULT NULL,
  `quantity` double DEFAULT NULL,
  `quotes` double DEFAULT NULL,
  `amount_money` double DEFAULT NULL,
  `payment_date` int(10) unsigned DEFAULT NULL,
  `total_installment` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `production_sales_FKIndex1` (`id_production_crop`),
  KEY `production_sales_FKIndex2` (`id_suppliers`),
  CONSTRAINT `tb_production_sales_ibfk_1` FOREIGN KEY (`id_production_crop`) REFERENCES `tb_prodution_crop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_production_sales_ibfk_2` FOREIGN KEY (`id_suppliers`) REFERENCES `tb_suppliers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_production_sales`
--

LOCK TABLES `tb_production_sales` WRITE;
/*!40000 ALTER TABLE `tb_production_sales` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_production_sales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_products`
--

DROP TABLE IF EXISTS `tb_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_products` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_unit` int(10) unsigned NOT NULL,
  `id_category` int(10) unsigned NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` int(10) unsigned DEFAULT NULL,
  `barcode` varchar(255) DEFAULT NULL,
  `stock` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `products_FKIndex1` (`id_category`),
  KEY `products_FKIndex2` (`id_unit`),
  CONSTRAINT `tb_products_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `tb_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_products_ibfk_2` FOREIGN KEY (`id_unit`) REFERENCES `tb_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_products`
--

LOCK TABLES `tb_products` WRITE;
/*!40000 ALTER TABLE `tb_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_prodution_crop`
--

DROP TABLE IF EXISTS `tb_prodution_crop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_prodution_crop` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_unit` int(10) unsigned NOT NULL,
  `id_farm` int(10) unsigned NOT NULL,
  `id_crop` int(10) unsigned NOT NULL,
  `quantity` double DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `results_crop_FKIndex1` (`id_crop`),
  KEY `results_crop_FKIndex2` (`id_farm`),
  KEY `prodution_crop_FKIndex3` (`id_unit`),
  CONSTRAINT `tb_prodution_crop_ibfk_1` FOREIGN KEY (`id_crop`) REFERENCES `tb_crop` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_prodution_crop_ibfk_2` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_prodution_crop_ibfk_3` FOREIGN KEY (`id_unit`) REFERENCES `tb_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_prodution_crop`
--

LOCK TABLES `tb_prodution_crop` WRITE;
/*!40000 ALTER TABLE `tb_prodution_crop` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_prodution_crop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_purchase`
--

DROP TABLE IF EXISTS `tb_purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_purchase` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_suppliers` int(10) unsigned NOT NULL,
  `id_farm` int(10) unsigned NOT NULL,
  `date` date DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `total_installment` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `purchase_FKIndex1` (`id_farm`),
  KEY `purchase_FKIndex2` (`id_suppliers`),
  CONSTRAINT `tb_purchase_ibfk_1` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_purchase_ibfk_2` FOREIGN KEY (`id_suppliers`) REFERENCES `tb_suppliers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_purchase`
--

LOCK TABLES `tb_purchase` WRITE;
/*!40000 ALTER TABLE `tb_purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_purchase_detail`
--

DROP TABLE IF EXISTS `tb_purchase_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_purchase_detail` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_products` int(10) unsigned NOT NULL,
  `id_unit` int(10) unsigned NOT NULL,
  `id_purchase` int(10) unsigned NOT NULL,
  `quantity` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `lote` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `purchase_detail_FKIndex1` (`id_purchase`),
  KEY `purchase_detail_FKIndexIdUnit` (`id_unit`),
  KEY `purchase_detail_FKIndex3` (`id_products`),
  CONSTRAINT `tb_purchase_detail_ibfk_1` FOREIGN KEY (`id_purchase`) REFERENCES `tb_purchase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_purchase_detail_ibfk_2` FOREIGN KEY (`id_unit`) REFERENCES `tb_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_purchase_detail_ibfk_3` FOREIGN KEY (`id_products`) REFERENCES `tb_products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_purchase_detail`
--

LOCK TABLES `tb_purchase_detail` WRITE;
/*!40000 ALTER TABLE `tb_purchase_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_purchase_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_signature_plans`
--

DROP TABLE IF EXISTS `tb_signature_plans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_signature_plans` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(150) DEFAULT NULL,
  `benefits` varchar(150) DEFAULT NULL,
  `total_price` double DEFAULT NULL,
  `total_installments` int(10) unsigned DEFAULT NULL,
  `installment_price` double DEFAULT NULL,
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_signature_plans`
--

LOCK TABLES `tb_signature_plans` WRITE;
/*!40000 ALTER TABLE `tb_signature_plans` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_signature_plans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_signature_to_receive`
--

DROP TABLE IF EXISTS `tb_signature_to_receive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_signature_to_receive` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_signature_plans` int(10) unsigned NOT NULL,
  `id_users` int(10) unsigned NOT NULL,
  `expected_payment_date` date DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `installment` int(10) unsigned DEFAULT NULL,
  `payment_date_made` date DEFAULT NULL,
  `amount_received` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `signature_to_receive_FKIndex1` (`id_users`),
  KEY `signature_to_receive_FKIndex2` (`id_signature_plans`),
  CONSTRAINT `tb_signature_to_receive_ibfk_1` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_signature_to_receive_ibfk_2` FOREIGN KEY (`id_signature_plans`) REFERENCES `tb_signature_plans` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_signature_to_receive`
--

LOCK TABLES `tb_signature_to_receive` WRITE;
/*!40000 ALTER TABLE `tb_signature_to_receive` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_signature_to_receive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_stock_movement`
--

DROP TABLE IF EXISTS `tb_stock_movement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_stock_movement` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_phytosanitary` int(10) unsigned NOT NULL,
  `id_purchase` int(10) unsigned NOT NULL,
  `id_users` int(10) unsigned NOT NULL,
  `id_products` int(10) unsigned NOT NULL,
  `id_unit` int(10) unsigned NOT NULL,
  `types` enum('input_value') DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `quantity_moved` decimal(15,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `stock_movement_FKIndex1` (`id_unit`),
  KEY `stock_movement_FKIndex2` (`id_products`),
  KEY `stock_movement_FKIndex3` (`id_users`),
  KEY `stock_movement_FKIndex4` (`id_purchase`),
  KEY `stock_movement_FKIndex5` (`id_phytosanitary`),
  CONSTRAINT `tb_stock_movement_ibfk_1` FOREIGN KEY (`id_unit`) REFERENCES `tb_unit` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_stock_movement_ibfk_2` FOREIGN KEY (`id_products`) REFERENCES `tb_products` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_stock_movement_ibfk_3` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_stock_movement_ibfk_4` FOREIGN KEY (`id_purchase`) REFERENCES `tb_purchase` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_stock_movement_ibfk_5` FOREIGN KEY (`id_phytosanitary`) REFERENCES `tb_phytosanitary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_stock_movement`
--

LOCK TABLES `tb_stock_movement` WRITE;
/*!40000 ALTER TABLE `tb_stock_movement` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_stock_movement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_suppliers`
--

DROP TABLE IF EXISTS `tb_suppliers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_suppliers` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_farm` int(10) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `cnpj` decimal(20,0) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `suppliers_FKIndex1` (`id_farm`),
  CONSTRAINT `tb_suppliers_ibfk_1` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_suppliers`
--

LOCK TABLES `tb_suppliers` WRITE;
/*!40000 ALTER TABLE `tb_suppliers` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_suppliers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_unit`
--

DROP TABLE IF EXISTS `tb_unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_unit` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(150) DEFAULT NULL,
  `type` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_unit`
--

LOCK TABLES `tb_unit` WRITE;
/*!40000 ALTER TABLE `tb_unit` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_level`
--

DROP TABLE IF EXISTS `tb_user_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_level` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_level`
--

LOCK TABLES `tb_user_level` WRITE;
/*!40000 ALTER TABLE `tb_user_level` DISABLE KEYS */;
INSERT INTO `tb_user_level` VALUES (3,'SUPER ADMIN'), (2,'ADMIN'),(1,'USER');
/*!40000 ALTER TABLE `tb_user_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_permission`
--

DROP TABLE IF EXISTS `tb_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_users` int(10) unsigned NOT NULL,
  `id_farm` int(10) unsigned NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_permission_FKIndex1` (`id_farm`),
  KEY `user_permission_FKIndex2` (`id_users`),
  CONSTRAINT `tb_user_permission_ibfk_1` FOREIGN KEY (`id_farm`) REFERENCES `tb_farm` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_user_permission_ibfk_2` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_permission`
--

LOCK TABLES `tb_user_permission` WRITE;
/*!40000 ALTER TABLE `tb_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_subscriptions`
--

DROP TABLE IF EXISTS `tb_subscriptions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_subscriptions` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_users` int(10) unsigned NOT NULL,
  `subscription_id` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `subscriptions_FKIndex1` (`id_users`),
  CONSTRAINT `tb_subscriptions_ibfk_1` FOREIGN KEY (`id_users`) REFERENCES `tb_users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_subscriptions`
--

LOCK TABLES `tb_subscriptions` WRITE;
/*!40000 ALTER TABLE `tb_subscriptions` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_subscriptions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_users`
--

DROP TABLE IF EXISTS `tb_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_user_level` int(10) unsigned NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `doc` decimal(15,0) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `zipcode` decimal(10,0) DEFAULT NULL,
  `level` decimal(15,0) DEFAULT NULL,
  `creatd_by` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `users_FKIndex1` (`id`),
  CONSTRAINT `tb_users_ibfk_1` FOREIGN KEY (`id_user_level`) REFERENCES `tb_user_level` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_users`
--

LOCK TABLES `tb_users` WRITE;
/*!40000 ALTER TABLE `tb_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_system_agro'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13 23:01:26
