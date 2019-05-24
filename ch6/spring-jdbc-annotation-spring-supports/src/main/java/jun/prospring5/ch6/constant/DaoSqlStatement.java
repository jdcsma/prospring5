package jun.prospring5.ch6.constant;

public final class DaoSqlStatement {

    public static final String SQL_SELECT_SINGER_ALL = "SELECT * FROM singer";

    public static final String SQL_SELECT_SINGER_BY_ID = "SELECT * FROM singer WHERE id=:id";

    public static final String SQL_SELECT_SINGER_FULL_NAME_BY_ID =
            "SELECT first_name, last_name FROM singer WHERE id=:id";

    public static final String SQL_SELECT_SINGER_WITH_DETAIL_BY_ID =
            "SELECT s.id,s.first_name,s.last_name,s.birth_date,s.is_death, " +
                    "a.id as album_id,a.title,a.release_date FROM singer s " +
                    "LEFT JOIN album a ON s.id = a.singer_id";

    public static final String SQL_SELECT_SINGER_FIRST_NAME_BY_ID =
            "SELECT first_name FROM singer WHERE id=:id";

    public static final String SQL_SELECT_SINGER_FIRST_NAME_BY_ID_WITH_STORED_PROCEDURE =
            "CALL getFirstNameById(?)";

    public static final String SQL_SELECT_SINGER_LAST_NAME_BY_ID_WITH_STORED_FUNCTION =
            "SELECT getLastNameById(?)";

    public static final String SQL_SELECT_SINGER_LAST_NAME_BY_ID =
            "SELECT last_name FROM singer WHERE id=:id";

    public static final String SQL_INSERT_SINGER =
            "INSERT INTO singer (first_name,last_name,birth_date,is_death) " +
                    "VALUES (:first_name,:last_name,:birth_date,:is_death)";

    public static final String SQL_UPDATE_SINGER_BY_ID = "UPDATE singer " +
            "SET first_name=:first_name,last_name=:last_name,birth_date=:birth_date,is_death=:is_death " +
            "WHERE id=:id";

    public static final String SQL_DELETE_SINGER = "DELETE FROM singer WHERE id=:id";

    public static final String SQL_SELECT_ALBUM_BY_SINGER_ID =
            "SELECT * FROM album WHERE singer_id=:singer_id";

    public static final String SQL_INSERT_ALBUM =
            "INSERT INTO album (singer_id,title,release_date) " +
                    "VALUES (:singer_id,:title,:release_date)";

    public static final String SQL_DELETE_ALBUM_BY_SINGER_ID =
            "DELETE FROM album WHERE singer_id=:singer_id";
}
