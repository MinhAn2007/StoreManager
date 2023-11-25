-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.1.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for lab02
CREATE DATABASE IF NOT EXISTS `lab02` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `lab02`;

-- Dumping structure for table lab02.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(250) NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `cust_name` varchar(150) NOT NULL,
  `phone` varchar(15) NOT NULL,
  PRIMARY KEY (`cust_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.customer: ~16 rows (approximately)
INSERT INTO `customer` (`cust_id`, `address`, `email`, `cust_name`, `phone`) VALUES
	(1, '123 Main Street', 'customer1@example.com', 'John Doe', '123-456-7890'),
	(2, '456 Elm Street', 'customer2@example.com', 'Alice Johnson', '987-654-3210'),
	(3, '789 Oak Street', 'customer3@example.com', 'Bob Smith', '555-123-4567'),
	(4, '321 Birch Street', 'customer4@example.com', 'Mary White', '777-888-9999'),
	(5, '987 Pine Street', 'customer5@example.com', 'David Brown', '111-222-3333'),
	(6, '555 Cedar Street', 'customer6@example.com', 'Linda Davis', '444-555-6666'),
	(7, '111 Willow Street', 'customer7@example.com', 'Tom Johnson', '666-777-8888'),
	(8, '222 Redwood Street', 'customer8@example.com', 'Susan Lee', '999-000-1111'),
	(9, '333 Magnolia Street', 'customer9@example.com', 'Michael Wilson', '222-333-4444'),
	(10, '444 Maple Street', 'customer10@example.com', 'Sarah Johnson', '888-999-0000'),
	(14, '123 Main Street', 'customerAn@example.com', 'Hang cua Vo Ngoc Minh An', '123-456-7890'),
	(15, '21', '123@gmail', 'null', '1231'),
	(16, '21', 'minhan200702@gmail.com', 'null', '212121'),
	(19, '21', 'minhan2007012312312@gmail.com', 'bbbbb', '212121'),
	(21, '21', 'minhan200712312302@gmail.com', 'bbbbb', '212121'),
	(22, '21', 'minh123123an200702@gmail.com', 'bbbbb', '212121');

-- Dumping structure for table lab02.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `emp_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(250) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(150) DEFAULT NULL,
  `full_name` varchar(150) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.employee: ~10 rows (approximately)
INSERT INTO `employee` (`emp_id`, `address`, `dob`, `email`, `full_name`, `phone`, `status`) VALUES
	(1, '123 Main St', '1990-01-01', 'john@example.com', 'John Doe', '123-456-7890', 1),
	(2, '456 Elm St', '1985-02-15', 'jane@example.com', 'Jane Smith', '987-654-3210', 1),
	(3, '789 Oak St', '1992-07-10', 'mike@example.com', 'Mike Johnson', '555-123-4567', 1),
	(4, '123 Maple St', '1988-05-20', 'susan@example.com', 'Susan Brown', '222-333-4444', 1),
	(5, '456 Birch St', '1987-12-30', 'david@example.com', 'David Lee', '999-888-7777', 1),
	(6, '789 Pine St', '1995-03-05', 'linda@example.com', 'Linda Wilson', '111-222-3333', 1),
	(7, '123 Redwood St', '1998-09-15', 'robert@example.com', 'Robert Taylor', '444-555-6666', 1),
	(8, '456 Sequoia St', '1994-06-25', 'sarah@example.com', 'Sarah White', '777-888-9999', 1),
	(9, '789 Cedar St', '1997-02-10', 'james@example.com', 'James Miller', '666-555-4444', 1),
	(10, '123 Fir St', '1996-11-20', 'mary@example.com', 'Mary Davis', '333-222-1111', 1);

-- Dumping structure for table lab02.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_date` datetime(6) NOT NULL,
  `cust_id` bigint(20) DEFAULT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FK_orders_employee_id` (`employee_id`),
  KEY `FK_orders_cust_id` (`cust_id`),
  CONSTRAINT `FK_orders_cust_id` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`),
  CONSTRAINT `FK_orders_employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.orders: ~30 rows (approximately)
INSERT INTO `orders` (`order_id`, `order_date`, `cust_id`, `employee_id`) VALUES
	(1, '2023-11-05 02:21:53.000000', 8, 7),
	(2, '2023-11-04 02:21:53.000000', 3, 1),
	(3, '2023-11-03 02:21:53.000000', 7, 10),
	(4, '2023-11-02 02:21:53.000000', 10, 9),
	(5, '2023-11-01 02:21:53.000000', 6, 10),
	(6, '2023-10-31 02:21:53.000000', 1, 6),
	(7, '2023-10-30 02:21:53.000000', 6, 2),
	(8, '2023-10-29 02:21:53.000000', 10, 5),
	(9, '2023-10-28 02:21:53.000000', 5, 8),
	(10, '2023-10-27 02:21:53.000000', 6, 3),
	(11, '2023-11-05 02:21:55.000000', 9, 4),
	(12, '2023-11-04 02:21:55.000000', 4, 5),
	(13, '2023-11-03 02:21:55.000000', 3, 9),
	(14, '2023-11-02 02:21:55.000000', 5, 8),
	(15, '2023-11-01 02:21:55.000000', 3, 3),
	(16, '2023-10-31 02:21:55.000000', 5, 5),
	(17, '2023-10-30 02:21:55.000000', 10, 5),
	(18, '2023-10-29 02:21:55.000000', 5, 1),
	(19, '2023-10-28 02:21:55.000000', 8, 5),
	(20, '2023-10-27 02:21:55.000000', 1, 8),
	(21, '2023-11-05 03:00:29.000000', 9, 2),
	(22, '2023-11-04 03:00:29.000000', 9, 10),
	(23, '2023-11-03 03:00:29.000000', 1, 4),
	(24, '2023-11-02 03:00:29.000000', 7, 5),
	(25, '2023-11-01 03:00:29.000000', 2, 3),
	(26, '2023-10-31 03:00:29.000000', 9, 6),
	(27, '2023-10-30 03:00:29.000000', 4, 2),
	(28, '2023-10-29 03:00:29.000000', 7, 7),
	(29, '2023-10-28 03:00:29.000000', 6, 9),
	(30, '2023-10-27 03:00:29.000000', 5, 7);

-- Dumping structure for table lab02.order_detail
CREATE TABLE IF NOT EXISTS `order_detail` (
  `note` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` double NOT NULL,
  `order_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`order_id`,`product_id`),
  KEY `FK_order_detail_product_id` (`product_id`),
  CONSTRAINT `FK_order_detail_order_id` FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `FK_order_detail_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.order_detail: ~20 rows (approximately)
INSERT INTO `order_detail` (`note`, `price`, `quantity`, `order_id`, `product_id`) VALUES
	('Gia  sp gan naht', 25.01, 1, 2, 1),
	('Gia  sp gan naht', 25.01, 1, 3, 1),
	('Gia  sp gan naht', 18.61, 1, 3, 2),
	('Gia  sp gan naht', 48, 1, 3, 3),
	('Gia  sp gan naht', 58.28, 1, 3, 4),
	('Gia  sp gan naht', 63.34, 1, 3, 5),
	('Gia  sp gan naht', 16.72, 1, 3, 6),
	('Gia  sp gan naht', 98.48, 1, 3, 7),
	('Gia  sp gan naht', 26.46, 1, 3, 8),
	('Gia  sp gan naht', 34.9, 1, 3, 9),
	('Gia  sp gan naht', 68.58, 1, 3, 10),
	('Gia sp gan nhat', 18.61, 1, 4, 1),
	('Gia  sp gan naht', 48, 1, 4, 4),
	('Gia  sp gan naht', 16.72, 7, 5, 6),
	('Gia  sp gan naht', 98.48, 8, 6, 7),
	('Gia  sp gan naht', 26.46, 9, 7, 8),
	('Gia  sp gan naht', 34.9, 10, 7, 9),
	('Gia  sp gan naht', 63.34, 6, 8, 5),
	('Gia  sp gan naht', 68.58, 1, 8, 10),
	('Gia  sp gan naht', 58.28, 5, 9, 4);

-- Dumping structure for table lab02.product
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` text DEFAULT NULL,
  `manufacturer_name` varchar(100) NOT NULL,
  `name` varchar(150) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `unit` varchar(25) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.product: ~10 rows (approximately)
INSERT INTO `product` (`product_id`, `description`, `manufacturer_name`, `name`, `status`, `unit`) VALUES
	(1, 'HTC111 manufacturer_name', 'HTC111', 'iPhone SE (2nd gen)', -1, 'piece'),
	(2, 'HTC manufacturer_name', 'HTC', 'iPhone 13', 1, 'piece'),
	(3, 'Panasonic manufacturer_name', 'Panasonic', 'iPhone 12 Pro Max', 1, 'piece'),
	(4, 'BlackBerry manufacturer_name', 'BlackBerry', 'Google Pixel XL', 1, 'piece'),
	(5, 'OnePlus manufacturer_name', 'OnePlus', 'Xiaomi Mi 8 SE', 1, 'piece'),
	(6, 'Panasonic manufacturer_name', 'Panasonic', 'iPhone 11 Pro', 1, 'piece'),
	(7, 'Panasonic manufacturer_name', 'Oppo', 'Huawei P20', 1, 'piece'),
	(8, 'Samsung manufacturer_name', 'Samsung', 'Xiaomi Mi 8 Lite', 1, 'piece'),
	(9, 'Plum manufacturer_name', 'Plum', 'OnePlus 3T', 1, 'piece'),
	(10, 'Plum manufacturer_name', 'Plum', 'Samsung Galaxy S3 Mini', 1, 'piece');

-- Dumping structure for table lab02.product_image
CREATE TABLE IF NOT EXISTS `product_image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alternative` varchar(250) DEFAULT NULL,
  `path` varchar(250) NOT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FK_product_image_product_id` (`product_id`),
  CONSTRAINT `FK_product_image_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.product_image: ~10 rows (approximately)
INSERT INTO `product_image` (`image_id`, `alternative`, `path`, `product_id`) VALUES
	(1, 'Alternative 1', 'https://i.pinimg.com/236x/ef/99/65/ef996542a1bb55c3d4f97928fa198276.jpg', 1),
	(2, 'Alternative 2', 'https://i.pinimg.com/236x/df/a4/46/dfa446dc4a4b67225d47a201cc32cb42.jpg', 2),
	(3, 'Alternative 3', 'https://i.pinimg.com/236x/47/3e/f6/473ef6260330eade6eaca2cae28e34e4.jpg', 3),
	(4, 'Alternative 4', 'https://i.pinimg.com/236x/d9/ba/d8/d9bad87a011371ce280314b88ea2ce82.jpg', 4),
	(5, 'Alternative 5', 'https://i.pinimg.com/236x/4d/4e/9b/4d4e9b52647db3d38f45b68fe9931b3a.jpg', 5),
	(6, 'Alternative 6', 'https://i.pinimg.com/236x/0c/59/03/0c59037d2eb0d5054ebfe3aa8f56d6cd.jpg', 6),
	(7, 'Alternative 7', 'https://i.pinimg.com/236x/87/66/bd/8766bdcd371e02df3b108ec7373314ab.jpg', 7),
	(8, 'Alternative 8', 'https://i.pinimg.com/236x/78/e9/d0/78e9d0e17d658cdcb3c13c64ac01ee42.jpg	', 8),
	(9, 'Alternative 9', 'https://i.pinimg.com/236x/5c/2a/ee/5c2aeebfb2aa0d9a001689c3fef88cbf.jpg', 9),
	(10, 'Alternative 10', 'https://i.pinimg.com/236x/fe/7a/f5/fe7af5d2a7be8587c27d5db1d91494a6.jpg', 10);

-- Dumping structure for table lab02.product_price
CREATE TABLE IF NOT EXISTS `product_price` (
  `price_date_time` datetime(6) NOT NULL,
  `note` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`price_date_time`,`product_id`),
  KEY `FK_product_price_product_id` (`product_id`),
  CONSTRAINT `FK_product_price_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table lab02.product_price: ~63 rows (approximately)
INSERT INTO `product_price` (`price_date_time`, `note`, `price`, `product_id`) VALUES
	('2023-11-05 09:00:00.000000', 'gia ban dau', 10.99, 1),
	('2023-11-05 09:00:00.000000', 'Gia sp lan 2 9', 18.25, 10),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 2 6', 76.39, 1),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 2 6', 34.86, 3),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 1 8', 79.63, 5),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 2 8', 20.46, 6),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 1 8', 0.05, 7),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 2 6', 95.72, 8),
	('2023-11-06 09:00:00.000000', 'Gia sp lan 2 6', 36.53, 10),
	('2023-11-06 10:30:00.000000', 'gia thay doi lan 1', 12.99, 1),
	('2023-11-07 09:00:00.000000', 'Gia sp lan 1 4', 33.68, 1),
	('2023-11-07 09:00:00.000000', 'Gia sp lan 1 5', 57.85, 2),
	('2023-11-07 09:00:00.000000', 'Gia sp lan 2 3', 50.55, 3),
	('2023-11-07 09:00:00.000000', 'Gia sp lan 2 0', 7.26, 9),
	('2023-11-07 14:45:00.000000', 'gia thay doi lan 2', 9.99, 1),
	('2023-11-08 09:00:00.000000', 'Gia sp lan 2 5', 57.17, 10),
	('2023-11-09 09:00:00.000000', 'Gia sp lan 1 2', 34.91, 1),
	('2023-11-09 09:00:00.000000', 'Gia sp lan 2 2', 54.64, 6),
	('2023-11-10 09:00:00.000000', 'Gia sp lan 1 5', 82.21, 2),
	('2023-11-10 09:00:00.000000', 'Gia sp lan 2 7', 46.43, 8),
	('2023-11-11 09:00:00.000000', 'Gia sp lan 2 5', 25.23, 9),
	('2023-11-12 09:00:00.000000', 'Gia sp lan 1 2', 80.43, 2),
	('2023-11-12 09:00:00.000000', 'Gia sp lan 2 3', 42, 3),
	('2023-11-12 09:00:00.000000', 'Gia sp lan 2 8', 13.03, 9),
	('2023-11-13 09:00:00.000000', 'Gia sp lan 2 0', 38.2, 4),
	('2023-11-13 09:00:00.000000', 'Gia sp lan 1 4', 4.9, 7),
	('2023-11-13 09:00:00.000000', 'Gia sp lan 2 6', 8.44, 8),
	('2023-11-15 09:00:00.000000', 'Gia sp lan 2 5', 31.94, 1),
	('2023-11-15 09:00:00.000000', 'Gia sp lan 2 1', 2.5300000000000002, 3),
	('2023-11-15 09:00:00.000000', 'Gia sp lan 2 1', 84.69, 10),
	('2023-11-16 09:00:00.000000', 'Gia sp lan 2 6', 96.09, 3),
	('2023-11-16 09:00:00.000000', 'Gia sp lan 2 5', 14.92, 4),
	('2023-11-16 09:00:00.000000', 'Gia sp lan 2 7', 57.68, 9),
	('2023-11-17 09:00:00.000000', 'Gia sp lan 2 4', 13.99, 1),
	('2023-11-17 09:00:00.000000', 'Gia sp lan 2 6', 38.46, 3),
	('2023-11-17 09:00:00.000000', 'Gia sp lan 2 1', 8.82, 4),
	('2023-11-17 09:00:00.000000', 'Gia sp lan 1 2', 8.97, 5),
	('2023-11-18 09:00:00.000000', 'Gia sp lan 1 3', 14.87, 5),
	('2023-11-18 09:00:00.000000', 'Gia sp lan 2 0', 88.22, 10),
	('2023-11-19 09:00:00.000000', 'Gia sp lan 1 2', 93.12, 2),
	('2023-11-19 09:00:00.000000', 'Gia sp lan 2 1', 91.47, 6),
	('2023-11-20 09:00:00.000000', 'Gia sp lan 1 3', 78.3, 5),
	('2023-11-20 09:00:00.000000', 'Gia sp lan 2 6', 83.51, 6),
	('2023-11-23 09:00:00.000000', 'Gia sp lan 1 0', 38.53, 7),
	('2023-11-23 09:00:00.000000', 'Gia sp lan 2 0', 97.7, 10),
	('2023-11-24 09:00:00.000000', 'Gia sp lan 1 3', 26.47, 1),
	('2023-11-24 09:00:00.000000', 'Gia sp lan 1 2', 63.34, 5),
	('2023-11-24 09:00:00.000000', 'Gia sp lan 1 7', 74.98, 7),
	('2023-11-24 09:00:00.000000', 'Gia sp lan 2 7', 95.4, 8),
	('2023-11-25 09:00:00.000000', 'Gia sp lan 2 9', 0.83, 4),
	('2023-11-28 09:00:00.000000', 'Gia sp lan 2 9', 86.54, 1),
	('2023-11-29 09:00:00.000000', 'Gia sp lan 2 8', 46.17, 1),
	('2023-11-29 09:00:00.000000', 'Gia sp lan 2 3', 16.72, 6),
	('2023-11-30 09:00:00.000000', 'Gia sp lan 2 7', 25.01, 1),
	('2023-11-30 09:00:00.000000', 'Gia sp lan 1 9', 89.16, 2),
	('2023-11-30 09:00:00.000000', 'Gia sp lan 2 8', 48, 3),
	('2023-11-30 09:00:00.000000', 'Gia sp lan 2 3', 58.28, 4),
	('2023-11-30 09:00:00.000000', 'Gia sp lan 2 2', 34.9, 9),
	('2023-11-30 09:00:00.000000', 'Gia sp lan 2 8', 68.58, 10),
	('2023-12-02 09:00:00.000000', 'Gia sp lan 1 2', 80.4, 2),
	('2023-12-02 09:00:00.000000', 'Gia sp lan 1 1', 98.48, 7),
	('2023-12-03 09:00:00.000000', 'Gia sp lan 2 4', 26.46, 8),
	('2023-12-04 09:00:00.000000', 'Gia sp lan 1 9', 18.61, 2);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
