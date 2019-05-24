package jun.prospring5.ch6.dao.implementation.queries;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import jun.prospring5.ch6.dao.support.AlbumQuerySupport;
import org.springframework.jdbc.object.BatchSqlUpdate;

import javax.sql.DataSource;

public class AlbumInserterBatch extends BatchSqlUpdate {

    private static final int MAX_BATCH_SIZE = 10;

    public AlbumInserterBatch(DataSource dataSource) {
        super(dataSource, DaoSqlStatement.SQL_INSERT_ALBUM);
        AlbumQuerySupport.declareUpdateParameters(this, false);
        super.setBatchSize(MAX_BATCH_SIZE);
    }
}
