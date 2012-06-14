CREATE DATABASE  IF NOT EXISTS `syo` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `syo`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: syo
-- ------------------------------------------------------
-- Server version	5.5.17

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
-- Table structure for table `sammlung`
--

DROP TABLE IF EXISTS `sammlung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sammlung` (
  `ID_Sammlung` int(11) NOT NULL AUTO_INCREMENT,
  `SammlungName` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Sammlung`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sammlung`
--

LOCK TABLES `sammlung` WRITE;
/*!40000 ALTER TABLE `sammlung` DISABLE KEYS */;
/*!40000 ALTER TABLE `sammlung` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objekt`
--

DROP TABLE IF EXISTS `objekt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objekt` (
  `ID_Objekt` int(11) NOT NULL AUTO_INCREMENT,
  `ObjektName` varchar(50) NOT NULL,
  `Typ_ID` int(11) NOT NULL,
  `Barcode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID_Objekt`),
  KEY `FK_Objekt_Typ` (`Typ_ID`),
  CONSTRAINT `FK_Objekt_Typ` FOREIGN KEY (`Typ_ID`) REFERENCES `typ` (`ID_Typ`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objekt`
--

LOCK TABLES `objekt` WRITE;
/*!40000 ALTER TABLE `objekt` DISABLE KEYS */;
/*!40000 ALTER TABLE `objekt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typ_feld`
--

DROP TABLE IF EXISTS `typ_feld`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typ_feld` (
  `Typ_ID` int(11) NOT NULL,
  `Feld_ID` int(11) NOT NULL,
  PRIMARY KEY (`Typ_ID`,`Feld_ID`),
  KEY `FK_Typ_Feld_Feld` (`Feld_ID`),
  CONSTRAINT `FK_Typ_Feld_Feld` FOREIGN KEY (`Feld_ID`) REFERENCES `feld` (`ID_Feld`) ON DELETE CASCADE,
  CONSTRAINT `FK_Typ_Feld_Typ` FOREIGN KEY (`Typ_ID`) REFERENCES `typ` (`ID_Typ`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typ_feld`
--

LOCK TABLES `typ_feld` WRITE;
/*!40000 ALTER TABLE `typ_feld` DISABLE KEYS */;
/*!40000 ALTER TABLE `typ_feld` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feld`
--

DROP TABLE IF EXISTS `feld`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feld` (
  `ID_Feld` int(11) NOT NULL AUTO_INCREMENT,
  `FeldName` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Feld`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feld`
--

LOCK TABLES `feld` WRITE;
/*!40000 ALTER TABLE `feld` DISABLE KEYS */;
/*!40000 ALTER TABLE `feld` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typ`
--

DROP TABLE IF EXISTS `typ`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `typ` (
  `ID_Typ` int(11) NOT NULL AUTO_INCREMENT,
  `TypName` varchar(50) NOT NULL,
  PRIMARY KEY (`ID_Typ`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typ`
--

LOCK TABLES `typ` WRITE;
/*!40000 ALTER TABLE `typ` DISABLE KEYS */;
/*!40000 ALTER TABLE `typ` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eigenschaft`
--

DROP TABLE IF EXISTS `eigenschaft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `eigenschaft` (
  `Objekt_ID` int(11) NOT NULL,
  `Feld_ID` int(11) NOT NULL,
  `Wert` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`Objekt_ID`,`Feld_ID`),
  KEY `FK_Eigenschaften_Wert` (`Feld_ID`),
  KEY `Fk_Eigenschaft_Objekt_ID` (`Objekt_ID`),
  KEY `Fk_Eigenschaft_Feld_ID` (`Feld_ID`),
  CONSTRAINT `Fk_Eigenschaft_Feld_ID` FOREIGN KEY (`Feld_ID`) REFERENCES `feld` (`ID_Feld`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `Fk_Eigenschaft_Objekt_ID` FOREIGN KEY (`Objekt_ID`) REFERENCES `objekt` (`ID_Objekt`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eigenschaft`
--

LOCK TABLES `eigenschaft` WRITE;
/*!40000 ALTER TABLE `eigenschaft` DISABLE KEYS */;
/*!40000 ALTER TABLE `eigenschaft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objekt_sammlung`
--

DROP TABLE IF EXISTS `objekt_sammlung`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `objekt_sammlung` (
  `Objekt_ID` int(11) NOT NULL,
  `Sammlung_ID` int(11) NOT NULL,
  PRIMARY KEY (`Objekt_ID`,`Sammlung_ID`),
  KEY `FK_Objekt_Sammlung_Sammlung` (`Sammlung_ID`),
  CONSTRAINT `FK_Objekt_Sammlung_Objekt` FOREIGN KEY (`Objekt_ID`) REFERENCES `objekt` (`ID_Objekt`) ON DELETE CASCADE,
  CONSTRAINT `FK_Objekt_Sammlung_Sammlung` FOREIGN KEY (`Sammlung_ID`) REFERENCES `sammlung` (`ID_Sammlung`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objekt_sammlung`
--

LOCK TABLES `objekt_sammlung` WRITE;
/*!40000 ALTER TABLE `objekt_sammlung` DISABLE KEYS */;
/*!40000 ALTER TABLE `objekt_sammlung` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-14  8:44:50
