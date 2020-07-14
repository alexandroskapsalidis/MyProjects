-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: project_part_b
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `assignment_per_course_per_student`
--

DROP TABLE IF EXISTS `assignment_per_course_per_student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignment_per_course_per_student` (
  `COURSE_ID` int(11) NOT NULL,
  `STUDENT_ID` int(11) NOT NULL,
  `ASSIGNMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`COURSE_ID`,`STUDENT_ID`,`ASSIGNMENT_ID`),
  KEY `STUDENT_ID` (`STUDENT_ID`),
  KEY `ASSIGNMENT_ID` (`ASSIGNMENT_ID`),
  CONSTRAINT `assignment_per_course_per_student_ibfk_1` FOREIGN KEY (`COURSE_ID`) REFERENCES `course` (`COURSE_ID`),
  CONSTRAINT `assignment_per_course_per_student_ibfk_2` FOREIGN KEY (`STUDENT_ID`) REFERENCES `student` (`STUDENT_ID`),
  CONSTRAINT `assignment_per_course_per_student_ibfk_3` FOREIGN KEY (`ASSIGNMENT_ID`) REFERENCES `assignment` (`ASSIGNMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignment_per_course_per_student`
--

LOCK TABLES `assignment_per_course_per_student` WRITE;
/*!40000 ALTER TABLE `assignment_per_course_per_student` DISABLE KEYS */;
INSERT INTO `assignment_per_course_per_student` VALUES (1,100,2),(1,102,1),(2,103,4),(2,104,6),(3,105,7),(3,106,8),(3,107,9),(4,108,10),(4,109,11);
/*!40000 ALTER TABLE `assignment_per_course_per_student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-22 20:38:08
