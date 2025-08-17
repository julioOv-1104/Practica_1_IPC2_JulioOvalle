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
  `codigo_actividad` varchar(10) NOT NULL,
  `codigo_evento` varchar(10) DEFAULT NULL,
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
INSERT INTO `actividad` VALUES ('ACT-0','kp','TALLER','encargado@algo.com','13:00:00','14:00:00','prueba 2',12),('ACT-01','EV-01','TALLER','encargado@algo.com','10:00:00','11:00:00','actividad de prueba',20),('ACT-02','EV-01','DEBATE','6515@algo.com','12:00:00','13:00:00','PRUEBA DE CUPO',1),('akaka','kp','TALLER','uuuuuu','10:00:00','11:00:00','pri',50);
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
  `codigo_actividad` varchar(10) DEFAULT NULL,
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
INSERT INTO `asistencia` VALUES ('uuuuuu','akaka'),('encargado@algo.com','ACT-01'),('encargado@algo.com','akaka'),('6515@algo.com','ACT-02');
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
  `codigo_evento` varchar(10) DEFAULT NULL,
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
INSERT INTO `certificado` VALUES ('encargado@algo.com','EV-01'),('encargado@algo.com','kp'),('6515@algo.com','EV-01');
/*!40000 ALTER TABLE `certificado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `codigo_evento` varchar(10) NOT NULL,
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
INSERT INTO `evento` VALUES ('apapapa','2000-01-02','CONGRESO','primera prueba','donde sea',20,0),('EV-01','2025-08-13','CONGRESO','prueba','donde sea',25,0),('EV-02','2025-08-13','TALLER','PRUEBA DE CUPOS','AQUI',1,0),('EVE','2025-08-16','CONGRESO','Evento costo','sdsfsf',50,120),('EVENTO 2','2025-08-16','CONGRESO','Evento costo 2','sdsfsf',50,50.5),('kp','2000-04-04','CHARLA','sfsfsfsf','wffe',25,0);
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
  `codigo_evento` varchar(10) DEFAULT NULL,
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
INSERT INTO `inscripcion` VALUES ('ASISTENTE','julio@algo.com','apapapa',1),('TALLERISTA','uuuuuu','kp',1),('ASISTENTE','pppp@algo.com','kp',1),('CONFERENCISTA','encargado@algo.com','EV-01',1),('TALLERISTA','encargado@algo.com','kp',1),('TALLERISTA','6515@algo.com','kp',1),('TALLERISTA','6515@algo.com','EV-02',0),('ASISTENTE','6515@algo.com','EV-01',1),('CONFERENCISTA','julio@algo.com','EVENTO 2',1),('CONFERENCISTA','encargado@algo.com','EVENTO 2',1),('TALLERISTA','uuuuuu','EVENTO 2',1);
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
  `codigo_evento` varchar(10) DEFAULT NULL,
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
INSERT INTO `pago` VALUES ('julio@algo.com','apapapa','TRANSFERENCIA',50),('uuuuuu','kp','EFECTIVO',50),('pppp@algo.com','kp','EFECTIVO',50),('encargado@algo.com','EV-01','EFECTIVO',50),('encargado@algo.com','kp','EFECTIVO',50),('6515@algo.com','kp','EFECTIVO',50),('6515@algo.com','EV-01','EFECTIVO',50),('julio@algo.com','EVENTO 2','EFECTIVO',50),('encargado@algo.com','EVENTO 2','EFECTIVO',50),('uuuuuu','EVENTO 2','TRANSFERENCIA',50.8);
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
INSERT INTO `participante` VALUES (' uuuuuu ','eeeee','ESTUDIANTE','bdfbdfb'),('6515@algo.com','CUNOC','PROFESIONAL','Allan'),('ajajsjsjsjsj@algo.com','CUNOC','PROFESIONAL','julio'),('encargado@algo.com','CUNOC','PROFESIONAL','encargado'),('GGGGGGG@algo.com','CUNOC','PROFESIONAL','Allan'),('hyhhyhhy@algo.com','sdccscsc','INVITADO','refeferf'),('julio@algo.com','CUNOC','ESTUDIANTE','julio'),('pppp@algo.com','cddddd','PROFESIONAL','dffgb'),('uuuuuu','eeeeeeeee','INVITADO','hngngng');
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

-- Dump completed on 2025-08-16 21:36:10
