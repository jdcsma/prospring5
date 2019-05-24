package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import jun.prospring5.ch6.dao.factory.SingerFactory;
import jun.prospring5.ch6.entity.Singer;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingerSelectorAll extends MappingSqlQuery<Singer> {

    public SingerSelectorAll(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_SELECT_SINGER_ALL);
    }

    @Override
    protected Singer mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        return SingerFactory.newSingerFromResultSet(rs);
    }
}
