-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: eventos_hyrule
-- ------------------------------------------------------
-- Server version	9.2.0

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

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `codigo_actividad` varchar(15) NOT NULL,
  `codigo_evento` varchar(15) DEFAULT NULL,
  `tipo_actividad` enum('CHARLA','TALLER','DEBATE','OTRA') DEFAULT NULL,
  `email_encargado` varchar(100) DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `titulo` varchar(200) DEFAULT NULL,
  `cupo_maximo` int DEFAULT NULL,
  PRIMARY KEY (`codigo_actividad`),
  KEY `codigo_evento` (`codigo_evento`),
  KEY `email_encargado` (`email_encargado`),
  CONSTRAINT `actividad_ibfk_1` FOREIGN KEY (`codigo_evento`) REFERENCES `evento` (`codigo_evento`),
  CONSTRAINT `actividad_ibfk_2` FOREIGN KEY (`email_encargado`) REFERENCES `participante` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES ('ACT-00000001','EVT-00000001','CHARLA','link@hyrule.com','10:00:00','12:00:00','Nuevos Runes',30),('ACT-00000002','EVT-00000002','TALLER','mipha@zora.net','09:00:00','11:00:00','Forja Básica',20);
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencia` (
  `email_participante` varchar(100) DEFAULT NULL,
  `codigo_actividad` varchar(15) DEFAULT NULL,
  KEY `email_participante` (`email_participante`),
  KEY `codigo_actividad` (`codigo_actividad`),
  CONSTRAINT `asistencia_ibfk_1` FOREIGN KEY (`email_participante`) REFERENCES `participante` (`email`),
  CONSTRAINT `asistencia_ibfk_2` FOREIGN KEY (`codigo_actividad`) REFERENCES `actividad` (`codigo_actividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
INSERT INTO `asistencia` VALUES ('zelda@hyrule.edu','ACT-00000001'),('impa@kakariko.org','ACT-00000001'),('link@hyrule.com','ACT-00000001'),('urbosa@gerudo.gov','ACT-00000002'),('link@hyrule.com','ACT-00000002');
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificado`
--

DROP TABLE IF EXISTS `certificado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificado` (
  `email_participante` varchar(100) DEFAULT NULL,
  `codigo_evento` varchar(15) DEFAULT NULL,
  KEY `email_participante` (`email_participante`),
  KEY `codigo_evento` (`codigo_evento`),
  CONSTRAINT `certificado_ibfk_1` FOREIGN KEY (`email_participante`) REFERENCES `participante` (`email`),
  CONSTRAINT `certificado_ibfk_2` FOREIGN KEY (`codigo_evento`) REFERENCES `evento` (`codigo_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificado`
--

LOCK TABLES `certificado` WRITE;
/*!40000 ALTER TABLE `certificado` DISABLE KEYS */;
INSERT INTO `certificado` VALUES ('zelda@hyrule.edu','EVT-00000001'),('impa@kakariko.org','EVT-00000001'),('link@hyrule.com','EVT-00000001'),('urbosa@gerudo.gov','EVT-00000002'),('link@hyrule.com','EVT-00000002');
/*!40000 ALTER TABLE `certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `codigo_evento` varchar(15) NOT NULL,
  `fecha` date DEFAULT NULL,
  `tipo_evento` enum('CHARLA','CONGRESO','TALLER','DEBATE') DEFAULT NULL,
  `titulo` varchar(200) DEFAULT NULL,
  `ubicacion` varchar(100) DEFAULT NULL,
  `cupo_maximo` int DEFAULT NULL,
  `costo_inscripcion` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES ('EVT-00000001','2025-08-25','CHARLA','Tecnología Sheikah','Auditorio Central',150,50),('EVT-00000002','2025-08-26','TALLER','Forja de Espadas','Sala Taller 1',50,120);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inscripcion` (
  `tipo_inscripcion` enum('ASISTENTE','CONFERENCISTA','TALLERISTA','OTRO') DEFAULT NULL,
  `email_participante` varchar(100) DEFAULT NULL,
  `codigo_evento` varchar(15) DEFAULT NULL,
  `es_valida` tinyint(1) DEFAULT NULL,
  KEY `email_participante` (`email_participante`),
  KEY `codigo_evento` (`codigo_evento`),
  CONSTRAINT `inscripcion_ibfk_1` FOREIGN KEY (`email_participante`) REFERENCES `participante` (`email`),
  CONSTRAINT `inscripcion_ibfk_2` FOREIGN KEY (`codigo_evento`) REFERENCES `evento` (`codigo_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES ('ASISTENTE','zelda@hyrule.edu','EVT-00000001',1),('CONFERENCISTA','link@hyrule.com','EVT-00000001',1),('ASISTENTE','impa@kakariko.org','EVT-00000001',1),('ASISTENTE','daruk@goron.co','EVT-00000001',1),('TALLERISTA','mipha@zora.net','EVT-00000002',1),('ASISTENTE','urbosa@gerudo.gov','EVT-00000002',1),('ASISTENTE','link@hyrule.com','EVT-00000002',1);
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `email_participante` varchar(100) DEFAULT NULL,
  `codigo_evento` varchar(15) DEFAULT NULL,
  `metodo_pago` enum('EFECTIVO','TRANSFERENCIA','TARJETA') DEFAULT NULL,
  `monto` double DEFAULT NULL,
  KEY `email_participante` (`email_participante`),
  KEY `codigo_evento` (`codigo_evento`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`email_participante`) REFERENCES `participante` (`email`),
  CONSTRAINT `pago_ibfk_2` FOREIGN KEY (`codigo_evento`) REFERENCES `evento` (`codigo_evento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES ('zelda@hyrule.edu','EVT-00000001','EFECTIVO',50),('link@hyrule.com','EVT-00000001','TRANSFERENCIA',50),('impa@kakariko.org','EVT-00000001','TARJETA',50),('daruk@goron.co','EVT-00000001','EFECTIVO',50),('mipha@zora.net','EVT-00000002','TRANSFERENCIA',120),('urbosa@gerudo.gov','EVT-00000002','TARJETA',120),('link@hyrule.com','EVT-00000002','EFECTIVO',120);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participante`
--

DROP TABLE IF EXISTS `participante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participante` (
  `email` varchar(100) NOT NULL,
  `institucion` varchar(100) DEFAULT NULL,
  `tipo_participante` enum('ESTUDIANTE','PROFESIONAL','INVITADO') DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participante`
--

LOCK TABLES `participante` WRITE;
/*!40000 ALTER TABLE `participante` DISABLE KEYS */;
INSERT INTO `participante` VALUES ('daruk@goron.co','Goron Co.','INVITADO','Daruk Goron'),('impa@kakariko.org','Kakariko Institute','PROFESIONAL','Impa Noh'),('link@hyrule.com','Triforce Software','PROFESIONAL','Link Hyrule'),('mipha@zora.net','Zora Clinic','PROFESIONAL','Mipha Zora'),('urbosa@gerudo.gov','Gerudo Council','PROFESIONAL','Urbosa Gerudo'),('zelda@hyrule.edu','Universidad de Hyrule','ESTUDIANTE','Zelda Hyrule');
/*!40000 ALTER TABLE `participante` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-17 21:31:30
