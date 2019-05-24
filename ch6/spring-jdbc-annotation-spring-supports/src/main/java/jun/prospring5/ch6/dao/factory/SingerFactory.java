package jun.prospring5.ch6.dao.factory;

import jun.prospring5.ch6.entity.Singer;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class SingerFactory {

    public static Singer newSingerFromResultSet(
            ResultSet resultSet) throws SQLException {

        Singer singer = new Singer();

        singer.setId(resultSet.getLong("id"));
        singer.setFirstName(resultSet.getString("first_name"));
        singer.setLastName(resultSet.getString("last_name"));
        singer.setBirthDate(resultSet.getDate("birth_date"));
        singer.setDeath(resultSet.getBoolean("is_death"));

        return singer;
    }
}
