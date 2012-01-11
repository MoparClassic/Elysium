SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `elysium` DEFAULT CHARACTER SET utf8 ;
USE `elysium` ;

-- -----------------------------------------------------
-- Table `elysium`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(12) NOT NULL ,
  `password_hash` VARCHAR(255) NOT NULL ,
  `password_salt` VARCHAR(64) NOT NULL ,
  `permission_bitfield` BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  `subscription_expiry` BIGINT NOT NULL DEFAULT 0 ,
  `x` SMALLINT UNSIGNED NOT NULL DEFAULT 213 ,
  `y` SMALLINT UNSIGNED NOT NULL DEFAULT 452 ,
  `fatigue` TINYINT UNSIGNED NOT NULL DEFAULT 0 ,
  `creation_time` BIGINT UNSIGNED NOT NULL ,
  `creation_ip` VARCHAR(45) NOT NULL ,
  `block_public_chat` TINYINT(1) NOT NULL DEFAULT 0 ,
  `block_private_chat` TINYINT(1) NOT NULL DEFAULT 0 ,
  `block_trade_requests` TINYINT(1) NOT NULL DEFAULT 0 ,
  `block_duel_requests` TINYINT(1) NOT NULL DEFAULT 0 ,
  `automatic_camera` TINYINT(1) NOT NULL DEFAULT 0 ,
  `automatic_screenshot` TINYINT(1) NOT NULL DEFAULT 0 ,
  `enable_sound` TINYINT(1) NOT NULL DEFAULT 0 ,
  `display_roof` TINYINT(1) NOT NULL DEFAULT 0 ,
  `display_combat_window` TINYINT(1) NOT NULL DEFAULT 0 ,
  `combat_style` TINYINT NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`player_stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player_stats` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player_stats` (
  `id_player` INT UNSIGNED NOT NULL ,
  `cur_attack` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_defense` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_strength` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_hitpoints` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_range` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_prayer` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_magic` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_cooking` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_woodcutting` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_fletching` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_fishing` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_firemaking` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_crafting` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_smithing` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_mining` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_herblaw` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_agility` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `cur_thieving` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `exp_attack` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_defense` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_strength` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_hitpoints` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_range` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_prayer` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_magic` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_cooking` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_woodcutting` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_fletching` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_fishing` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_firemaking` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_crafting` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_smithing` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_mining` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_herblaw` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_agility` INT UNSIGNED NOT NULL DEFAULT 0 ,
  `exp_thieving` INT UNSIGNED NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id_player`) ,
  CONSTRAINT `fk_player_stats_player`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`player_avatar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player_avatar` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player_avatar` (
  `id_player` INT UNSIGNED NOT NULL ,
  `hair_colour` TINYINT UNSIGNED NOT NULL DEFAULT 2 ,
  `top_colour` TINYINT UNSIGNED NOT NULL DEFAULT 8 ,
  `trouser_colour` TINYINT UNSIGNED NOT NULL DEFAULT 14 ,
  `skin_colour` TINYINT UNSIGNED NOT NULL DEFAULT 0 ,
  `head_sprite` TINYINT UNSIGNED NOT NULL DEFAULT 1 ,
  `body_sprite` TINYINT UNSIGNED NOT NULL DEFAULT 2 ,
  `is_male` TINYINT(1) NOT NULL DEFAULT 1 ,
  `skull_expiry` BIGINT UNSIGNED NOT NULL DEFAULT 0 ,
  PRIMARY KEY (`id_player`) ,
  CONSTRAINT `fk_player_look_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`quest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`quest` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`quest` (
  `id` INT UNSIGNED NOT NULL ,
  `name` VARCHAR(50) NOT NULL ,
  `points` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`quest_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`quest_status` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`quest_status` (
  `id_quest` INT UNSIGNED NOT NULL ,
  `id_player` INT UNSIGNED NOT NULL ,
  `quest_waypoint` SMALLINT UNSIGNED NOT NULL DEFAULT 0 ,
  `state` ENUM('NOT_STARTED', 'INCOMPLETE', 'COMPLETE') NOT NULL DEFAULT 'NOT_STARTED' ,
  PRIMARY KEY (`id_quest`, `id_player`) ,
  INDEX `fk_quest_status_quest1` (`id_quest` ASC) ,
  INDEX `fk_quest_status_player1` (`id_player` ASC) ,
  CONSTRAINT `fk_quest_status_quest1`
    FOREIGN KEY (`id_quest` )
    REFERENCES `elysium`.`quest` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_quest_status_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`item` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`item` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `name` VARCHAR(50) NOT NULL ,
  `description` VARCHAR(100) NOT NULL ,
  `command` VARCHAR(50) NULL ,
  `sprite` MEDIUMINT UNSIGNED NOT NULL ,
  `base_price` INT UNSIGNED NOT NULL ,
  `stackable` TINYINT(1) NOT NULL ,
  `wieldable` TINYINT(1) NOT NULL ,
  `picture_mask` INT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`player_bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player_bank` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player_bank` (
  `id_player` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  INDEX `fk_player_bank_item1` (`id_item` ASC) ,
  PRIMARY KEY (`id_player`, `id_item`) ,
  CONSTRAINT `fk_player_bank_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_bank_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`player_inventory`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player_inventory` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player_inventory` (
  `id_player` INT UNSIGNED NOT NULL ,
  `slot` TINYINT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  `wielded` TINYINT(1) NOT NULL DEFAULT 0 ,
  INDEX `fk_player_inventory_player1` (`id_player` ASC) ,
  INDEX `fk_player_inventory_item1` (`id_item` ASC) ,
  PRIMARY KEY (`id_player`, `slot`) ,
  CONSTRAINT `fk_player_inventory_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_inventory_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`player_friends`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player_friends` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player_friends` (
  `id_player` INT UNSIGNED NOT NULL ,
  `id_friend` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_player`, `id_friend`) ,
  INDEX `fk_player_friends_player1` (`id_player` ASC) ,
  INDEX `fk_player_friends_player2` (`id_friend` ASC) ,
  CONSTRAINT `fk_player_friends_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_friends_player2`
    FOREIGN KEY (`id_friend` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`player_ignores`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`player_ignores` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`player_ignores` (
  `id_player` INT UNSIGNED NOT NULL ,
  `id_ignore` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_player`, `id_ignore`) ,
  INDEX `fk_player_ignores_player1` (`id_player` ASC) ,
  INDEX `fk_player_ignores_player2` (`id_ignore` ASC) ,
  CONSTRAINT `fk_player_ignores_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_player_ignores_player2`
    FOREIGN KEY (`id_ignore` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`store`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`store` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`store` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(60) NOT NULL ,
  `greeting` VARCHAR(60) NOT NULL ,
  `is_general_store` TINYINT(1) NOT NULL ,
  `sell_modifier` MEDIUMINT UNSIGNED NOT NULL ,
  `buy_modifier` MEDIUMINT UNSIGNED NOT NULL ,
  `respawn_rate` MEDIUMINT UNSIGNED NOT NULL ,
  `min_x` SMALLINT UNSIGNED NOT NULL ,
  `min_y` SMALLINT UNSIGNED NOT NULL ,
  `max_x` SMALLINT UNSIGNED NOT NULL ,
  `max_y` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`store_option`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`store_option` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`store_option` (
  `id_store` INT UNSIGNED NOT NULL ,
  `option` VARCHAR(80) NOT NULL ,
  INDEX `id_store_index` (`id_store` ASC) ,
  CONSTRAINT `fk_store_option_store1`
    FOREIGN KEY (`id_store` )
    REFERENCES `elysium`.`store` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`store_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`store_item` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`store_item` (
  `id_store` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` MEDIUMINT NOT NULL ,
  INDEX `fk_store_item_store1` (`id_store` ASC) ,
  PRIMARY KEY (`id_store`, `id_item`) ,
  INDEX `fk_store_item_item_definition1` (`id_item` ASC) ,
  CONSTRAINT `fk_store_item_store1`
    FOREIGN KEY (`id_store` )
    REFERENCES `elysium`.`store` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_item_item_definition1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`item_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`item_location` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`item_location` (
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `x` SMALLINT UNSIGNED NOT NULL ,
  `y` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  INDEX `fk_item_location_item_definition1` (`id_item` ASC) ,
  CONSTRAINT `fk_item_location_item_definition1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`prayer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`prayer` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`prayer` (
  `id` TINYINT UNSIGNED NOT NULL ,
  `level` TINYINT UNSIGNED NOT NULL ,
  `drain_rate` TINYINT UNSIGNED NOT NULL ,
  `name` VARCHAR(30) NOT NULL ,
  `description` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`door`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`door` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`door` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  `command_one` VARCHAR(45) NULL ,
  `command_two` VARCHAR(45) NULL ,
  `model_var_one` SMALLINT UNSIGNED NOT NULL ,
  `model_var_two` SMALLINT UNSIGNED NOT NULL ,
  `model_var_three` SMALLINT UNSIGNED NOT NULL ,
  `door_type` TINYINT UNSIGNED NOT NULL ,
  `unknown` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`game_object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`game_object` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`game_object` (
  `id` INT NOT NULL ,
  `name` VARCHAR(45) NOT NULL ,
  `description` VARCHAR(100) NULL ,
  `command_one` VARCHAR(45) NULL ,
  `command_two` VARCHAR(45) NULL ,
  `type` TINYINT UNSIGNED NOT NULL ,
  `width` TINYINT UNSIGNED NOT NULL ,
  `height` TINYINT UNSIGNED NOT NULL ,
  `ground_item_var` TINYINT UNSIGNED NOT NULL ,
  `object_model` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
