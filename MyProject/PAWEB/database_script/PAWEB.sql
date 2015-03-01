 

--
-- Create schema paweb
--
 
CREATE DATABASE IF NOT EXISTS paweb;
USE paweb;

--
-- Definition of table `api_master`
--

DROP TABLE IF EXISTS `api_master`;
CREATE TABLE `api_master` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT,
  `api_name` varchar(45) NOT NULL,
  `api_path` varchar(45) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `expired_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`api_id`),
  KEY `FK_api_master_1` (`created_by`),
  CONSTRAINT `FK_api_master_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_master`
--

/*!40000 ALTER TABLE `api_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `api_master` ENABLE KEYS */;


--
-- Definition of table `documents_info`
--

DROP TABLE IF EXISTS `documents_info`;
CREATE TABLE `documents_info` (
  `document_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_number` varchar(45) NOT NULL,
  `title` varchar(500) NOT NULL,
  `author` varchar(128) NOT NULL,
  `date` datetime NOT NULL,
  `rank` int(8) NOT NULL,
  `search_key` varchar(45) NOT NULL,
  `title_href` varchar(128) DEFAULT NULL,
  `photo_path` varchar(128) DEFAULT NULL,
  `book_name` varchar(128) DEFAULT NULL,
  `book_language` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`document_id`,`title`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `documents_info`
--

/*!40000 ALTER TABLE `documents_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `documents_info` ENABLE KEYS */;


--
-- Definition of table `role_master`
--

DROP TABLE IF EXISTS `role_master`;
CREATE TABLE `role_master` (
  `role_master_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(45) DEFAULT NULL,
  `role_des` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`role_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_master`
--

/*!40000 ALTER TABLE `role_master` DISABLE KEYS */;
INSERT INTO `role_master` (`role_master_id`,`role_name`,`role_des`) VALUES 
 (1,'admin','Admin'),
 (2,'user','User');
/*!40000 ALTER TABLE `role_master` ENABLE KEYS */;


--
-- Definition of table `saved_histroy`
--

DROP TABLE IF EXISTS `saved_histroy`;
CREATE TABLE `saved_histroy` (
  `saved_history_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date_time` varchar(45) DEFAULT NULL,
  `saved_name` varchar(45) DEFAULT NULL,
  `docs_history` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`saved_history_id`),
  KEY `FK_saved_histroy_1` (`user_id`),
  CONSTRAINT `FK_saved_histroy_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `saved_histroy`
--

/*!40000 ALTER TABLE `saved_histroy` DISABLE KEYS */;
/*!40000 ALTER TABLE `saved_histroy` ENABLE KEYS */;


--
-- Definition of table `sub_documents_info`
--

DROP TABLE IF EXISTS `sub_documents_info`;
CREATE TABLE `sub_documents_info` (
  `sub_document_id` int(11) NOT NULL AUTO_INCREMENT,
  `sub_doc_name` varchar(45) NOT NULL,
  `title` varchar(45) DEFAULT NULL,
  `is_free` varchar(45) DEFAULT NULL,
  `author_name` varchar(45) DEFAULT NULL,
  `book_name` varchar(45) DEFAULT NULL,
  `primary_author` varchar(45) DEFAULT NULL,
  `others_authors` varchar(45) DEFAULT NULL,
  `published_date` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `resoruce_name` varchar(45) DEFAULT NULL,
  `OLCN` varchar(45) DEFAULT NULL,
  `CLEC` varchar(45) DEFAULT NULL,
  `doc_info_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sub_document_id`),
  KEY `FK_sub_documents_info_1` (`doc_info_id`),
  CONSTRAINT `FK_sub_documents_info_1` FOREIGN KEY (`doc_info_id`) REFERENCES `documents_info` (`document_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sub_documents_info`
--

/*!40000 ALTER TABLE `sub_documents_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_documents_info` ENABLE KEYS */;


--
-- Definition of table `transcation_log`
--

DROP TABLE IF EXISTS `transcation_log`;
CREATE TABLE `transcation_log` (
  `transcation_id` int(11) NOT NULL AUTO_INCREMENT,
  `transcation_name` varchar(45) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `transcation_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`transcation_id`),
  KEY `FK_transcation_log_1` (`created_by`),
  CONSTRAINT `FK_transcation_log_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transcation_log`
--

/*!40000 ALTER TABLE `transcation_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `transcation_log` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email_id` varchar(45) DEFAULT NULL,
  `security_question` varchar(128) DEFAULT NULL,
  `security_answer` varchar(128) DEFAULT NULL,
  `role_id` int(8) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_users_1` (`role_id`),
  CONSTRAINT `FK_users_1` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`user_id`,`user_name`,`password`,`first_name`,`last_name`,`email_id`,`security_question`,`security_answer`,`role_id`) VALUES 
 (1,'user','user','yrdffff','ddddd','subbuvmca@gmail.com','Your Name','subbu',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
