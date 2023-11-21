SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Autor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Autor` (
  `id` INT NOT NULL,
  `cpf` VARCHAR(11) NOT NULL,
  `nome_original` VARCHAR(45) NOT NULL,
  `nome_artistico` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`categoria` (
  `id` INT NOT NULL,
  `nome` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`musica` (
  `id` INT NOT NULL,
  `titulo` VARCHAR(45) NOT NULL,
  `letra` VARCHAR(200) NOT NULL,
  `data_lancamento` DATE NOT NULL,
  `fk_categoria` INT NOT NULL,
  `duracao` VARCHAR(6) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_musica_categoria1_idx` (`fk_categoria` ASC) VISIBLE,
  CONSTRAINT `fk_musica_categoria1`
    FOREIGN KEY (`fk_categoria`)
    REFERENCES `mydb`.`categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `mydb`.`relacionamento_autor_musica`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`relacionamento_autor_musica` (
  `id_tabela` INT NOT NULL,
  `autor_id` INT NOT NULL,
  `musica_id` INT NOT NULL,
  PRIMARY KEY (`id_tabela`),
  INDEX `fk_relacionamento_autor_musica_musica1_idx` (`musica_id` ASC) VISIBLE,
  INDEX `fk_relacionamento_autor_musica_Autor1_idx` (`Autor_id` ASC) VISIBLE,
  CONSTRAINT `fk_relacionamento_autor_musica_Autor1`
    FOREIGN KEY (`autor_id`)
    REFERENCES `mydb`.`Autor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_relacionamento_autor_musica_musica1`
    FOREIGN KEY (`musica_id`)
    REFERENCES `mydb`.`musica` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;