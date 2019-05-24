package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SingerSelectorFullNameById extends MappingSqlQuery<String> {

    public SingerSelectorFullNameById(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_SELECT_SINGER_FULL_NAME_BY_ID);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected String mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        StringBuffer builder = new StringBuffer();
        builder.append(rs.getString("first_name"))
                .append(" ")
                .append(rs.getString("last_name"));
        return builder.toString();
    }
}
