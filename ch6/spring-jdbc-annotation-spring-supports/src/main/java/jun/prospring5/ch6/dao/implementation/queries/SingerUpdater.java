package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class SingerUpdater extends SqlUpdate {

    public SingerUpdater(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_UPDATE_SINGER_BY_ID);
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birth_date", Types.DATE));
        super.declareParameter(new SqlParameter("is_death", Types.TINYINT));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
