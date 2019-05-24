package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SingerSelectorFirstNameById extends MappingSqlQuery<String> {

    public SingerSelectorFirstNameById(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_SELECT_SINGER_FIRST_NAME_BY_ID);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected String mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("first_name");
    }
}
