CREATE DATABASE IF NOT EXISTS `springbook`;
USE `springbook`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(10) primary key,
  `name` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `email` varchar(100) NOT NULL,
  `level` tinyint NOT NULL,
  `login` int NOT NULL,
  `recommend` int NOT NULL
) DEFAULT CHARSET=utf8;