CREATE DATABASE  IF NOT EXISTS `mobiauto_teste_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mobiauto_teste_db`;
-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: mobiauto_teste_db
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.1

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Cliente Teste','cliente_teste@gmail.com','1199587878'),(2,'Cliente Teste','cliente@teste.com','123456789'),(3,'Cliente Teste','cliente@teste.com','123456789'),(5,'Cliente Teste','cliente@teste.com','123456789'),(6,'Cliente Teste','cliente@teste.com','123456789'),(7,'Cliente Teste','cliente@teste.com','123456789'),(8,'Novo Cliente','novo@cliente.com','987654321'),(9,'Cliente Teste','cliente@teste.com','123456789'),(10,'Cliente Atualizado','clienteatualizado@teste.com','987654321'),(11,'Cliente Teste','cliente@teste.com','123456789'),(12,'Cliente Teste','cliente@teste.com','123456789'),(14,'Cliente Teste','cliente@teste.com','123456789'),(15,'Cliente Teste','cliente@teste.com','123456789'),(16,'Cliente Teste','cliente@teste.com','123456789'),(17,'Novo Cliente','novo@cliente.com','987654321'),(18,'Cliente Teste','cliente@teste.com','123456789'),(19,'Cliente Atualizado','clienteatualizado@teste.com','987654321'),(20,'Cliente Teste','cliente@teste.com','123456789'),(21,'Cliente Teste','cliente@teste.com','123456789'),(23,'Cliente Teste','cliente@teste.com','123456789'),(24,'Cliente Teste','cliente@teste.com','123456789'),(25,'Cliente Teste','cliente@teste.com','123456789'),(26,'Novo Cliente','novo@cliente.com','987654321'),(27,'Cliente Teste','cliente@teste.com','123456789'),(28,'Cliente Atualizado','clienteatualizado@teste.com','987654321'),(29,'Cliente Teste','cliente@teste.com','123456789'),(30,'Cliente Teste','cliente@teste.com','123456789'),(32,'Cliente Teste','cliente@teste.com','123456789'),(33,'Cliente Teste','cliente@teste.com','123456789'),(34,'Cliente Teste','cliente@teste.com','123456789'),(35,'Novo Cliente','novo@cliente.com','987654321'),(36,'Cliente Teste','cliente@teste.com','123456789'),(37,'Cliente Atualizado','clienteatualizado@teste.com','987654321'),(38,'Cliente Teste','cliente@teste.com','123456789'),(39,'Cliente Teste','cliente@teste.com','123456789'),(41,'Cliente Teste','cliente@teste.com','123456789'),(42,'Cliente Teste','cliente@teste.com','123456789'),(43,'Cliente Teste','cliente@teste.com','123456789'),(44,'Novo Cliente','novo@cliente.com','987654321'),(45,'Cliente Teste','cliente@teste.com','123456789'),(46,'Cliente Atualizado','clienteatualizado@teste.com','987654321'),(47,'Cliente Teste','cliente@teste.com','123456789');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oportunidade`
--

DROP TABLE IF EXISTS `oportunidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `oportunidade` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` enum('NOVO','EM_ATENDIMENTO','CONCLUIDO') NOT NULL,
  `motivo_conclusao` varchar(255) DEFAULT NULL,
  `data_atribuicao` date NOT NULL,
  `data_conclusao` date DEFAULT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  `cliente_id` bigint DEFAULT NULL,
  `veiculo_id` bigint DEFAULT NULL,
  `usuario_id` bigint DEFAULT NULL,
  `revenda_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh01npshahpnheswqb9ewxs0xv` (`cliente_id`),
  KEY `FKge92hp5fwhqtk8keq5t64kk38` (`revenda_id`),
  KEY `FKrorwqq35tl2ovpnogji2ucd5o` (`usuario_id`),
  KEY `FKnd5glssqea1hqcm5n7aqqd0gf` (`veiculo_id`),
  CONSTRAINT `FKge92hp5fwhqtk8keq5t64kk38` FOREIGN KEY (`revenda_id`) REFERENCES `revenda` (`id`),
  CONSTRAINT `FKh01npshahpnheswqb9ewxs0xv` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKnd5glssqea1hqcm5n7aqqd0gf` FOREIGN KEY (`veiculo_id`) REFERENCES `veiculo` (`id`),
  CONSTRAINT `FKrorwqq35tl2ovpnogji2ucd5o` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oportunidade`
--

LOCK TABLES `oportunidade` WRITE;
/*!40000 ALTER TABLE `oportunidade` DISABLE KEYS */;
INSERT INTO `oportunidade` VALUES (1,'NOVO','string','2024-08-30','2024-08-30',1,1,1,2,1),(3,'EM_ATENDIMENTO',NULL,'2024-09-01',NULL,1,NULL,NULL,26,10);
/*!40000 ALTER TABLE `oportunidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revenda`
--

DROP TABLE IF EXISTS `revenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `revenda` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(255) NOT NULL,
  `nome_social` varchar(255) NOT NULL,
  `ativo` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnpj` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revenda`
--

LOCK TABLES `revenda` WRITE;
/*!40000 ALTER TABLE `revenda` DISABLE KEYS */;
INSERT INTO `revenda` VALUES (1,'20800712000188','Teste LTDA',1),(2,'20800712000100','Revenda 2 teste',1),(3,'12345678901234','Revenda Teste',1),(6,'28028921000181','Revenda Teste',1),(8,'79318073000186','Revenda Atualizada',0),(10,'58184554000176','Revenda Teste',1);
/*!40000 ALTER TABLE `revenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `cargo` enum('PROPRIETARIO','GERENTE','ASSISTENTE','ADMINISTRADOR') NOT NULL,
  `revenda_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `FKa5l9c04st9kg2mishitvaassx` (`revenda_id`),
  CONSTRAINT `FKa5l9c04st9kg2mishitvaassx` FOREIGN KEY (`revenda_id`) REFERENCES `revenda` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (2,'Teste Assistente Atualizado','teste_assistente@mobiauto.com','$2a$10$xP5EVQy9dZOKIwuqwM6nNOCqLWKfr5FR3fqkCTGBr8mZatnn3S7sO','ASSISTENTE',1),(3,'Teste Gerente','teste_gerente@mobiauto.com','$2a$10$5eGGGRmQf8h8w96n90Zz9uPRkHtcoQfPHz9K8LxT8zPBZKf3/wLEO','GERENTE',1),(4,'Teste Proprietario','teste_proprietario@mobiauto.com','$2a$10$XmLoNGGlk0PLe40tzOiSuuWitYZfY9Md/0S/ZOfk39TjlPw30krUC','PROPRIETARIO',1),(5,'Administrador','admin@mobiauto.com','$2a$10$WOlzetoP4R/e39SZ610zwOax8cIKAkGG2g.5NvfBtbQFeJraTp2Uu','ADMINISTRADOR',NULL),(6,'Novo Usuario','novo@teste.com','$2a$10$CDOWu.vC7cXPgdMAaK2JEetpSaqJ8yu11iCv7HFl7OYeFDNRUUMau','GERENTE',6),(7,'Usuario Atualizado','atualizado@teste.com','$2a$10$j.6Mc5ce53Bs0zG9utfnwuKcXpWI2lQfBpX783vgwjlCL766N.vTS','GERENTE',6),(24,'Gerente Teste','gerente@teste.com','123456','GERENTE',10),(25,'Assistente 1','assistente1@teste.com','123456','ASSISTENTE',10),(26,'Assistente 2','assistente2@teste.com','123456','ASSISTENTE',10);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veiculo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `marca` varchar(255) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `versao` varchar(255) NOT NULL,
  `ano_modelo` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veiculo`
--

LOCK TABLES `veiculo` WRITE;
/*!40000 ALTER TABLE `veiculo` DISABLE KEYS */;
INSERT INTO `veiculo` VALUES (1,'Honda','Civic','EXL',2019),(2,'Toyota','Corolla','1.8 Flex',2020),(3,'Honda','Civic','2.0 Sport',2021),(4,'Toyota','Corolla','1.8 Flex',2020),(5,'Toyota','Corolla','1.8 Flex',2020),(6,'Toyota','Corolla','1.8 Flex',2020),(7,'Nissan','Sentra','2.0 SL',2022),(8,'Toyota','Corolla','1.8 Flex',2020),(10,'Toyota','Corolla','1.8 Flex',2020),(11,'Toyota','Corolla','1.8 Flex',2020),(12,'Honda','Civic','2.0 Sport',2021),(13,'Toyota','Corolla','1.8 Flex',2020),(14,'Toyota','Corolla','1.8 Flex',2020),(15,'Toyota','Corolla','1.8 Flex',2020),(16,'Nissan','Sentra','2.0 SL',2022),(17,'Toyota','Corolla','1.8 Flex',2020),(19,'Toyota','Corolla','1.8 Flex',2020),(20,'Toyota','Corolla','1.8 Flex',2020),(21,'Honda','Civic','2.0 Sport',2021),(22,'Toyota','Corolla','1.8 Flex',2020),(23,'Toyota','Corolla','1.8 Flex',2020),(24,'Toyota','Corolla','1.8 Flex',2020),(25,'Nissan','Sentra','2.0 SL',2022),(26,'Toyota','Corolla','1.8 Flex',2020),(28,'Toyota','Corolla','1.8 Flex',2020),(29,'Toyota','Corolla','1.8 Flex',2020),(30,'Honda','Civic','2.0 Sport',2021),(31,'Toyota','Corolla','1.8 Flex',2020),(32,'Toyota','Corolla','1.8 Flex',2020),(33,'Toyota','Corolla','1.8 Flex',2020),(34,'Nissan','Sentra','2.0 SL',2022),(35,'Toyota','Corolla','1.8 Flex',2020),(37,'Toyota','Corolla','1.8 Flex',2020),(38,'Toyota','Corolla','1.8 Flex',2020),(39,'Honda','Civic','2.0 Sport',2021),(40,'Toyota','Corolla','1.8 Flex',2020),(41,'Toyota','Corolla','1.8 Flex',2020),(42,'Toyota','Corolla','1.8 Flex',2020),(43,'Nissan','Sentra','2.0 SL',2022),(44,'Toyota','Corolla','1.8 Flex',2020),(46,'Toyota','Corolla','1.8 Flex',2020),(47,'Toyota','Corolla','1.8 Flex',2020),(48,'Honda','Civic','2.0 Sport',2021),(49,'Toyota','Corolla','1.8 Flex',2020),(50,'Toyota','Corolla','1.8 Flex',2020),(51,'Toyota','Corolla','1.8 Flex',2020),(52,'Nissan','Sentra','2.0 SL',2022),(53,'Toyota','Corolla','1.8 Flex',2020),(55,'Toyota','Corolla','1.8 Flex',2020);
/*!40000 ALTER TABLE `veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mobiauto_teste_db'
--

--
-- Dumping routines for database 'mobiauto_teste_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-01 15:38:07
