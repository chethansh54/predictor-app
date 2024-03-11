CREATE TABLE `login_history` (
  `uname` varchar(50) DEFAULT NULL,
  `login_epoch` bigint DEFAULT NULL,
  `logout_epoch` bigint DEFAULT NULL,
  `access_date` datetime DEFAULT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `uname` (`uname`),
  CONSTRAINT `login_history_ibfk_1` FOREIGN KEY (`uname`) REFERENCES `users_info` (`uname`)
);


CREATE TABLE `processed_data` (
  `processing_id` int NOT NULL AUTO_INCREMENT,
  `sensor_name` varchar(50) DEFAULT NULL,
  `reading_mgdl` int DEFAULT NULL,
  `received_ts` bigint DEFAULT NULL,
  PRIMARY KEY (`processing_id`)
);

CREATE TABLE `raw_data` (
  `processing_id` int NOT NULL AUTO_INCREMENT,
  `sensor_name` varchar(100) DEFAULT NULL,
  `sensor_type` varchar(100) DEFAULT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `mrp_price` float DEFAULT NULL,
  `delay_seconds` int DEFAULT NULL,
  `reading_mgdl` int DEFAULT NULL,
  `received_ts` bigint DEFAULT NULL,
  PRIMARY KEY (`processing_id`)
);

CREATE TABLE `users_info` (
  `uname` varchar(50) NOT NULL,
  `password` varchar(30) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uname`)
);

CREATE TABLE jobconfigmanager (
servicename varchar(100),
jobstatus varchar(50),
lastupdatedts bigint
);


INSERT INTO jobconfigmanager VALUES ("data_injector","IDLE",1710079287);
INSERT INTO jobconfigmanager VALUES ("rmq_data_consumer","IDLE",1710079287);
