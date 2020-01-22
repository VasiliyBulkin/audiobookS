-- Valentina Studio --
-- MySQL dump --
-- ---------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- ---------------------------------------------------------


-- CREATE TABLE "author" ---------------------------------------
CREATE TABLE `author` ( 
	`id` BigInt( 20 ) NOT NULL,
	`authorname` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE TABLE "book" -----------------------------------------
CREATE TABLE `book` ( 
	`id` BigInt( 20 ) NOT NULL,
	`filename` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`name` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	`author_id` BigInt( 20 ) NOT NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE TABLE "book_genre" -----------------------------------
CREATE TABLE `book_genre` ( 
	`book_id` BigInt( 20 ) NOT NULL,
	`genre_id` BigInt( 20 ) NOT NULL,
	PRIMARY KEY ( `book_id`, `genre_id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------




-- CREATE TABLE "genre" ----------------------------------------
CREATE TABLE `genre` ( 
	`id` BigInt( 20 ) NOT NULL,
	`name` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE TABLE "hibernate_sequence" ---------------------------
CREATE TABLE `hibernate_sequence` ( 
	`next_val` BigInt( 20 ) NULL )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE TABLE "user_id" --------------------------------------
CREATE TABLE `user_id` ( 
	`owner_id` BigInt( 20 ) NOT NULL,
	`book_id` BigInt( 20 ) NOT NULL,
	PRIMARY KEY ( `book_id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE TABLE "user_role" ------------------------------------
CREATE TABLE `user_role` ( 
	`user_id` BigInt( 20 ) NOT NULL,
	`roles` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE TABLE "usr" ------------------------------------------
CREATE TABLE `usr` ( 
	`id` BigInt( 20 ) NOT NULL,
	`active` Bit( 1 ) NOT NULL,
	`password` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	`username` VarChar( 255 ) CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
	PRIMARY KEY ( `id` ) )
CHARACTER SET = utf8
COLLATE = utf8_general_ci
ENGINE = InnoDB;
-- -------------------------------------------------------------


-- CREATE INDEX "FKklnrv3weler2ftkweewlky958" ------------------
CREATE INDEX `FKklnrv3weler2ftkweewlky958` USING BTREE ON `book`( `author_id` );
-- -------------------------------------------------------------


-- CREATE INDEX "FK8l6ops8exmjrlr89hmfow4mmo" ------------------
CREATE INDEX `FK8l6ops8exmjrlr89hmfow4mmo` USING BTREE ON `book_genre`( `genre_id` );
-- -------------------------------------------------------------


-- CREATE INDEX "flyway_schema_history_s_idx" ------------------
CREATE INDEX `flyway_schema_history_s_idx` USING BTREE ON `flyway_schema_history`( `success` );
-- -------------------------------------------------------------


-- CREATE INDEX "FKd8sbwb9sn4txxpuiqmf4xu05" -------------------
CREATE INDEX `FKd8sbwb9sn4txxpuiqmf4xu05` USING BTREE ON `user_id`( `owner_id` );
-- -------------------------------------------------------------


-- CREATE INDEX "FKfpm8swft53ulq2hl11yplpr5" -------------------
CREATE INDEX `FKfpm8swft53ulq2hl11yplpr5` USING BTREE ON `user_role`( `user_id` );
-- -------------------------------------------------------------


-- CREATE LINK "FKklnrv3weler2ftkweewlky958" -------------------
ALTER TABLE `book`
	ADD CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY ( `author_id` )
	REFERENCES `author`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------


-- CREATE LINK "FK52evq6pdc5ypanf41bij5u218" -------------------
ALTER TABLE `book_genre`
	ADD CONSTRAINT `FK52evq6pdc5ypanf41bij5u218` FOREIGN KEY ( `book_id` )
	REFERENCES `book`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------


-- CREATE LINK "FK8l6ops8exmjrlr89hmfow4mmo" -------------------
ALTER TABLE `book_genre`
	ADD CONSTRAINT `FK8l6ops8exmjrlr89hmfow4mmo` FOREIGN KEY ( `genre_id` )
	REFERENCES `genre`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------


-- CREATE LINK "FKd8sbwb9sn4txxpuiqmf4xu05" --------------------
ALTER TABLE `user_id`
	ADD CONSTRAINT `FKd8sbwb9sn4txxpuiqmf4xu05` FOREIGN KEY ( `owner_id` )
	REFERENCES `usr`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------


-- CREATE LINK "FKlwinuao3alkwdji4f6verhpac" -------------------
ALTER TABLE `user_id`
	ADD CONSTRAINT `FKlwinuao3alkwdji4f6verhpac` FOREIGN KEY ( `book_id` )
	REFERENCES `book`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------


-- CREATE LINK "FKfpm8swft53ulq2hl11yplpr5" --------------------
ALTER TABLE `user_role`
	ADD CONSTRAINT `FKfpm8swft53ulq2hl11yplpr5` FOREIGN KEY ( `user_id` )
	REFERENCES `usr`( `id` )
	ON DELETE Restrict
	ON UPDATE Restrict;
-- -------------------------------------------------------------


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- ---------------------------------------------------------


