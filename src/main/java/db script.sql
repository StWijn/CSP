CREATE DATABASE  IF NOT EXISTS `cspaccount`;
USE `cspaccount`;

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_ref` int(11),
  `acc_number` varchar(45) DEFAULT NULL,
  `end_bal` DOUBLE,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;