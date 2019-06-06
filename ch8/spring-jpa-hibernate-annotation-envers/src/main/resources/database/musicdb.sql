
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `singer`;
DROP TABLE IF EXISTS `singer_history`;
DROP TABLE IF EXISTS `revinfo`;

-- ----------------------------
-- Table structure for singer
-- ----------------------------
CREATE TABLE `singer`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `birth_date` date NOT NULL,
  `version` int(10) NOT NULL DEFAULT 0,
  `created_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `created_date` timestamp,
  `last_modified_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `last_modified_date` timestamp,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `IK_SINGER_NAME`(`first_name`, `last_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for singer_history
-- ----------------------------
CREATE TABLE `singer_history`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `last_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `birth_date` date,
  `version` int(10) NOT NULL DEFAULT 0,
  `created_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `created_date` timestamp,
  `last_modified_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci,
  `last_modified_date` timestamp,
  `revision` int(10) NOT NULL,
  `revision_type` int(10),
  `revision_end` int(10),
  `revision_end_timestamp` timestamp,
  UNIQUE UQ_SINGER_AUDIT_H (`first_name`, `last_name`)  USING BTREE,
  PRIMARY KEY (`id`, `revision`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for revinfo
-- ----------------------------
CREATE TABLE `revinfo`  (
  `rev` int(10) NOT NULL AUTO_INCREMENT,
  `revtstmp` bigint(20) NOT NULL,
  PRIMARY KEY (`rev`, `revtstmp`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
