-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.2.43-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- orangepoolvilla 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `orangepoolvilla` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `orangepoolvilla`;

-- 테이블 orangepoolvilla.address 구조 내보내기
CREATE TABLE IF NOT EXISTS `address` (
  `address_no` int(11) NOT NULL AUTO_INCREMENT,
  `zip_code` char(5) NOT NULL DEFAULT '' COMMENT '우편번호',
  `province` varchar(30) NOT NULL DEFAULT '' COMMENT '시도',
  `city` varchar(30) NOT NULL DEFAULT '' COMMENT '시군구',
  `town` varchar(30) NOT NULL DEFAULT '' COMMENT '읍면',
  `street` varchar(50) NOT NULL DEFAULT '' COMMENT '도로명',
  `building1` varchar(5) NOT NULL DEFAULT '' COMMENT '건물번호본번',
  PRIMARY KEY (`address_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6337163 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.cooking_tool 구조 내보내기
CREATE TABLE IF NOT EXISTS `cooking_tool` (
  `cooking_tool_no` int(11) NOT NULL AUTO_INCREMENT,
  `cooking_tool_name` varchar(100) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`cooking_tool_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.customer 구조 내보내기
CREATE TABLE IF NOT EXISTS `customer` (
  `customer_id` varchar(100) NOT NULL,
  `customer_pw` varchar(255) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `birth_date` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `level` int(2) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.facility 구조 내보내기
CREATE TABLE IF NOT EXISTS `facility` (
  `facility_no` int(11) NOT NULL AUTO_INCREMENT,
  `facility_name` varchar(100) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`facility_no`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.host 구조 내보내기
CREATE TABLE IF NOT EXISTS `host` (
  `host_id` varchar(100) NOT NULL,
  `host_pw` varchar(255) NOT NULL,
  `level` int(2) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`host_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.ott 구조 내보내기
CREATE TABLE IF NOT EXISTS `ott` (
  `ott_no` int(11) NOT NULL AUTO_INCREMENT,
  `ott_name` varchar(100) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`ott_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla` (
  `pv_no` int(11) NOT NULL AUTO_INCREMENT,
  `host_id` varchar(100) NOT NULL,
  `location_no` int(11) NOT NULL,
  `address_no` int(11) NOT NULL,
  `pv_detailaddr` varchar(255) NOT NULL,
  `pv_name` varchar(100) NOT NULL,
  `price` int(11) NOT NULL,
  `pv_size` double NOT NULL,
  `pv_floor` int(11) NOT NULL,
  `pv_people` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`pv_no`,`host_id`),
  KEY `FK_host_TO_poolvilla_1` (`host_id`),
  KEY `FK_address_TO_poolvilla_1` (`address_no`) USING BTREE,
  KEY `FK_poolvilla_poolvilla_location` (`location_no`),
  CONSTRAINT `FK_address_TO_poolvilla_1` FOREIGN KEY (`address_no`) REFERENCES `address` (`address_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_host_TO_poolvilla_1` FOREIGN KEY (`host_id`) REFERENCES `host` (`host_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_poolvilla_poolvilla_location` FOREIGN KEY (`location_no`) REFERENCES `poolvilla_location` (`location_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_cooking_tool 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_cooking_tool` (
  `pv_no` int(11) NOT NULL,
  `cooking_tool_no` int(11) NOT NULL,
  `cooking_tool_cnt` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`pv_no`,`cooking_tool_no`) USING BTREE,
  KEY `FK_cooking_facilities_list_TO_poolvilla_cooking_facilities_1` (`cooking_tool_no`) USING BTREE,
  CONSTRAINT `FK_cooking_facilities_list_TO_poolvilla_cooking_facilities_1` FOREIGN KEY (`cooking_tool_no`) REFERENCES `cooking_tool` (`cooking_tool_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_poolvilla_TO_poolvilla_cooking_facilities_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_facility 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_facility` (
  `pv_no` int(11) NOT NULL,
  `facility_no` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  `facility_cnt` int(11) NOT NULL,
  PRIMARY KEY (`pv_no`,`facility_no`),
  KEY `FK_facility_list_TO_poolvilla_facility_1` (`facility_no`),
  CONSTRAINT `FK_facility_list_TO_poolvilla_facility_1` FOREIGN KEY (`facility_no`) REFERENCES `facility` (`facility_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_poolvilla_TO_poolvilla_facility_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_location 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_location` (
  `location_no` int(11) NOT NULL,
  `location_name` varchar(100) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`location_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_ott 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_ott` (
  `pv_no` int(11) NOT NULL,
  `ott_no` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`pv_no`,`ott_no`),
  KEY `FK_ott_TO_poolvilla_ott_1` (`ott_no`),
  CONSTRAINT `FK_ott_TO_poolvilla_ott_1` FOREIGN KEY (`ott_no`) REFERENCES `ott` (`ott_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_poolvilla_TO_poolvilla_ott_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_photo 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_photo` (
  `photo_no` int(11) NOT NULL AUTO_INCREMENT,
  `pv_no` int(11) NOT NULL,
  `photo_name` varchar(100) NOT NULL,
  `photo_original_name` varchar(100) NOT NULL,
  `photo_type` varchar(100) NOT NULL,
  `photo_size` double NOT NULL,
  `photo_area` varchar(100) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`photo_no`,`pv_no`),
  KEY `FK_poolvilla_TO_poolvila_photo_1` (`pv_no`),
  CONSTRAINT `FK_poolvilla_TO_poolvila_photo_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_pool 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_pool` (
  `pool_no` int(11) NOT NULL AUTO_INCREMENT,
  `pv_no` int(11) NOT NULL,
  `pool_width` double NOT NULL,
  `pool_name` varchar(100) NOT NULL DEFAULT '0',
  `pool_length` double NOT NULL,
  `depth` double NOT NULL,
  `hot_water` enum('Y','N') NOT NULL,
  `indoor_outdoor` enum('실내','야외') NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`pool_no`,`pv_no`),
  KEY `FK_poolvilla_TO_pool_1` (`pv_no`),
  CONSTRAINT `FK_poolvilla_TO_pool_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_room 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_room` (
  `room_no` int(11) NOT NULL AUTO_INCREMENT,
  `pv_no` int(11) NOT NULL,
  `room_type` enum('침대방','온돌방','놀이방') NOT NULL,
  `room_name` varchar(100) NOT NULL,
  `room_info` text DEFAULT NULL,
  `room_size` double NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`room_no`,`pv_no`),
  KEY `FK_poolvilla_TO_room_info_1` (`pv_no`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.poolvilla_supplies 구조 내보내기
CREATE TABLE IF NOT EXISTS `poolvilla_supplies` (
  `pv_no` int(11) NOT NULL,
  `supplies_no` int(11) NOT NULL,
  `supplies_cnt` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`pv_no`,`supplies_no`),
  KEY `FK_supplies_list_TO_poolvilla_supplies_1` (`supplies_no`),
  CONSTRAINT `FK_poolvilla_TO_poolvilla_supplies_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_supplies_list_TO_poolvilla_supplies_1` FOREIGN KEY (`supplies_no`) REFERENCES `supplies` (`supplies_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.reservation 구조 내보내기
CREATE TABLE IF NOT EXISTS `reservation` (
  `reservation_no` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(100) NOT NULL,
  `pv_no` int(11) NOT NULL,
  `reservation_begin_date` date NOT NULL,
  `reservation_last_date` date NOT NULL,
  `reservation_status` enum('결제대기','결제완료','취소대기','취소','이용완료') NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`reservation_no`) USING BTREE,
  KEY `FK_customer_TO_order_1` (`customer_id`),
  KEY `FK_poolvilla_TO_order_1` (`pv_no`),
  CONSTRAINT `FK_customer_TO_order_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_poolvilla_TO_order_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.review 구조 내보내기
CREATE TABLE IF NOT EXISTS `review` (
  `review_no` int(11) NOT NULL AUTO_INCREMENT,
  `cleanliness` int(3) NOT NULL,
  `revisit` enum('Y','N') NOT NULL,
  `satisfaction` int(3) NOT NULL,
  `opinion` text DEFAULT NULL,
  `review_contents` text DEFAULT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  `review_active` enum('Y','N') NOT NULL,
  `reservation_no` int(11) NOT NULL,
  PRIMARY KEY (`review_no`),
  KEY `FK_order_TO_review_1` (`reservation_no`) USING BTREE,
  CONSTRAINT `FK_review_reservation` FOREIGN KEY (`reservation_no`) REFERENCES `reservation` (`reservation_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.room_bed 구조 내보내기
CREATE TABLE IF NOT EXISTS `room_bed` (
  `bed_no` int(11) NOT NULL AUTO_INCREMENT,
  `room_no` int(11) NOT NULL,
  `bed_size` enum('싱글','슈퍼싱글','더블','퀸','킹','라지킹') NOT NULL,
  `bed_cnt` int(11) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`bed_no`,`room_no`),
  KEY `FK_bed_room_info` (`room_no`),
  CONSTRAINT `FK_bed_room_info` FOREIGN KEY (`room_no`) REFERENCES `poolvilla_room` (`room_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.room_photo 구조 내보내기
CREATE TABLE IF NOT EXISTS `room_photo` (
  `photo_no` int(11) NOT NULL AUTO_INCREMENT,
  `room_no` int(11) NOT NULL,
  `photo_name` varchar(100) NOT NULL,
  `photo_original_name` varchar(100) NOT NULL,
  `photo_type` varchar(100) NOT NULL,
  `photo_size` double NOT NULL,
  `photo_area` varchar(100) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`photo_no`,`room_no`),
  KEY `FK_room_photo_room_info` (`room_no`),
  CONSTRAINT `FK_room_photo_room_info` FOREIGN KEY (`room_no`) REFERENCES `poolvilla_room` (`room_no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.supplies 구조 내보내기
CREATE TABLE IF NOT EXISTS `supplies` (
  `supplies_no` int(11) NOT NULL AUTO_INCREMENT,
  `supplies_name` varchar(100) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`supplies_no`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

-- 테이블 orangepoolvilla.wish_list 구조 내보내기
CREATE TABLE IF NOT EXISTS `wish_list` (
  `pv_no` int(11) NOT NULL,
  `customer_id` varchar(100) NOT NULL,
  `update_date` datetime NOT NULL,
  PRIMARY KEY (`pv_no`,`customer_id`),
  KEY `FK_customer_TO_wish_list_1` (`customer_id`),
  CONSTRAINT `FK_customer_TO_wish_list_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `FK_poolvilla_TO_wish_list_1` FOREIGN KEY (`pv_no`) REFERENCES `poolvilla` (`pv_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
