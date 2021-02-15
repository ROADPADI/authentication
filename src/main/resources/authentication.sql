-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema authentication_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema authentication_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `authentication_schema` DEFAULT CHARACTER SET utf8 ;
USE `authentication_schema` ;

-- -----------------------------------------------------
-- Table `authentication_schema`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `authentication_schema`.`roles` (
  `roles_id` INT(11) NOT NULL AUTO_INCREMENT,
  `roles_name` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`roles_id`),
  UNIQUE INDEX `roles_id_UNIQUE` (`roles_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `authentication_schema`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `authentication_schema`.`users` (
  `users_id` INT(11) NOT NULL AUTO_INCREMENT,
  `users_username` VARCHAR(200) NOT NULL,
  `users_email` VARCHAR(200) NOT NULL,
  `users_password` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`users_id`),
  UNIQUE INDEX `users_id_UNIQUE` (`users_id` ASC) VISIBLE,
  UNIQUE INDEX `users_email_UNIQUE` (`users_email` ASC) VISIBLE,
  UNIQUE INDEX `users_username_UNIQUE` (`users_username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `authentication_schema`.`users_has_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `authentication_schema`.`users_has_roles` (
  `users_users_id` INT(11) NOT NULL,
  `roles_roles_id` INT(11) NOT NULL,
  PRIMARY KEY (`users_users_id`, `roles_roles_id`),
  INDEX `fk_users_has_roles_roles1_idx` (`roles_roles_id` ASC) VISIBLE,
  INDEX `fk_users_has_roles_users_idx` (`users_users_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_roles_users`
    FOREIGN KEY (`users_users_id`)
    REFERENCES `authentication_schema`.`users` (`users_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_roles_roles1`
    FOREIGN KEY (`roles_roles_id`)
    REFERENCES `authentication_schema`.`roles` (`roles_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

