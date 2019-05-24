package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import jun.prospring5.ch6.dao.factory.AlbumFactory;
import jun.prospring5.ch6.entity.Album;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AlbumSelectorAll extends MappingSqlQuery<Album> {

    public AlbumSelectorAll(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_SELECT_ALBUM_BY_SINGER_ID);
        super.declareParameter(new SqlParameter("singer_id", Types.INTEGER));
    }

    @Override
    protected Album mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        return AlbumFactory.newAlbumFromResultSet(rs);
    }
}
