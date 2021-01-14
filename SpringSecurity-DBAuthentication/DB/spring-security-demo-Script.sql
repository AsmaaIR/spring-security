DROP DATABASE  IF EXISTS `spring_security_demo`;

CREATE DATABASE  IF NOT EXISTS `spring_security_demo`;
USE `spring_security_demo`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- use generation tool: https://www.bcryptcalculator.com/
--
-- Default passwords here are: xyz123
--

INSERT INTO `user` (username,password,first_name,last_name,email)
VALUES 
('asmaa','$2a$10$ihHT6ANsJ/XhUOuPJRTwpus4PjwiNZ8J7bFg/vzqddfKuDVf41SdO','Asmaa','Ibrahim','asmaa@librahim.com'),
('rahma','$2a$10$ihHT6ANsJ/XhUOuPJRTwpus4PjwiNZ8J7bFg/vzqddfKuDVf41SdO','Rahma','Yassin','rahma@yassin.com'),
('nilly','$2a$10$ihHT6ANsJ/XhUOuPJRTwpus4PjwiNZ8J7bFg/vzqddfKuDVf41SdO','nilly','Hamzah','nilly@hamzah.com');


--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;


INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 3)
