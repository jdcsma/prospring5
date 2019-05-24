
-- ----------------------------
-- Procedure structure for getFirstNameById
-- ----------------------------
DROP PROCEDURE IF EXISTS `getFirstNameById`;
delimiter ;;
CREATE PROCEDURE `getFirstNameById`(IN `in_id` int)
BEGIN
	SELECT first_name FROM singer WHERE id=in_id;
END
;;
delimiter ;
