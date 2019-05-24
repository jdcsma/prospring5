package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import jun.prospring5.ch6.dao.support.SingerQuerySupport;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;

public class SingerInserter extends SqlUpdate {

    public SingerInserter(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_INSERT_SINGER);
        SingerQuerySupport.declareUpdateParameters(this, true);
    }
}
