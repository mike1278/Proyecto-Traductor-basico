-- MySQL Workbench Synchronization
-- Generated: 2018-12-02 18:04
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Gaming

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER SCHEMA `lenguajes`  DEFAULT CHARACTER SET utf8  DEFAULT COLLATE utf8_general_ci ;

CREATE TABLE IF NOT EXISTS `lenguajes`.`Italiano` (
  `idparola` INT(11) NOT NULL AUTO_INCREMENT,
  `parola` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idparola`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `lenguajes`.`English` (
  `idword` INT(11) NOT NULL AUTO_INCREMENT,
  `words` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idword`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `lenguajes`.`Espanol` (
  `idpalabra` INT(11) NOT NULL AUTO_INCREMENT,
  `palabras` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idpalabra`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `lenguajes`.`Ita_Eng` (
  `idparola` INT(11) NOT NULL,
  `idword` INT(11) NOT NULL,
  `sinonimos` TINYINT(4) NOT NULL,
  PRIMARY KEY (`idparola`, `idword`),
  INDEX `fk_Italiano_has_English_English1_idx` (`idword` ASC),
  INDEX `fk_Italiano_has_English_Italiano_idx` (`idparola` ASC),
  CONSTRAINT `fk_Italiano_has_English_Italiano`
    FOREIGN KEY (`idparola`)
    REFERENCES `lenguajes`.`Italiano` (`idparola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Italiano_has_English_English1`
    FOREIGN KEY (`idword`)
    REFERENCES `lenguajes`.`English` (`idword`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `lenguajes`.`Ita_Esp` (
  `idparola` INT(11) NOT NULL,
  `idpalabra` INT(11) NOT NULL,
  `sinonimos` TINYINT(4) NOT NULL,
  PRIMARY KEY (`idparola`, `idpalabra`),
  INDEX `fk_Italiano_has_Espanol_Espanol1_idx` (`idpalabra` ASC),
  INDEX `fk_Italiano_has_Espanol_Italiano1_idx` (`idparola` ASC),
  CONSTRAINT `fk_Italiano_has_Espanol_Italiano1`
    FOREIGN KEY (`idparola`)
    REFERENCES `lenguajes`.`Italiano` (`idparola`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Italiano_has_Espanol_Espanol1`
    FOREIGN KEY (`idpalabra`)
    REFERENCES `lenguajes`.`Espanol` (`idpalabra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `lenguajes`.`Esp_Eng` (
  `idpalabra` INT(11) NOT NULL,
  `idword` INT(11) NOT NULL,
  `sinonimos` TINYINT(4) NOT NULL,
  PRIMARY KEY (`idpalabra`, `idword`),
  INDEX `fk_Espanol_has_English_English1_idx` (`idword` ASC),
  INDEX `fk_Espanol_has_English_Espanol1_idx` (`idpalabra` ASC),
  CONSTRAINT `fk_Espanol_has_English_Espanol1`
    FOREIGN KEY (`idpalabra`)
    REFERENCES `lenguajes`.`Espanol` (`idpalabra`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Espanol_has_English_English1`
    FOREIGN KEY (`idword`)
    REFERENCES `lenguajes`.`English` (`idword`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
