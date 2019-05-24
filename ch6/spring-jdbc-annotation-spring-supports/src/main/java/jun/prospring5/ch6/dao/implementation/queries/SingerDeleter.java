package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class SingerDeleter extends SqlUpdate {

    public SingerDeleter(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_DELETE_SINGER);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
