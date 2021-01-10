DROP DATABASE  IF EXISTS `spring_security_demo`;

CREATE DATABASE  IF NOT EXISTS `spring_security_demo`;
USE `spring_security_demo`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- use generation tool: https://www.bcryptcalculator.com/
--
-- Default passwords here are: xyz123
--

INSERT INTO `users` 
VALUES 
('asmaa','{bcrypt}$2a$10$ihHT6ANsJ/XhUOuPJRTwpus4PjwiNZ8J7bFg/vzqddfKuDVf41SdO',1),
('rahma','{bcrypt}$2a$10$ihHT6ANsJ/XhUOuPJRTwpus4PjwiNZ8J7bFg/vzqddfKuDVf41SdO',1),
('nilly','{bcrypt}$2a$10$ihHT6ANsJ/XhUOuPJRTwpus4PjwiNZ8J7bFg/vzqddfKuDVf41SdO',1);

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('nilly','ROLE_EMPLOYEE'),
('asmaa','ROLE_EMPLOYEE'),
('asmaa','ROLE_MANAGER'),
('rahma','ROLE_EMPLOYEE'),
('rahma','ROLE_ADMIN');


