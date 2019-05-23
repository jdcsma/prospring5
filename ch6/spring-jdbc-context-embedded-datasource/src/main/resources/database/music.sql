/*
 Navicat Premium Data Transfer

 Source Server         : localhost-mysql
 Source Server Type    : MySQL
 Source Server Version : 50640
 Source Host           : localhost:3306
 Source Schema         : music

 Target Server Type    : MySQL
 Target Server Version : 50640
 File Encoding         : 65001

 Date: 23/05/2019 17:02:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for album
-- ----------------------------
DROP TABLE IF EXISTS `album`;
CREATE TABLE `album`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `singer_id` bigint(20) NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `release_date` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `singer_id`) USING BTREE,
  UNIQUE INDEX `UK_SINGER_ALBUM_1`(`singer_id`, `title`) USING BTREE,
  CONSTRAINT `FK_ALBUM` FOREIGN KEY (`singer_id`) REFERENCES `singer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of album
-- ----------------------------
INSERT INTO `album` VALUES (1, 1, 'The Search For Everything', '2017-01-20');
INSERT INTO `album` VALUES (2, 1, 'Battle Stuidies', '2009-11-17');
INSERT INTO `album` VALUES (3, 2, 'From The Cradle', '1994-09-13');

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birth_date` date NOT NULL,
  `is_death` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_SINGER_1`(`first_name`, `last_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (1, 'John', 'Mayer', '1977-10-16', 0);
INSERT INTO `singer` VALUES (2, 'Eric', 'Clapton', '1945-03-30', 0);
INSERT INTO `singer` VALUES (3, 'John', 'Butler', '1975-04-01', 0);

SET FOREIGN_KEY_CHECKS = 1;
