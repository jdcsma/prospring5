
-- ----------------------------
-- Function structure for getFirstNameById
-- ----------------------------
DROP FUNCTION IF EXISTS `getLastNameById`;
delimiter ;;
CREATE FUNCTION `getLastNameById`(`in_id` int)
    RETURNS varchar(60)
BEGIN
	RETURN (SELECT last_name FROM singer WHERE id=in_id);
END
;;
delimiter ;
