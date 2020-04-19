-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema health_care
-- -----------------------------------------------------
-- 
-- 
-- 
DROP SCHEMA IF EXISTS `health_care` ;

-- -----------------------------------------------------
-- Schema health_care
--
-- 
-- 
-- 
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `health_care` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `health_care` ;

-- -----------------------------------------------------
-- Table `health_care`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`user` ;

CREATE TABLE IF NOT EXISTS `health_care`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(10) NOT NULL,
  `user_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`hospital`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`hospital` ;

CREATE TABLE IF NOT EXISTS `health_care`.`hospital` (
  `hosp_id` INT NOT NULL AUTO_INCREMENT,
  `hosp_name` VARCHAR(45) NOT NULL,
  `hosp_address` VARCHAR(100) NOT NULL,
  `hosp_email` VARCHAR(45) NULL,
  `hosp_phone` VARCHAR(12) NOT NULL,
  `hosp_reg_date` DATE NOT NULL,
  `hosp_charge` FLOAT NOT NULL,
  PRIMARY KEY (`hosp_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`doctor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`doctor`;

CREATE TABLE IF NOT EXISTS `health_care`.`doctor` (
  `doc_id` INT NOT NULL AUTO_INCREMENT,
  `doc_nic` VARCHAR(45) NOT NULL,
  `doc_fname` VARCHAR(45) NOT NULL,
  `doc_lname` VARCHAR(45) NULL,
  `doc_email` VARCHAR(45) NULL,
  `doc_gender` VARCHAR(45) NOT NULL,
  `liscen_no_` VARCHAR(45) NOT NULL,
  `specialization` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NOT NULL,
  `doc_charge` FLOAT NOT NULL,
  `user_user_id` INT NOT NULL,
   PRIMARY KEY (`doc_id`, `user_user_id`),
  CONSTRAINT `fk_doctor_user1`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `health_care`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`patient`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`patient` ;

CREATE TABLE IF NOT EXISTS `health_care`.`patient` (
  `patient_id` INT NOT NULL AUTO_INCREMENT,
  `p_nic` VARCHAR(45) NOT NULL,
  `p_fname` VARCHAR(45) NOT NULL,
  `p_lname` VARCHAR(45) NOT NULL,
  `p_dob` DATE NOT NULL,
  `p_address` VARCHAR(100) NOT NULL,
  `p_phone` VARCHAR(45) NOT NULL,
  `p_email` VARCHAR(45) NULL,
  `p_gender` VARCHAR(45) NOT NULL,
  `user_user_id` INT NOT NULL,
  PRIMARY KEY (`patient_id`, `user_user_id`),
  INDEX `fk_patient_user_idx` (`user_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_patient_user`
    FOREIGN KEY (`user_user_id`)
    REFERENCES `health_care`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`tax`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`tax` ;

CREATE TABLE IF NOT EXISTS `health_care`.`tax` (
  `tax_id` INT NOT NULL AUTO_INCREMENT,
  `tax_amount` FLOAT NOT NULL,
  `valid_from` DATE NOT NULL,
  `valid_to` DATE NOT NULL,
  PRIMARY KEY (`tax_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`appoinment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`appoinment` ;

CREATE TABLE IF NOT EXISTS `health_care`.`appoinment` (
  `appoinment_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `time` VARCHAR(10) NOT NULL,
  `patient_patient_id` INT NOT NULL,
  `doctor_doc_id` INT NOT NULL,
  `hospital_hosp_id` INT NOT NULL,
  PRIMARY KEY (`appoinment_id`, `patient_patient_id`, `doctor_doc_id`, `hospital_hosp_id`),
  INDEX `fk_appoinment_patient1_idx` (`patient_patient_id` ASC) VISIBLE,
  INDEX `fk_appoinment_doctor1_idx` (`doctor_doc_id` ASC) VISIBLE,
  INDEX `fk_appoinment_hospital1_idx` (`hospital_hosp_id` ASC) VISIBLE,
  CONSTRAINT `fk_appoinment_patient1`
    FOREIGN KEY (`patient_patient_id`)
    REFERENCES `health_care`.`patient` (`patient_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appoinment_doctor1`
    FOREIGN KEY (`doctor_doc_id`)
    REFERENCES `health_care`.`doctor` (`doc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_appoinment_hospital1`
    FOREIGN KEY (`hospital_hosp_id`)
    REFERENCES `health_care`.`hospital` (`hosp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`payment` ;

CREATE TABLE IF NOT EXISTS `health_care`.`payment` (
  `payment_id` INT NOT NULL AUTO_INCREMENT,
  `card_type` VARCHAR(45) NOT NULL,
  `card_number` VARCHAR(45) NOT NULL,
  `name_on_card` VARCHAR(45) NOT NULL,
  `cvc` VARCHAR(45) NOT NULL,
  `expire_date` DATE NOT NULL,
  `status` VARCHAR(45) NOT NULL,
  `sub_amount` DOUBLE NOT NULL,
  `date` DATE NOT NULL,
  `tax_tax_id` INT NOT NULL,
  `appoinment_appoinment_id` INT NOT NULL,
  PRIMARY KEY (`payment_id`, `tax_tax_id`, `appoinment_appoinment_id`),
  INDEX `fk_payment_tax1_idx` (`tax_tax_id` ASC) VISIBLE,
  INDEX `fk_payment_appoinment1_idx` (`appoinment_appoinment_id` ASC) VISIBLE,
  CONSTRAINT `fk_payment_tax1`
    FOREIGN KEY (`tax_tax_id`)
    REFERENCES `health_care`.`tax` (`tax_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_appoinment1`
    FOREIGN KEY (`appoinment_appoinment_id`)
    REFERENCES `health_care`.`appoinment` (`appoinment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `health_care`.`doctor_has_hospital`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `health_care`.`doctor_has_hospital` ;


CREATE TABLE IF NOT EXISTS `health_care`.`doctor_has_hospital` (
  `doctor_doc_id` INT NOT NULL,
  `hospital_hosp_id` INT NOT NULL,
  `date` Varchar(20) NOT NULL,
  `time` time NOT NULL,	

  PRIMARY KEY (`doctor_doc_id`, `hospital_hosp_id`),
  INDEX `fk_doctor_has_hospital_hospital1_idx` (`hospital_hosp_id` ASC) VISIBLE,
  INDEX `fk_doctor_has_hospital_doctor1_idx` (`doctor_doc_id` ASC) VISIBLE,
  CONSTRAINT `fk_doctor_has_hospital_doctor1`
    FOREIGN KEY (`doctor_doc_id`)
    REFERENCES `health_care`.`doctor` (`doc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_doctor_has_hospital_hospital1`
    FOREIGN KEY (`hospital_hosp_id`)
    REFERENCES `health_care`.`hospital` (`hosp_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
