-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-HackerRank
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `spammer` bit(1) DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_i7xei45auwq1f6vu25985riuh` (`user_account`),
  UNIQUE KEY `UK_l5g7gnrnmi97hb5or693xsoef` (`number`),
  CONSTRAINT `FK_i7xei45auwq1f6vu25985riuh` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_form`
--

DROP TABLE IF EXISTS `actor_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_form` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `terms_and_condicions` bit(1) DEFAULT NULL,
  `user_accountpassword` varchar(255) DEFAULT NULL,
  `user_accountuser` varchar(255) DEFAULT NULL,
  `vat` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_4cptr7kjrtd6sjpmr5mcw2irf` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_form`
--

LOCK TABLES `actor_form` WRITE;
/*!40000 ALTER TABLE `actor_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_form_surname`
--

DROP TABLE IF EXISTS `actor_form_surname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_form_surname` (
  `actor_form` int(11) NOT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_form_surname`
--

LOCK TABLES `actor_form_surname` WRITE;
/*!40000 ALTER TABLE `actor_form_surname` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor_form_surname` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_surname`
--

DROP TABLE IF EXISTS `actor_surname`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_surname` (
  `actor` int(11) NOT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_surname`
--

LOCK TABLES `actor_surname` WRITE;
/*!40000 ALTER TABLE `actor_surname` DISABLE KEYS */;
INSERT INTO `actor_surname` VALUES (671,'System'),(677,'Admin'),(678,'Gonzalez'),(679,'Garcia'),(680,'Lanzas'),(681,'Company\'s'),(682,'Company\'s');
/*!40000 ALTER TABLE `actor_surname` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `spammer` bit(1) DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7ohwsa2usmvu0yxb44je2lge` (`user_account`),
  UNIQUE KEY `UK_jpvhxc4moy111llxmou8w0dv9` (`number`),
  CONSTRAINT `FK_7ohwsa2usmvu0yxb44je2lge` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (671,0,'','163',6,19,'Customer 1','VISA','4716477920082572','correo@gmail.com','AcmeParade','+34647307406','',NULL,0.21,670),(677,0,'Reina Mercedes','728',10,20,'Customer 2','MASTER','5498128346540526','conwdasto@jmsx.es','Admin1','+34647607406','http://tinyurl.com/picture.png',NULL,0.21,664),(678,0,'Reina Mercedes','533',6,19,'Sponsor 1','AMEX','375278545368168','lusi@gamil.es','Admin2','+34647307406','http://tinyurl.com/picture.png',NULL,0.21,665);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `submit_moment` datetime DEFAULT NULL,
  `curricula` int(11) NOT NULL,
  `hacker` int(11) NOT NULL,
  `position` int(11) NOT NULL,
  `problem` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hsw5exxa4qe3jxi8xdhnn0dl5` (`curricula`),
  KEY `FK_3hgwemcpn15ns7bi2upsq613y` (`hacker`),
  KEY `FK_cqpb54jon3yjef814oj6g6o4n` (`position`),
  KEY `FK_7gn6fojv7rim6rxyglc6n9mt2` (`problem`),
  CONSTRAINT `FK_7gn6fojv7rim6rxyglc6n9mt2` FOREIGN KEY (`problem`) REFERENCES `problem` (`id`),
  CONSTRAINT `FK_3hgwemcpn15ns7bi2upsq613y` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`),
  CONSTRAINT `FK_cqpb54jon3yjef814oj6g6o4n` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_hsw5exxa4qe3jxi8xdhnn0dl5` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (751,0,NULL,NULL,'2018-03-16 15:20:00','PENDING',NULL,713,679,684,694),(752,0,NULL,NULL,'2018-03-15 15:21:00','PENDING',NULL,715,679,684,695),(753,0,'Explanation application 3','http://www.linkAnswer3.com','2018-03-16 15:22:00','SUBMITTED','2018-03-16 15:30:00',717,679,687,698),(754,0,NULL,NULL,'2018-03-15 15:31:00','PENDING',NULL,719,679,687,697);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_form`
--

DROP TABLE IF EXISTS `application_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_form` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_form`
--

LOCK TABLES `application_form` WRITE;
/*!40000 ALTER TABLE `application_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `application_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `spammer` bit(1) DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `commercial_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pno7oguspp7fxv0y2twgplt0s` (`user_account`),
  UNIQUE KEY `UK_9o7n0h0ll4v2ytdwbfr42uyoc` (`number`),
  CONSTRAINT `FK_pno7oguspp7fxv0y2twgplt0s` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` VALUES (681,0,'Reina Mercedes','837',11,22,'Company 1','VISA','4231348143458624','company1@gmail.es','Company1','+34647307480','http://tinyurl.com/picture.png',NULL,0.21,668,'commercialName1'),(682,0,'Reina Mercedes','988',11,20,'Company 2','VISA','4294148159742547','company2@gmail.es','Company2','+34647307484','http://tinyurl.com/picture.png',NULL,0.21,669,'commercialName2');
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company_form`
--

DROP TABLE IF EXISTS `company_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company_form` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `terms_and_condicions` bit(1) DEFAULT NULL,
  `user_accountpassword` varchar(255) DEFAULT NULL,
  `user_accountuser` varchar(255) DEFAULT NULL,
  `vat` double NOT NULL,
  `commercial_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kwa768kxhjmd06wvf5xm4vuxf` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company_form`
--

LOCK TABLES `company_form` WRITE;
/*!40000 ALTER TABLE `company_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `company_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters`
--

DROP TABLE IF EXISTS `configuration_parameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `banner` varchar(255) DEFAULT NULL,
  `country_phone_code` varchar(255) DEFAULT NULL,
  `finder_time` int(11) NOT NULL,
  `max_finder_results` int(11) NOT NULL,
  `sys_name` varchar(255) DEFAULT NULL,
  `welcome_message_en` varchar(255) DEFAULT NULL,
  `welcome_message_esp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters`
--

LOCK TABLES `configuration_parameters` WRITE;
/*!40000 ALTER TABLE `configuration_parameters` DISABLE KEYS */;
INSERT INTO `configuration_parameters` VALUES (683,0,'https://i.imgur.com/7b8lu4b.png','+34',1,10,'Acme Hacker Rank','Welcome to Acme hacker Rank! We\'re IT hacker\'s favourite job marketplace!','¡Bienvenidos a Acme Hacker Rank! ¡Somos el mercado de trabajo favorito de los profesionales de las TICs!');
/*!40000 ALTER TABLE `configuration_parameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_credit_card_make`
--

DROP TABLE IF EXISTS `configuration_parameters_credit_card_make`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_credit_card_make` (
  `configuration_parameters` int(11) NOT NULL,
  `credit_card_make` varchar(255) DEFAULT NULL,
  KEY `FK_msvomwet3mpkas6chhws7tv92` (`configuration_parameters`),
  CONSTRAINT `FK_msvomwet3mpkas6chhws7tv92` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_credit_card_make`
--

LOCK TABLES `configuration_parameters_credit_card_make` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_credit_card_make` DISABLE KEYS */;
INSERT INTO `configuration_parameters_credit_card_make` VALUES (683,'VISA'),(683,'MCARD'),(683,'AMEX'),(683,'DINNERS'),(683,'FLY');
/*!40000 ALTER TABLE `configuration_parameters_credit_card_make` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_negative_words`
--

DROP TABLE IF EXISTS `configuration_parameters_negative_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_negative_words` (
  `configuration_parameters` int(11) NOT NULL,
  `negative_words` varchar(255) DEFAULT NULL,
  KEY `FK_4auqbhvtjhot2strl1dqnumwd` (`configuration_parameters`),
  CONSTRAINT `FK_4auqbhvtjhot2strl1dqnumwd` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_negative_words`
--

LOCK TABLES `configuration_parameters_negative_words` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_negative_words` DISABLE KEYS */;
INSERT INTO `configuration_parameters_negative_words` VALUES (683,'not'),(683,'bad'),(683,'horrible'),(683,'average'),(683,'disaster'),(683,'malo'),(683,'media'),(683,'desastre');
/*!40000 ALTER TABLE `configuration_parameters_negative_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_positive_words`
--

DROP TABLE IF EXISTS `configuration_parameters_positive_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_positive_words` (
  `configuration_parameters` int(11) NOT NULL,
  `positive_words` varchar(255) DEFAULT NULL,
  KEY `FK_br643v4oj38jqfgcq5f11q8cx` (`configuration_parameters`),
  CONSTRAINT `FK_br643v4oj38jqfgcq5f11q8cx` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_positive_words`
--

LOCK TABLES `configuration_parameters_positive_words` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_positive_words` DISABLE KEYS */;
INSERT INTO `configuration_parameters_positive_words` VALUES (683,'good'),(683,'factastic'),(683,'excellent'),(683,'great'),(683,'amazing'),(683,'terrific'),(683,'beautiful'),(683,'bueno'),(683,'buena'),(683,'fantástico'),(683,'fantástica'),(683,'excelente'),(683,'genial'),(683,'terrorífico'),(683,'bonito'),(683,'bonita');
/*!40000 ALTER TABLE `configuration_parameters_positive_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration_parameters_spam_words`
--

DROP TABLE IF EXISTS `configuration_parameters_spam_words`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration_parameters_spam_words` (
  `configuration_parameters` int(11) NOT NULL,
  `spam_words` varchar(255) DEFAULT NULL,
  KEY `FK_r9o9dd0kww7hr04phroji3ig7` (`configuration_parameters`),
  CONSTRAINT `FK_r9o9dd0kww7hr04phroji3ig7` FOREIGN KEY (`configuration_parameters`) REFERENCES `configuration_parameters` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration_parameters_spam_words`
--

LOCK TABLES `configuration_parameters_spam_words` WRITE;
/*!40000 ALTER TABLE `configuration_parameters_spam_words` DISABLE KEYS */;
INSERT INTO `configuration_parameters_spam_words` VALUES (683,'sex'),(683,'viagra'),(683,'cialis'),(683,'one million'),(683,'you\'ve been selected'),(683,'nigeria'),(683,'sexo'),(683,'un millón'),(683,'ha sido seleccionado');
/*!40000 ALTER TABLE `configuration_parameters_spam_words` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `hacker` int(11) DEFAULT NULL,
  `personal_record` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2tuytvvko9f96rtan6nwbpuei` (`personal_record`),
  KEY `UK_cmpmwnbib50w71nx0wjlp8x79` (`hacker`),
  CONSTRAINT `FK_2tuytvvko9f96rtan6nwbpuei` FOREIGN KEY (`personal_record`) REFERENCES `personal_data` (`id`),
  CONSTRAINT `FK_cmpmwnbib50w71nx0wjlp8x79` FOREIGN KEY (`hacker`) REFERENCES `hacker` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
INSERT INTO `curricula` VALUES (703,0,679,704),(708,0,680,709),(713,0,NULL,714),(715,0,NULL,716),(717,0,NULL,718),(719,0,NULL,720);
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_educations`
--

DROP TABLE IF EXISTS `curricula_educations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_educations` (
  `curricula` int(11) NOT NULL,
  `educations` int(11) NOT NULL,
  UNIQUE KEY `UK_rl5ep498kg54h9i4cs1ilylxe` (`educations`),
  KEY `FK_povorssmgd90qbs758m08sd8m` (`curricula`),
  CONSTRAINT `FK_povorssmgd90qbs758m08sd8m` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_rl5ep498kg54h9i4cs1ilylxe` FOREIGN KEY (`educations`) REFERENCES `education_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_educations`
--

LOCK TABLES `curricula_educations` WRITE;
/*!40000 ALTER TABLE `curricula_educations` DISABLE KEYS */;
INSERT INTO `curricula_educations` VALUES (703,705),(708,710);
/*!40000 ALTER TABLE `curricula_educations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_miscellaneous`
--

DROP TABLE IF EXISTS `curricula_miscellaneous`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_miscellaneous` (
  `curricula` int(11) NOT NULL,
  `miscellaneous` int(11) NOT NULL,
  UNIQUE KEY `UK_od98qmjcfhormy4d6keghi2yv` (`miscellaneous`),
  KEY `FK_exd5m7a1kj79xrfh3r1e203i` (`curricula`),
  CONSTRAINT `FK_exd5m7a1kj79xrfh3r1e203i` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_od98qmjcfhormy4d6keghi2yv` FOREIGN KEY (`miscellaneous`) REFERENCES `miscellaneous_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_miscellaneous`
--

LOCK TABLES `curricula_miscellaneous` WRITE;
/*!40000 ALTER TABLE `curricula_miscellaneous` DISABLE KEYS */;
INSERT INTO `curricula_miscellaneous` VALUES (703,706),(708,711);
/*!40000 ALTER TABLE `curricula_miscellaneous` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_positions`
--

DROP TABLE IF EXISTS `curricula_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_positions` (
  `curricula` int(11) NOT NULL,
  `positions` int(11) NOT NULL,
  UNIQUE KEY `UK_7kes1j2krk1enuvxfa89afaw6` (`positions`),
  KEY `FK_iv0od2tbrouqgcj9qs8seq9k` (`curricula`),
  CONSTRAINT `FK_iv0od2tbrouqgcj9qs8seq9k` FOREIGN KEY (`curricula`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_7kes1j2krk1enuvxfa89afaw6` FOREIGN KEY (`positions`) REFERENCES `position_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_positions`
--

LOCK TABLES `curricula_positions` WRITE;
/*!40000 ALTER TABLE `curricula_positions` DISABLE KEYS */;
INSERT INTO `curricula_positions` VALUES (703,707),(708,712);
/*!40000 ALTER TABLE `curricula_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `education_data`
--

DROP TABLE IF EXISTS `education_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `education_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `institution` varchar(255) DEFAULT NULL,
  `mark` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `education_data`
--

LOCK TABLES `education_data` WRITE;
/*!40000 ALTER TABLE `education_data` DISABLE KEYS */;
INSERT INTO `education_data` VALUES (705,0,'Informática','2018-04-11','Universidad de Sevilla',7,'2014-04-11'),(710,0,'Magisterio',NULL,'Universidad de Sevilla',8,'2018-04-11');
/*!40000 ALTER TABLE `education_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder`
--

DROP TABLE IF EXISTS `finder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `creation_date` datetime DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `max_deadline` datetime DEFAULT NULL,
  `max_salary` double DEFAULT NULL,
  `min_deadline` datetime DEFAULT NULL,
  `min_salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder`
--

LOCK TABLES `finder` WRITE;
/*!40000 ALTER TABLE `finder` DISABLE KEYS */;
INSERT INTO `finder` VALUES (701,0,'2019-04-11 13:00:00',NULL,NULL,1800,NULL,NULL),(702,0,'2019-04-11 13:00:00',NULL,NULL,NULL,NULL,1800);
/*!40000 ALTER TABLE `finder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `finder_positions`
--

DROP TABLE IF EXISTS `finder_positions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `finder_positions` (
  `finder` int(11) NOT NULL,
  `positions` int(11) NOT NULL,
  KEY `FK_3d46gil0nks2dhgg7cyhv2m39` (`positions`),
  KEY `FK_l0b3qg4nly59oeqhe8ig5yssc` (`finder`),
  CONSTRAINT `FK_l0b3qg4nly59oeqhe8ig5yssc` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`),
  CONSTRAINT `FK_3d46gil0nks2dhgg7cyhv2m39` FOREIGN KEY (`positions`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `finder_positions`
--

LOCK TABLES `finder_positions` WRITE;
/*!40000 ALTER TABLE `finder_positions` DISABLE KEYS */;
/*!40000 ALTER TABLE `finder_positions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder`
--

DROP TABLE IF EXISTS `folder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `is_system_folder` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `actor` int(11) NOT NULL,
  `father` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_qjuch7uem8xlrhpvcwd24mkqd` (`actor`,`father`,`is_system_folder`),
  KEY `FK_13reojwl2ypyecuvvsopd48lq` (`father`),
  CONSTRAINT `FK_13reojwl2ypyecuvvsopd48lq` FOREIGN KEY (`father`) REFERENCES `folder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder`
--

LOCK TABLES `folder` WRITE;
/*!40000 ALTER TABLE `folder` DISABLE KEYS */;
INSERT INTO `folder` VALUES (672,0,'','Out box',671,NULL),(673,0,'','In box',671,NULL),(674,0,'','Trash box',671,NULL),(675,0,'','Notification box',671,NULL),(676,0,'','Spam box',671,NULL),(721,0,'','Out box',677,NULL),(722,0,'','In box',677,NULL),(723,0,'','Trash box',677,NULL),(724,0,'','Notification box',677,NULL),(725,0,'','Spam box',677,NULL),(726,0,'','Out box',678,NULL),(727,0,'','In box',678,NULL),(728,0,'','Trash box',678,NULL),(729,0,'','Notification box',678,NULL),(730,0,'','Spam box',678,NULL),(731,0,'','Out box',679,NULL),(732,0,'','In box',679,NULL),(733,0,'','Trash box',679,NULL),(734,0,'','Notification box',679,NULL),(735,0,'','Spam box',679,NULL),(736,0,'','Out box',680,NULL),(737,0,'','In box',680,NULL),(738,0,'','Trash box',680,NULL),(739,0,'','Notification box',680,NULL),(740,0,'','Spam box',680,NULL),(741,0,'','Out box',681,NULL),(742,0,'','In box',681,NULL),(743,0,'','Trash box',681,NULL),(744,0,'','Notification box',681,NULL),(745,0,'','Spam box',681,NULL),(746,0,'','Out box',682,NULL),(747,0,'','In box',682,NULL),(748,0,'','Trash box',682,NULL),(749,0,'','Notification box',682,NULL),(750,0,'','Spam box',682,NULL);
/*!40000 ALTER TABLE `folder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `folder_messages`
--

DROP TABLE IF EXISTS `folder_messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `folder_messages` (
  `folder` int(11) NOT NULL,
  `messages` int(11) NOT NULL,
  KEY `FK_pd7js9rp0nie7ft4b2ltq7jx0` (`messages`),
  KEY `FK_p4c0hkadh5uwpdsjbyqfkauak` (`folder`),
  CONSTRAINT `FK_p4c0hkadh5uwpdsjbyqfkauak` FOREIGN KEY (`folder`) REFERENCES `folder` (`id`),
  CONSTRAINT `FK_pd7js9rp0nie7ft4b2ltq7jx0` FOREIGN KEY (`messages`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `folder_messages`
--

LOCK TABLES `folder_messages` WRITE;
/*!40000 ALTER TABLE `folder_messages` DISABLE KEYS */;
/*!40000 ALTER TABLE `folder_messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hacker`
--

DROP TABLE IF EXISTS `hacker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hacker` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cvv` varchar(255) DEFAULT NULL,
  `expiration_month` int(11) DEFAULT NULL,
  `expiration_year` int(11) DEFAULT NULL,
  `holder_name` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `spammer` bit(1) DEFAULT NULL,
  `vat` double DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  `finder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pechhr6iex4b7l2rymmx1xi38` (`user_account`),
  UNIQUE KEY `UK_bt998x9pkwrggt6v3aihfs4p1` (`number`),
  KEY `FK_eohjws41xqofou03dl7t7aku4` (`finder`),
  CONSTRAINT `FK_pechhr6iex4b7l2rymmx1xi38` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`),
  CONSTRAINT `FK_eohjws41xqofou03dl7t7aku4` FOREIGN KEY (`finder`) REFERENCES `finder` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hacker`
--

LOCK TABLES `hacker` WRITE;
/*!40000 ALTER TABLE `hacker` DISABLE KEYS */;
INSERT INTO `hacker` VALUES (679,1,'Reina Mercedes','266',10,19,'Sponsor 2','VISA','4532787155338743','garcia@gmail.es','Hacker1','+34647307406','http://tinyurl.com/picture.png',NULL,0.21,666,701),(680,1,'Reina Mercedes','885',2,19,'Sponsor 3','VISA','4716699361876929','lanzas@gmail.es','Hacker2','+34647307406','http://tinyurl.com/picture.png',NULL,0.21,667,702);
/*!40000 ALTER TABLE `hacker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `body` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `priority` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `sender` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_recipients`
--

DROP TABLE IF EXISTS `message_recipients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_recipients` (
  `message` int(11) NOT NULL,
  `recipients` int(11) NOT NULL,
  KEY `FK_1odmg2n3n487tvhuxx5oyyya2` (`message`),
  CONSTRAINT `FK_1odmg2n3n487tvhuxx5oyyya2` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_recipients`
--

LOCK TABLES `message_recipients` WRITE;
/*!40000 ALTER TABLE `message_recipients` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_recipients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message_tags`
--

DROP TABLE IF EXISTS `message_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_tags` (
  `message` int(11) NOT NULL,
  `tags` varchar(255) DEFAULT NULL,
  KEY `FK_suckduhsrrtot7go5ejeev57w` (`message`),
  CONSTRAINT `FK_suckduhsrrtot7go5ejeev57w` FOREIGN KEY (`message`) REFERENCES `message` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_tags`
--

LOCK TABLES `message_tags` WRITE;
/*!40000 ALTER TABLE `message_tags` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_data`
--

DROP TABLE IF EXISTS `miscellaneous_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `free_text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_data`
--

LOCK TABLES `miscellaneous_data` WRITE;
/*!40000 ALTER TABLE `miscellaneous_data` DISABLE KEYS */;
INSERT INTO `miscellaneous_data` VALUES (706,0,'Miscellaneous data 1'),(711,0,'Miscellaneous data 2');
/*!40000 ALTER TABLE `miscellaneous_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `miscellaneous_data_attachments`
--

DROP TABLE IF EXISTS `miscellaneous_data_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `miscellaneous_data_attachments` (
  `miscellaneous_data` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_e1oadh6x6vsemmnrwp19vocmr` (`miscellaneous_data`),
  CONSTRAINT `FK_e1oadh6x6vsemmnrwp19vocmr` FOREIGN KEY (`miscellaneous_data`) REFERENCES `miscellaneous_data` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `miscellaneous_data_attachments`
--

LOCK TABLES `miscellaneous_data_attachments` WRITE;
/*!40000 ALTER TABLE `miscellaneous_data_attachments` DISABLE KEYS */;
INSERT INTO `miscellaneous_data_attachments` VALUES (706,' Attachment 1 of Miscellaneous Data 1'),(706,' Attachment 2 of Miscellaneous Data 1'),(711,' Attachment 1 of Miscellaneous Data 2'),(711,' Attachment 2 of Miscellaneous Data 2');
/*!40000 ALTER TABLE `miscellaneous_data_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_data`
--

DROP TABLE IF EXISTS `personal_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `github` varchar(255) DEFAULT NULL,
  `linkedin` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_data`
--

LOCK TABLES `personal_data` WRITE;
/*!40000 ALTER TABLE `personal_data` DISABLE KEYS */;
INSERT INTO `personal_data` VALUES (704,0,'Hacker 1','http://www.githubHacker1.com','http://www.linkedInHacker1.com','657896321','Statement hacker 1'),(709,0,'Hacker 2','http://www.githubHacker2.com','http://www.linkedInHacker2.com','697125753','Statement hacker 2'),(714,0,'Hacker 2','http://www.githubHacker2.com','http://www.linkedInHacker2.com','697125753','Statement hacker 2'),(716,0,'Hacker 2','http://www.githubHacker2.com','http://www.linkedInHacker2.com','697125753','Statement hacker 2'),(718,0,'Hacker 2','http://www.githubHacker2.com','http://www.linkedInHacker2.com','697125753','Statement hacker 2'),(720,0,'Hacker 2','http://www.githubHacker2.com','http://www.linkedInHacker2.com','697125753','Statement hacker 2');
/*!40000 ALTER TABLE `personal_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `ticker` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_15390c4j2aeju6ha0iwvi6mc5` (`ticker`),
  KEY `FK_7qlfn4nye234rrm4w83nms1g8` (`company`),
  CONSTRAINT `FK_7qlfn4nye234rrm4w83nms1g8` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (684,0,'2020-04-24 00:00:00','Description position 1','FINAL','Profile for position 1',1500,'COMM-1234','Position 1',681),(685,0,'2020-05-24 00:00:00','Description position 2','DRAFT','Profile for position 2',1700,'COMM-1235','Position 2',682),(686,0,'2020-04-24 00:00:00','Description position 3','DRAFT','Profile for position 3',1750,'COMM-1236','Position 3',681),(687,0,'2020-04-24 00:00:00','Description position 4','FINAL','Profile for position 4',2000,'COMM-1237','Position 4',681),(688,0,'2020-04-24 00:00:00','Description position 5','DRAFT','Profile for position 5',1750,'COMM-1238','Position 5',681),(689,0,'2020-04-24 00:00:00','Description position 6','FINAL','Profile for position 6',1750,'COMM-1789','Position 6',681),(690,0,'2020-04-24 00:00:00','Description position 7','DRAFT','Profile for position 7',1750,'COMM-1784','Position 7',682),(691,0,'2020-04-24 00:00:00','Description position 8','DRAFT','Profile for position 8',1750,'COMM-1783','Position 8',682),(692,0,'2020-04-24 00:00:00','Description position 9','DRAFT','Profile for position 9',1750,'COMM-1782','Position 9',682),(693,0,'2020-04-24 00:00:00','Description position 10','DRAFT','Profile for position 10',1750,'COMM-1781','Position 10',682);
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_data`
--

DROP TABLE IF EXISTS `position_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_data` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_data`
--

LOCK TABLES `position_data` WRITE;
/*!40000 ALTER TABLE `position_data` DISABLE KEYS */;
INSERT INTO `position_data` VALUES (707,0,'Description position data 1','2018-04-11','2014-04-11','Position Data 1'),(712,0,'Description position data 2','2018-04-11','2014-04-11','Position Data 2');
/*!40000 ALTER TABLE `position_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_form`
--

DROP TABLE IF EXISTS `position_form`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_form` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `profile` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_form`
--

LOCK TABLES `position_form` WRITE;
/*!40000 ALTER TABLE `position_form` DISABLE KEYS */;
/*!40000 ALTER TABLE `position_form` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_form_skills`
--

DROP TABLE IF EXISTS `position_form_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_form_skills` (
  `position_form` int(11) NOT NULL,
  `skills` varchar(255) DEFAULT NULL,
  KEY `FK_o0q2imosbg7g8o5ft4uoow6ah` (`position_form`),
  CONSTRAINT `FK_o0q2imosbg7g8o5ft4uoow6ah` FOREIGN KEY (`position_form`) REFERENCES `position_form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_form_skills`
--

LOCK TABLES `position_form_skills` WRITE;
/*!40000 ALTER TABLE `position_form_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `position_form_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_form_technologies`
--

DROP TABLE IF EXISTS `position_form_technologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_form_technologies` (
  `position_form` int(11) NOT NULL,
  `technologies` varchar(255) DEFAULT NULL,
  KEY `FK_205g7fvpa0cptcd420157rbuc` (`position_form`),
  CONSTRAINT `FK_205g7fvpa0cptcd420157rbuc` FOREIGN KEY (`position_form`) REFERENCES `position_form` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_form_technologies`
--

LOCK TABLES `position_form_technologies` WRITE;
/*!40000 ALTER TABLE `position_form_technologies` DISABLE KEYS */;
/*!40000 ALTER TABLE `position_form_technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_skills`
--

DROP TABLE IF EXISTS `position_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_skills` (
  `position` int(11) NOT NULL,
  `skills` varchar(255) DEFAULT NULL,
  KEY `FK_iksb6ri4m9ktp19nm3n0iqq9k` (`position`),
  CONSTRAINT `FK_iksb6ri4m9ktp19nm3n0iqq9k` FOREIGN KEY (`position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_skills`
--

LOCK TABLES `position_skills` WRITE;
/*!40000 ALTER TABLE `position_skills` DISABLE KEYS */;
INSERT INTO `position_skills` VALUES (684,'Skill1 for position 1'),(684,'Skill2 for position 1'),(685,'Skill1 for position 2'),(686,'Skill1 for position 3'),(686,'Skill2 for position 3'),(687,'Skill1 for position 4'),(687,'Skill2 for position 4'),(688,'Skill1 for position 5'),(688,'Skill2 for position 5'),(689,'Skill1 for position 6'),(689,'Skill2 for position 6'),(690,'Skill1 for position 7'),(690,'Skill2 for position 7'),(691,'Skill1 for position 8'),(691,'Skill2 for position 8'),(692,'Skill1 for position 9'),(692,'Skill2 for position 9'),(693,'Skill1 for position 10'),(693,'Skill2 for position 10');
/*!40000 ALTER TABLE `position_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_technologies`
--

DROP TABLE IF EXISTS `position_technologies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `position_technologies` (
  `position` int(11) NOT NULL,
  `technologies` varchar(255) DEFAULT NULL,
  KEY `FK_gap9mgajhx1f17j61fkxaagvy` (`position`),
  CONSTRAINT `FK_gap9mgajhx1f17j61fkxaagvy` FOREIGN KEY (`position`) REFERENCES `position` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_technologies`
--

LOCK TABLES `position_technologies` WRITE;
/*!40000 ALTER TABLE `position_technologies` DISABLE KEYS */;
INSERT INTO `position_technologies` VALUES (684,'Technology1 for position 1'),(685,'Technology1 for position 1'),(686,'Technology1 for position 3'),(687,'Technology1 for position 4'),(688,'Technology1 for position 5'),(689,'Technology1 for position 6'),(690,'Technology1 for position 7'),(691,'Technology1 for position 8'),(692,'Technology1 for position 9'),(693,'Technology1 for position 10');
/*!40000 ALTER TABLE `position_technologies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem`
--

DROP TABLE IF EXISTS `problem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `hint` varchar(255) DEFAULT NULL,
  `mode` varchar(255) DEFAULT NULL,
  `statement` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `company` int(11) NOT NULL,
  `position` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_ehy58i7iq25u9d829b76s1891` (`position`),
  KEY `UK_1dla8eoii1nn6emoo4yv86pgb` (`company`),
  KEY `UK_ndgdut3l4nia07hshfrad1ye9` (`position`,`mode`),
  CONSTRAINT `FK_ehy58i7iq25u9d829b76s1891` FOREIGN KEY (`position`) REFERENCES `position` (`id`),
  CONSTRAINT `FK_1dla8eoii1nn6emoo4yv86pgb` FOREIGN KEY (`company`) REFERENCES `company` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem`
--

LOCK TABLES `problem` WRITE;
/*!40000 ALTER TABLE `problem` DISABLE KEYS */;
INSERT INTO `problem` VALUES (694,0,'Hint of problem 1','FINAL','Statement of problem 1','Problem 1',681,684),(695,0,'Hint of problem 2','FINAL','Statement of problem 2','Problem 2',681,684),(696,0,'Hint of problem 3','DRAFT','Statement of problem 3','Problem 3',682,685),(697,0,'Hint of problem 4','FINAL','Statement of problem 4','Problem 4',681,687),(698,0,'Hint of problem 5','FINAL','Statement of problem 5','Problem 5',681,687),(699,0,'Hint of problem 6','FINAL','Statement of problem 6','Problem 6',681,689),(700,0,'Hint of problem 7','FINAL','Statement of problem 7','Problem 7',681,689);
/*!40000 ALTER TABLE `problem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problem_attachments`
--

DROP TABLE IF EXISTS `problem_attachments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `problem_attachments` (
  `problem` int(11) NOT NULL,
  `attachments` varchar(255) DEFAULT NULL,
  KEY `FK_mhrgqo77annlexxubmtv4qvf7` (`problem`),
  CONSTRAINT `FK_mhrgqo77annlexxubmtv4qvf7` FOREIGN KEY (`problem`) REFERENCES `problem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problem_attachments`
--

LOCK TABLES `problem_attachments` WRITE;
/*!40000 ALTER TABLE `problem_attachments` DISABLE KEYS */;
INSERT INTO `problem_attachments` VALUES (694,'http://www.attachment1Problem1.com'),(694,'http://www.attachment2Problem1.com'),(695,'http://www.attachment1Problem2.com'),(697,'http://www.attachment1Problem4.com'),(698,'http://www.attachment1Problem5.com'),(699,'http://www.attachment1Problem6.com'),(700,'http://www.attachment1Problem7.com');
/*!40000 ALTER TABLE `problem_attachments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (664,0,'e00cf25ad42683b3df678c61f42c6bda','admin1'),(665,0,'c84258e9c39059a89ab77d846ddab909','admin2'),(666,0,'2ba2a8ac968a7a2b0a7baa7f2fef18d2','hacker1'),(667,0,'91af68b69a50a98dbc0800942540342c','hacker2'),(668,0,'df655f976f7c9d3263815bd981225cd9','company1'),(669,0,'d196a28097115067fefd73d25b0c0be8','company2'),(670,0,'54b53072540eeeb8f8e9343e71f28176','system');
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (664,'ADMIN'),(665,'ADMIN'),(666,'HACKER'),(667,'HACKER'),(668,'COMPANY'),(669,'COMPANY'),(670,'ADMIN');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-15 11:43:19
