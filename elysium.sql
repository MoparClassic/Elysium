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
  `creation_ip` VARCHAR(15) NOT NULL ,
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
  UNIQUE INDEX `username_UNIQUE` USING BTREE (`username` ASC) )
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
  `command` VARCHAR(15) NULL ,
  `wieldable` SMALLINT UNSIGNED NOT NULL ,
  `sprite` SMALLINT UNSIGNED NOT NULL ,
  `base_price` MEDIUMINT UNSIGNED NOT NULL ,
  `picture_mask` INT NOT NULL ,
  `stackable` TINYINT(1) NOT NULL ,
  `tradable` TINYINT(1) NOT NULL ,
  `members` TINYINT(1) NOT NULL ,
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
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
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
  `id_store` SMALLINT UNSIGNED NOT NULL ,
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
  `id_store` SMALLINT UNSIGNED NOT NULL ,
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
  `respawn_time` SMALLINT UNSIGNED NOT NULL ,
  INDEX `fk_item_location_item_definition1` (`id_item` ASC) ,
  PRIMARY KEY (`id_item`, `x`, `y`) ,
  CONSTRAINT `fk_item_location_item_definition1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_door`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_door` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_door` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `name` VARCHAR(30) NOT NULL ,
  `description` VARCHAR(80) NULL ,
  `command_one` VARCHAR(12) NOT NULL ,
  `command_two` VARCHAR(12) NOT NULL ,
  `model_var_one` SMALLINT UNSIGNED NOT NULL ,
  `model_var_two` INT UNSIGNED NOT NULL ,
  `model_var_three` INT UNSIGNED NOT NULL ,
  `door_type` TINYINT(1) NOT NULL ,
  `unknown` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`game_object`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`game_object` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`game_object` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `name` VARCHAR(25) NOT NULL ,
  `description` VARCHAR(80) NULL ,
  `command_one` VARCHAR(20) NOT NULL ,
  `command_two` VARCHAR(20) NOT NULL ,
  `object_model` VARCHAR(30) NOT NULL ,
  `type` TINYINT UNSIGNED NOT NULL ,
  `width` TINYINT UNSIGNED NOT NULL ,
  `height` TINYINT UNSIGNED NOT NULL ,
  `ground_item_var` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`item_search_subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`item_search_subject` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`item_search_subject` (
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `term` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`id_item`, `term`) ,
  INDEX `fk_item_search_subjects_item1` (`id_item` ASC) ,
  INDEX `idx_term` USING BTREE (`term` ASC) ,
  CONSTRAINT `fk_item_search_subjects_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_prayer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_prayer` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_prayer` (
  `id` TINYINT UNSIGNED NOT NULL ,
  `name` VARCHAR(25) NOT NULL ,
  `description` VARCHAR(80) NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `drain_rate` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_tile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_tile` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_tile` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `colour` INT NOT NULL ,
  `unknown` TINYINT UNSIGNED NOT NULL ,
  `object_type` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`spell`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`spell` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`spell` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `name` VARCHAR(25) NOT NULL ,
  `description` VARCHAR(50) NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `type` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`spell_rune`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`spell_rune` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`spell_rune` (
  `id_spell` SMALLINT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_spell`, `id_item`) ,
  INDEX `fk_spell_rune_spell1` (`id_spell` ASC) ,
  INDEX `fk_spell_rune_item1` (`id_item` ASC) ,
  CONSTRAINT `fk_spell_rune_spell1`
    FOREIGN KEY (`id_spell` )
    REFERENCES `elysium`.`spell` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_spell_rune_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`npc`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`npc` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`npc` (
  `id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(30) NOT NULL ,
  `description` VARCHAR(100) NOT NULL ,
  `command` VARCHAR(15) NULL ,
  `hits` TINYINT UNSIGNED NOT NULL ,
  `attack` TINYINT UNSIGNED NOT NULL ,
  `strength` TINYINT UNSIGNED NOT NULL ,
  `defense` TINYINT UNSIGNED NOT NULL ,
  `attackable` TINYINT(1) NOT NULL ,
  `aggressive` TINYINT(1) NOT NULL ,
  `respawn_time` TINYINT UNSIGNED NOT NULL ,
  `hair_colour` MEDIUMINT UNSIGNED NOT NULL ,
  `top_colour` MEDIUMINT UNSIGNED NOT NULL ,
  `bottom_colour` MEDIUMINT UNSIGNED NOT NULL ,
  `skin_colour` MEDIUMINT UNSIGNED NOT NULL ,
  `camera_one` SMALLINT UNSIGNED NOT NULL ,
  `camera_two` SMALLINT UNSIGNED NOT NULL ,
  `walk_model` TINYINT UNSIGNED NOT NULL ,
  `combat_model` TINYINT UNSIGNED NOT NULL ,
  `combat_sprite` TINYINT UNSIGNED NOT NULL ,
  `sprite1` SMALLINT NOT NULL ,
  `sprite2` SMALLINT NOT NULL ,
  `sprite3` SMALLINT NOT NULL ,
  `sprite4` SMALLINT NOT NULL ,
  `sprite5` SMALLINT NOT NULL ,
  `sprite6` SMALLINT NOT NULL ,
  `sprite7` SMALLINT NOT NULL ,
  `sprite8` SMALLINT NOT NULL ,
  `sprite9` SMALLINT NOT NULL ,
  `sprite10` SMALLINT NOT NULL ,
  `sprite11` SMALLINT NOT NULL ,
  `sprite12` SMALLINT NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 0;


-- -----------------------------------------------------
-- Table `elysium`.`def_firemaking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_firemaking` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_firemaking` (
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  `length` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_item`) ,
  CONSTRAINT `fk_firemaking_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`game_object_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`game_object_location` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`game_object_location` (
  `id_object` SMALLINT UNSIGNED NOT NULL ,
  `x` SMALLINT UNSIGNED NOT NULL ,
  `y` SMALLINT UNSIGNED NOT NULL ,
  `direction` TINYINT UNSIGNED NOT NULL ,
  `type` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_object`, `x`, `y`) ,
  CONSTRAINT `fk_object_location_game_object1`
    FOREIGN KEY (`id_object` )
    REFERENCES `elysium`.`game_object` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`npc_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`npc_location` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`npc_location` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_npc` SMALLINT UNSIGNED NOT NULL ,
  `start_x` SMALLINT UNSIGNED NOT NULL ,
  `start_y` SMALLINT UNSIGNED NOT NULL ,
  `min_x` SMALLINT UNSIGNED NOT NULL ,
  `min_y` SMALLINT UNSIGNED NOT NULL ,
  `max_x` SMALLINT UNSIGNED NOT NULL ,
  `max_y` SMALLINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_npc_location_npc1` (`id_npc` ASC) ,
  CONSTRAINT `fk_npc_location_npc1`
    FOREIGN KEY (`id_npc` )
    REFERENCES `elysium`.`npc` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_telepoint`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_telepoint` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_telepoint` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `trigger_x` SMALLINT UNSIGNED NOT NULL ,
  `trigger_y` SMALLINT UNSIGNED NOT NULL ,
  `destination_x` SMALLINT UNSIGNED NOT NULL ,
  `destination_y` SMALLINT UNSIGNED NOT NULL ,
  `command` VARCHAR(15) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_login` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_login` (
  `id_player` INT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  `ip` VARCHAR(15) NOT NULL ,
  `hostname` VARCHAR(100) NOT NULL ,
  `status` ENUM('ACCEPTED', 'REJECTED') NOT NULL ,
  INDEX `fk_log_login_player1` (`id_player` ASC) ,
  CONSTRAINT `fk_log_login_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_trade` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_trade` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_player_one` INT UNSIGNED NOT NULL ,
  `id_player_two` INT UNSIGNED NOT NULL ,
  `x` SMALLINT UNSIGNED NOT NULL ,
  `y` SMALLINT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  INDEX `fk_log_trade_player1` USING BTREE (`id_player_one` ASC) ,
  INDEX `fk_log_trade_player2` (`id_player_two` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_log_trade_player1`
    FOREIGN KEY (`id_player_one` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_trade_player2`
    FOREIGN KEY (`id_player_two` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_drop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_drop` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_drop` (
  `id` BIGINT UNSIGNED NOT NULL ,
  `id_player` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  `type` ENUM('DROP', 'PLAYER_KILLED', 'NPC_KILLED') NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_drop_player1` (`id_player` ASC) ,
  INDEX `fk_log_drop_item1` (`id_item` ASC) ,
  UNIQUE INDEX `uq_item_uid` (`id` ASC) ,
  CONSTRAINT `fk_log_drop_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_drop_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_pickup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_pickup` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_pickup` (
  `id` BIGINT UNSIGNED NOT NULL ,
  `id_player` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_pickup_player1` (`id_player` ASC) ,
  INDEX `fk_log_pickup_item1` (`id_item` ASC) ,
  CONSTRAINT `fk_log_pickup_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_pickup_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_infraction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_infraction` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_infraction` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `issuer` INT UNSIGNED NOT NULL ,
  `recipient` INT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  `type` ENUM('BAN', 'MUTE') NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_infraction_player1` (`issuer` ASC) ,
  INDEX `fk_log_infraction_player2` (`recipient` ASC) ,
  CONSTRAINT `fk_log_infraction_player1`
    FOREIGN KEY (`issuer` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_infraction_player2`
    FOREIGN KEY (`recipient` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_player_kill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_player_kill` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_player_kill` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_killer` INT UNSIGNED NOT NULL ,
  `id_victim` INT UNSIGNED NOT NULL ,
  `x` SMALLINT UNSIGNED NOT NULL ,
  `y` SMALLINT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_player_kill_player1` (`id_killer` ASC) ,
  INDEX `fk_log_player_kill_player2` (`id_victim` ASC) ,
  CONSTRAINT `fk_log_player_kill_player1`
    FOREIGN KEY (`id_killer` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_player_kill_player2`
    FOREIGN KEY (`id_victim` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_npc_kill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_npc_kill` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_npc_kill` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_killer` INT UNSIGNED NOT NULL ,
  `id_victim` SMALLINT UNSIGNED NOT NULL ,
  `x` SMALLINT UNSIGNED NOT NULL ,
  `y` SMALLINT UNSIGNED NOT NULL ,
  `time` BIGINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_npc_kill_player1` (`id_killer` ASC) ,
  INDEX `fk_log_npc_kill_npc1` (`id_victim` ASC) ,
  CONSTRAINT `fk_log_npc_kill_player1`
    FOREIGN KEY (`id_killer` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_npc_kill_npc1`
    FOREIGN KEY (`id_victim` )
    REFERENCES `elysium`.`npc` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_session` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_session` (
  `id` INT UNSIGNED NOT NULL ,
  `id_player` INT UNSIGNED NOT NULL ,
  `elapsed_time` INT UNSIGNED NOT NULL ,
  INDEX `fk_log_session_player1` (`id_player` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_log_session_player1`
    FOREIGN KEY (`id_player` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_player_kill_drop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_player_kill_drop` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_player_kill_drop` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_player_kill_log` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  INDEX `fk_log_player_kill_drop_log_player_kill1` (`id_player_kill_log` ASC) ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_player_kill_drop_item1` (`id_item` ASC) ,
  CONSTRAINT `fk_log_player_kill_drop_log_player_kill1`
    FOREIGN KEY (`id_player_kill_log` )
    REFERENCES `elysium`.`log_player_kill` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_player_kill_drop_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_npc_kill_drop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_npc_kill_drop` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_npc_kill_drop` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT ,
  `id_npc_kill_log` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_npc_kill_drop_log_npc_kill1` (`id_npc_kill_log` ASC) ,
  INDEX `fk_log_npc_kill_drop_item1` (`id_item` ASC) ,
  CONSTRAINT `fk_log_npc_kill_drop_log_npc_kill1`
    FOREIGN KEY (`id_npc_kill_log` )
    REFERENCES `elysium`.`log_npc_kill` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_npc_kill_drop_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`log_trade_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`log_trade_item` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`log_trade_item` (
  `id` INT UNSIGNED NOT NULL ,
  `id_log_trade` INT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `recipient` INT UNSIGNED NOT NULL ,
  `amount` INT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_log_trade_item_log_trade1` (`id_log_trade` ASC) ,
  INDEX `fk_log_trade_item_player1` (`recipient` ASC) ,
  INDEX `fk_log_trade_item_item1` (`id_item` ASC) ,
  CONSTRAINT `fk_log_trade_item_log_trade1`
    FOREIGN KEY (`id_log_trade` )
    REFERENCES `elysium`.`log_trade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_trade_item_player1`
    FOREIGN KEY (`recipient` )
    REFERENCES `elysium`.`player` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_trade_item_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_arrow`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_arrow` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_arrow` (
  `id_arrow_head` SMALLINT UNSIGNED NOT NULL ,
  `id_arrow` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` DECIMAL(3,1) UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_arrow_head`) ,
  INDEX `fk_arrow_item2` (`id_arrow` ASC) ,
  CONSTRAINT `fk_arrow_item1`
    FOREIGN KEY (`id_arrow_head` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_arrow_item2`
    FOREIGN KEY (`id_arrow` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_bowstring`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_bowstring` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_bowstring` (
  `id_unstrung_bow` SMALLINT UNSIGNED NOT NULL ,
  `id_bow` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_unstrung_bow`) ,
  INDEX `fk_def_bowstring_item2` (`id_bow` ASC) ,
  CONSTRAINT `fk_def_bowstring_item1`
    FOREIGN KEY (`id_unstrung_bow` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_bowstring_item2`
    FOREIGN KEY (`id_bow` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_cooking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_cooking` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_cooking` (
  `id_raw_food` SMALLINT UNSIGNED NOT NULL ,
  `id_cooked_food` SMALLINT UNSIGNED NOT NULL ,
  `id_burned_food` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_raw_food`) ,
  INDEX `fk_def_cooking_item2` (`id_cooked_food` ASC) ,
  INDEX `fk_def_cooking_item3` (`id_burned_food` ASC) ,
  CONSTRAINT `fk_def_cooking_item1`
    FOREIGN KEY (`id_raw_food` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_cooking_item2`
    FOREIGN KEY (`id_cooked_food` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_cooking_item3`
    FOREIGN KEY (`id_burned_food` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_crafting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_crafting` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_crafting` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `id_result` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  INDEX `fk_def_crafting_item2` (`id_result` ASC) ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_def_crafting_item2`
    FOREIGN KEY (`id_result` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_dart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_dart` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_dart` (
  `id_dart_tip` SMALLINT UNSIGNED NOT NULL ,
  `id_dart` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` DECIMAL(3,1) UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_dart_tip`) ,
  INDEX `fk_def_dart_item2` (`id_dart` ASC) ,
  CONSTRAINT `fk_def_dart_item1`
    FOREIGN KEY (`id_dart_tip` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_dart_item2`
    FOREIGN KEY (`id_dart` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_edible`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_edible` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_edible` (
  `id_edible` SMALLINT UNSIGNED NOT NULL ,
  `health` TINYINT NOT NULL ,
  PRIMARY KEY (`id_edible`) ,
  CONSTRAINT `fk_def_edible_item1`
    FOREIGN KEY (`id_edible` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_gem`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_gem` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_gem` (
  `id_uncut_gem` SMALLINT UNSIGNED NOT NULL ,
  `id_cut_gem` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_uncut_gem`) ,
  INDEX `fk_def_gem_item2` (`id_cut_gem` ASC) ,
  CONSTRAINT `fk_def_gem_item1`
    FOREIGN KEY (`id_uncut_gem` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_gem_item2`
    FOREIGN KEY (`id_cut_gem` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_potion_primary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_potion_primary` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_potion_primary` (
  `id_herb` SMALLINT UNSIGNED NOT NULL ,
  `id_unfinished_potion` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_herb`) ,
  INDEX `fk_def_potion_primary_item2` (`id_unfinished_potion` ASC) ,
  CONSTRAINT `fk_def_potion_primary_item1`
    FOREIGN KEY (`id_herb` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_potion_primary_item2`
    FOREIGN KEY (`id_unfinished_potion` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_potion_secondary`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_potion_secondary` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_potion_secondary` (
  `id_ingredient` SMALLINT UNSIGNED NOT NULL ,
  `id_unfinished_potion` SMALLINT UNSIGNED NOT NULL ,
  `id_finished_potion` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_ingredient`) ,
  INDEX `fk_def_potion_secondary_item2` (`id_unfinished_potion` ASC) ,
  INDEX `fk_def_potion_secondary_item3` (`id_finished_potion` ASC) ,
  CONSTRAINT `fk_def_potion_secondary_item1`
    FOREIGN KEY (`id_ingredient` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_potion_secondary_item2`
    FOREIGN KEY (`id_unfinished_potion` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_potion_secondary_item3`
    FOREIGN KEY (`id_finished_potion` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_fletching`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_fletching` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_fletching` (
  `id_logs` SMALLINT UNSIGNED NOT NULL ,
  `id_shortbow` SMALLINT UNSIGNED NOT NULL ,
  `id_longbow` SMALLINT UNSIGNED NOT NULL ,
  `shaft_amount` TINYINT UNSIGNED NOT NULL ,
  `shaft_level` TINYINT UNSIGNED NOT NULL ,
  `shortbow_level` TINYINT UNSIGNED NOT NULL ,
  `shortbow_experience` TINYINT UNSIGNED NOT NULL ,
  `longbow_level` TINYINT UNSIGNED NOT NULL ,
  `longbow_experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_logs`) ,
  INDEX `fk_def_fletching_item2` (`id_shortbow` ASC) ,
  INDEX `fk_def_fletching_item3` (`id_longbow` ASC) ,
  CONSTRAINT `fk_def_fletching_item1`
    FOREIGN KEY (`id_logs` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_fletching_item2`
    FOREIGN KEY (`id_shortbow` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_fletching_item3`
    FOREIGN KEY (`id_longbow` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_smelting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_smelting` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_smelting` (
  `id_bar` SMALLINT UNSIGNED NOT NULL ,
  `id_ore` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_bar`) ,
  INDEX `fk_def_smelting_item1` (`id_ore` ASC) ,
  CONSTRAINT `fk_def_smelting_item1`
    FOREIGN KEY (`id_ore` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_smelting_item2`
    FOREIGN KEY (`id_bar` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_smelting_ore`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_smelting_ore` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_smelting_ore` (
  `id_bar` SMALLINT UNSIGNED NOT NULL ,
  `id_secondary_ore` SMALLINT UNSIGNED NOT NULL ,
  `amount` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_bar`) ,
  INDEX `fk_def_smelting_ore_item1` (`id_secondary_ore` ASC) ,
  CONSTRAINT `fk_def_smelting_ore_def_smelting1`
    FOREIGN KEY (`id_bar` )
    REFERENCES `elysium`.`def_smelting` (`id_bar` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_smelting_ore_item1`
    FOREIGN KEY (`id_secondary_ore` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_smithing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_smithing` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_smithing` (
  `id` SMALLINT UNSIGNED NOT NULL ,
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `amount` TINYINT UNSIGNED NOT NULL ,
  `required_bars` TINYINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_def_smithing_item1` (`id_item` ASC) ,
  CONSTRAINT `fk_def_smithing_item1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_herb_identification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_herb_identification` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_herb_identification` (
  `id_unidentified_herb` SMALLINT UNSIGNED NOT NULL ,
  `id_identified_herb` SMALLINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  `experience` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_unidentified_herb`) ,
  INDEX `fk_def_herb_identification_item2` (`id_identified_herb` ASC) ,
  CONSTRAINT `fk_def_herb_identification_item1`
    FOREIGN KEY (`id_unidentified_herb` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_def_herb_identification_item2`
    FOREIGN KEY (`id_identified_herb` )
    REFERENCES `elysium`.`item` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_wieldable_items`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_wieldable_items` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_wieldable_items` (
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `sprite` INT NOT NULL ,
  `type` INT NOT NULL ,
  `wield_position` TINYINT UNSIGNED NOT NULL ,
  `armour_points` TINYINT UNSIGNED NOT NULL ,
  `weapon_aim_points` TINYINT UNSIGNED NOT NULL ,
  `weapon_power_points` TINYINT UNSIGNED NOT NULL ,
  `magic_points` TINYINT UNSIGNED NOT NULL ,
  `prayer_points` TINYINT UNSIGNED NOT NULL ,
  `range_points` TINYINT UNSIGNED NOT NULL ,
  `female_only` TINYINT(1) NOT NULL ,
  PRIMARY KEY (`id_item`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elysium`.`def_wieldable_level_requirements`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `elysium`.`def_wieldable_level_requirements` ;

CREATE  TABLE IF NOT EXISTS `elysium`.`def_wieldable_level_requirements` (
  `id_item` SMALLINT UNSIGNED NOT NULL ,
  `stat` TINYINT UNSIGNED NOT NULL ,
  `required_level` TINYINT UNSIGNED NOT NULL ,
  PRIMARY KEY (`id_item`, `stat`) ,
  CONSTRAINT `fk_def_wieldable_level_requirements_def_wieldable_items1`
    FOREIGN KEY (`id_item` )
    REFERENCES `elysium`.`def_wieldable_items` (`id_item` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Placeholder table for view `elysium`.`vw_secure_player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elysium`.`vw_secure_player` (`id` INT, `username` INT, `permission_bitfield` INT, `subscription_expiry` INT, `x` INT, `y` INT, `fatigue` INT, `creation_time` INT, `creation_ip` INT, `block_public_chat` INT, `block_private_chat` INT, `block_trade_requests` INT, `block_duel_requests` INT, `automatic_camera` INT, `automatic_screenshot` INT, `enable_sound` INT, `display_roof` INT, `display_combat_window` INT, `combat_style` INT);

-- -----------------------------------------------------
-- View `elysium`.`vw_secure_player`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `elysium`.`vw_secure_player` ;
DROP TABLE IF EXISTS `elysium`.`vw_secure_player`;
USE `elysium`;
-- Create a player view that can be selected on without accessing the password or salt
CREATE  OR REPLACE VIEW `elysium`.`vw_secure_player` AS
SELECT p.id, p.username, p.permission_bitfield, p.subscription_expiry, p.x, p.y, p.fatigue,
       p.creation_time, p.creation_ip, p.block_public_chat, p.block_private_chat,
       p.block_trade_requests, p.block_duel_requests, p.automatic_camera, p.automatic_screenshot,
       p.enable_sound, p.display_roof, p.display_combat_window, p.combat_style
FROM player p;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
