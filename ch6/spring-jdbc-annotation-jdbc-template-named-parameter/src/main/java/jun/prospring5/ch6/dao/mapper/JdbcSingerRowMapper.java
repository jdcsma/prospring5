package jun.prospring5.ch6.dao.mapper;

import jun.prospring5.ch6.entity.Singer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcSingerRowMapper implements RowMapper<Singer> {

    @Override
    public Singer mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        Singer singer = new Singer();
        singer.setId(rs.getLong("id"));
        singer.setFirstName(rs.getString("first_name"));
        singer.setLastName(rs.getString("last_name"));
        singer.setBirthDate(rs.getDate("birth_date"));
        singer.setDeath(rs.getBoolean("is_death"));
        return singer;
    }
}
