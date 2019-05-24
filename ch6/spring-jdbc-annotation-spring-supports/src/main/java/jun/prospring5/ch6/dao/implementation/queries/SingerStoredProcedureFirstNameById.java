package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlFunction;

import javax.sql.DataSource;
import java.sql.Types;

public class SingerStoredProcedureFirstNameById extends SqlFunction<String> {

    public SingerStoredProcedureFirstNameById(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.
                SQL_SELECT_SINGER_FIRST_NAME_BY_ID_WITH_STORED_PROCEDURE);
        super.declareParameter(new SqlParameter(Types.INTEGER));
    }
}
