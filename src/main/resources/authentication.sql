-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema authentication_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema authentication_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `authentication_schema` DEFAULT CHARACTER SET utf8 ;
USE `authentication_schema` ;

-- -----------------------------------------------------
-- Table `authentication_schema`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `authentication_schema`.`users` (
  `user_id` INT NOT NULL,
  `user_username` VARCHAR(45) NOT NULL,
  `user_email` VARCHAR(45) NOT NULL,
  `user_password` VARCHAR(45) NOT NULL,
  `user_phonenumber` DOUBLE NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `user_email_UNIQUE` (`user_email` ASC) VISIBLE,
  UNIQUE INDEX `user_username_UNIQUE` (`user_username` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `authentication_schema`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `authentication_schema`.`roles` (
  `roles_id` INT NOT NULL,
  `roles_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roles_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `authentication_schema`.`users_has_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `authentication_schema`.`users_has_roles` (
  `users_user_id` INT NOT NULL,
  `roles_roles_id` INT NOT NULL,
  PRIMARY KEY (`users_user_id`, `roles_roles_id`),
  INDEX `fk_users_has_roles_roles1_idx` (`roles_roles_id` ASC) VISIBLE,
  INDEX `fk_users_has_roles_users_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_users_has_roles_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `authentication_schema`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_roles_roles1`
    FOREIGN KEY (`roles_roles_id`)
    REFERENCES `authentication_schema`.`roles` (`roles_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
