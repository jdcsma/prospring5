/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : musicdb

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 28/05/2019 12:40:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `album`;
DROP TABLE IF EXISTS `singer_instrument`;
DROP TABLE IF EXISTS `instrument`;
DROP TABLE IF EXISTS `singer`;

-- ----------------------------
-- Table structure for instrument
-- ----------------------------

CREATE TABLE `instrument`  (
  `instrument_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`instrument_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of instrument
-- ----------------------------
INSERT INTO `instrument` VALUES ('Drums');
INSERT INTO `instrument` VALUES ('Guitar');
INSERT INTO `instrument` VALUES ('Piano');
INSERT INTO `instrument` VALUES ('Synthesizer');
INSERT INTO `instrument` VALUES ('Voice');

-- ----------------------------
-- Table structure for singer
-- ----------------------------

CREATE TABLE `singer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birth_date` date NOT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_SINGER_FULL_NAME`(`first_name`, `last_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (1, 'John', 'Mayer', '1977-10-16', 0);
INSERT INTO `singer` VALUES (2, 'Eric', 'Clapton', '1945-03-30', 0);
  INSERT INTO `singer` VALUES (3, 'John', 'Butler', '1975-04-01', 0);

-- ----------------------------
-- Table structure for album
-- ----------------------------

CREATE TABLE `album`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `singer_id` bigint(20) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `release_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IK_SINGER_ID`(`singer_id`) USING BTREE,
  CONSTRAINT `FK_ALBUM_SINGER_ID` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 0 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES (1, 1, 'The Search For Everything', '2017-01-20');
INSERT INTO `album` VALUES (2, 1, 'Battle Stuidies', '2009-11-17');
INSERT INTO `album` VALUES (3, 2, 'From The Cradle', '1994-09-13');

-- ----------------------------
-- Table structure for singer_instrument
-- ----------------------------

CREATE TABLE `singer_instrument`  (
  `singer_id` bigint(20) NOT NULL,
  `instrument_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`singer_id`, `instrument_id`) USING BTREE,
  INDEX `FK_SINGER_INSTRUMENT_2`(`instrument_id`) USING BTREE,
  CONSTRAINT `FK_SI_SINGER_ID` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `FK_SI_INSTRUMENT_ID` FOREIGN KEY (`instrument_id`) REFERENCES `instrument` (`instrument_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of singer_instrument
-- ----------------------------
INSERT INTO `singer_instrument` VALUES (1, 'Guitar');
INSERT INTO `singer_instrument` VALUES (2, 'Guitar');
INSERT INTO `singer_instrument` VALUES (1, 'Piano');

SET FOREIGN_KEY_CHECKS = 1;
