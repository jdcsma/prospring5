package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

public class AlbumDeleter extends SqlUpdate {

    public AlbumDeleter(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_DELETE_ALBUM_BY_SINGER_ID);
        super.declareParameter(new SqlParameter("singer_id", Types.INTEGER));
    }
}
