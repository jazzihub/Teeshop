- MySQL Script generated by MySQL Workbench
-- 10/29/15 16:41:19
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema teeDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `teeDB` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `teeDB` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `teeDB` ;

-- -----------------------------------------------------
-- Table `mydb`.`kunde`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teeDB`.`kunde` ;

CREATE TABLE IF NOT EXISTS `teeDB`.`kunde` (
  `id` INT NOT NULL auto_increment,
  `nachname` VARCHAR(30) NOT NULL,
  `vorname` VARCHAR(30) NOT NULL,
  `strasse` VARCHAR(45) NOT NULL,
  hausnr VARCHAR(10) NOT NULL,
  `plz` CHAR(5) NULL,
  `ort` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `teeDB`.`artikel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teeDB`.`artikel` ;

CREATE TABLE IF NOT EXISTS `teeDB`.`artikel` (
  `id` INT NOT NULL auto_increment,
  `bezeichnung` VARCHAR(45) NOT NULL,
  `nettopreis` DECIMAL(6,2) NOT NULL,
  `mwst` decimal(4,2) NOT NULL,
  `lagerstand` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`bestellen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `teeDB`.`bestellen` ;

CREATE TABLE IF NOT EXISTS `teeDB`.`bestellen` (
  `artikel_id` INT NOT NULL,
  `kunde_id` INT NOT NULL,
  `anzahl` INT NOT NULL,
  `datum` DATE NOT NULL,
  PRIMARY KEY (`artikel_id`, `kunde_id`),
  INDEX `fk_a_artikel_has_k_kunde_copy1_k_kunde_copy11_idx` (`kunde_id` ASC),
  INDEX `fk_a_artikel_has_k_kunde_copy1_a_artikel1_idx` (`artikel_id` ASC),
  CONSTRAINT `fk_a_artikel_has_k_kunde_copy1_a_artikel1`
    FOREIGN KEY (`artikel_id`)
    REFERENCES `teeDB`.`artikel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_a_artikel_has_k_kunde_copy1_k_kunde_copy11`
    FOREIGN KEY (`kunde_id`)
    REFERENCES `teeDB`.`kunde` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;