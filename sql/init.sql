CREATE DATABASE `db_shiro`

USE `db_shiro`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
                         `id` int(4) NOT NULL AUTO_INCREMENT,
                         `username` varchar(20) DEFAULT NULL,
                         `password` varchar(100) DEFAULT NULL,
                         UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `users`(`id`,`username`,`password`) values (1,'yicj','123');



USE `db_shiro`;

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `id` INT(4) NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(20) DEFAULT NULL,
  `pass` VARCHAR(100) DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


INSERT  INTO `members`(`id`,`userName`,`pass`) VALUES (1,'yicj','123');