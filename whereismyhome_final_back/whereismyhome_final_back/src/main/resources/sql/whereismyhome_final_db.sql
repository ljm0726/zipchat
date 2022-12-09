-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema whereismyhome_final_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema whereismyhome_final_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `whereismyhome_final_db` DEFAULT CHARACTER SET utf8mb3 ;
USE `whereismyhome_final_db` ;

-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`user` (
  `id` VARCHAR(20) NOT NULL,
  `pass` VARCHAR(100) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(200) NOT NULL,
  `address` VARCHAR(400) NULL DEFAULT NULL,
  `phone` VARCHAR(20) NULL DEFAULT NULL,
  `join_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `roll` INT NOT NULL,
  `isDel` INT NOT NULL DEFAULT '0',
  `token` VARCHAR(1000) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`board` (
  `article_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `subject` VARCHAR(100) NULL DEFAULT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT '0',
  `register_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`),
  INDEX `board_to_user_id_fk` (`user_id` ASC) VISIBLE,
  CONSTRAINT `board_to_user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `whereismyhome_final_db`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 108
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`bus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`bus` (
  `bus_id` VARCHAR(30) NOT NULL,
  `bus_mobile_id` VARCHAR(10) NULL DEFAULT NULL,
  `bus_name` VARCHAR(100) NOT NULL,
  `gps_lati` VARCHAR(20) NULL DEFAULT NULL,
  `gps_long` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`bus_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`dongcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`dongcode` (
  `dongCode` VARCHAR(10) NOT NULL,
  `sidoName` VARCHAR(30) NULL DEFAULT NULL,
  `gugunName` VARCHAR(30) NULL DEFAULT NULL,
  `dongName` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`houseinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`houseinfo` (
  `aptCode` BIGINT NOT NULL,
  `buildYear` INT NULL DEFAULT NULL,
  `roadName` VARCHAR(40) NULL DEFAULT NULL,
  `roadNameBonbun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameBubun` VARCHAR(5) NULL DEFAULT NULL,
  `roadNameSeq` VARCHAR(2) NULL DEFAULT NULL,
  `roadNameBasementCode` VARCHAR(1) NULL DEFAULT NULL,
  `roadNameCode` VARCHAR(7) NULL DEFAULT NULL,
  `dong` VARCHAR(40) NULL DEFAULT NULL,
  `bonbun` VARCHAR(4) NULL DEFAULT NULL,
  `bubun` VARCHAR(4) NULL DEFAULT NULL,
  `sigunguCode` VARCHAR(5) NULL DEFAULT NULL,
  `eubmyundongCode` VARCHAR(5) NULL DEFAULT NULL,
  `dongCode` VARCHAR(10) NULL DEFAULT NULL,
  `landCode` VARCHAR(1) NULL DEFAULT NULL,
  `apartmentName` VARCHAR(40) NULL DEFAULT NULL,
  `jibun` VARCHAR(10) NULL DEFAULT NULL,
  `lng` VARCHAR(30) NULL DEFAULT NULL,
  `lat` VARCHAR(30) NULL DEFAULT NULL,
  PRIMARY KEY (`aptCode`),
  UNIQUE INDEX `UNIQUE` (`buildYear` ASC, `apartmentName` ASC, `jibun` ASC, `sigunguCode` ASC, `eubmyundongCode` ASC) INVISIBLE,
  INDEX `houseinfo_dongCode_dongcode_dongCode_fk_idx` (`dongCode` ASC) INVISIBLE,
  CONSTRAINT `houseinfo_dongCode_dongcode_dongCode_fk`
    FOREIGN KEY (`dongCode`)
    REFERENCES `whereismyhome_final_db`.`dongcode` (`dongCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`favorite` (
  `favorite_no` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(20) NOT NULL,
  `houseinfo_aptCode` BIGINT NOT NULL,
  `dongCode` VARCHAR(10) NULL DEFAULT NULL,
  PRIMARY KEY (`favorite_no`),
  INDEX `fk_favorite_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_favorite_houseinfo1_idx` (`houseinfo_aptCode` ASC) VISIBLE,
  CONSTRAINT `fk_favorite_houseinfo1`
    FOREIGN KEY (`houseinfo_aptCode`)
    REFERENCES `whereismyhome_final_db`.`houseinfo` (`aptCode`),
  CONSTRAINT `fk_favorite_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `whereismyhome_final_db`.`user` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`housedeal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`housedeal` (
  `no` BIGINT NOT NULL,
  `dealAmount` VARCHAR(20) NULL DEFAULT NULL,
  `dealYear` INT NULL DEFAULT NULL,
  `dealMonth` INT NULL DEFAULT NULL,
  `dealDay` INT NULL DEFAULT NULL,
  `area` VARCHAR(20) NULL DEFAULT NULL,
  `floor` VARCHAR(4) NULL DEFAULT NULL,
  `cancelDealType` VARCHAR(1) NULL DEFAULT NULL,
  `aptCode` BIGINT NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `housedeal_aptCode_houseinfo_aptCode_fk_idx` (`aptCode` ASC) VISIBLE,
  CONSTRAINT `housedeal_aptCode_houseinfo_aptCode_fk`
    FOREIGN KEY (`aptCode`)
    REFERENCES `whereismyhome_final_db`.`houseinfo` (`aptCode`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `whereismyhome_final_db`.`school`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `whereismyhome_final_db`.`school` (
  `school_id` VARCHAR(20) NOT NULL,
  `school_name` VARCHAR(45) NOT NULL,
  `school_level` VARCHAR(45) NOT NULL,
  `address_jibun` VARCHAR(200) NULL DEFAULT NULL,
  `address_doro` VARCHAR(200) NULL DEFAULT NULL,
  `gps_lati` VARCHAR(45) NULL DEFAULT NULL,
  `gps_long` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`school_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
