package jun.prospring5.ch6.dao.implementation;

import jun.prospring5.ch6.constant.DaoSqlStatement;
import jun.prospring5.ch6.dao.SingerDao;
import jun.prospring5.ch6.dao.implementation.queries.*;
import jun.prospring5.ch6.dao.mapper.JdbcSingerResultSetExtractor;
import jun.prospring5.ch6.dao.support.AlbumQuerySupport;
import jun.prospring5.ch6.dao.support.SingerQuerySupport;
import jun.prospring5.ch6.entity.Album;
import jun.prospring5.ch6.entity.Singer;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao, InitializingBean {

    private DataSource dataSource;

    private SingerSelectorAll singerSelectorAll;
    private SingerSelectorById singerSelectorById;
    private SingerSelectorFullNameById singerSelectorFullNameById;
    private SingerInserter singerInserter;
    private SingerUpdater singerUpdater;
    private SingerDeleter singerDeleter;

    private AlbumSelectorAll albumSelectorAll;
    private AlbumDeleter albumDeleter;

    private SingerStoredProcedureFirstNameById singerStoredProcedureFirstNameById;
    private SingerStoredFunctionLastNameById singerStoredFunctionLastNameById;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

        singerSelectorAll = new SingerSelectorAll(dataSource);
        singerSelectorById = new SingerSelectorById(dataSource);
        singerSelectorFullNameById = new SingerSelectorFullNameById(dataSource);
        singerInserter = new SingerInserter(dataSource);
        singerUpdater = new SingerUpdater(dataSource);
        singerDeleter = new SingerDeleter(dataSource);

        albumSelectorAll = new AlbumSelectorAll(dataSource);
        albumDeleter = new AlbumDeleter(dataSource);

        singerStoredProcedureFirstNameById =
                new SingerStoredProcedureFirstNameById(dataSource);
        singerStoredFunctionLastNameById =
                new SingerStoredFunctionLastNameById(dataSource);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Null dataSource on JdbcSingerDao");
        }
    }

    @Override
    public List<Singer> findAll() {
        return singerSelectorAll.execute();
    }

    @Override
    public List<Singer> findAllWithDetail() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query(
                DaoSqlStatement.SQL_SELECT_SINGER_WITH_DETAIL_BY_ID,
                new JdbcSingerResultSetExtractor());
    }

    @Override
    public List<Album> findAlbums(Long singerId) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("singer_id", singerId);
        return albumSelectorAll.executeByNamedParam(namedParameters);
    }

    @Override
    public Singer findById(Long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return singerSelectorById.findObjectByNamedParam(namedParameters);
    }

    @Override
    public String findFullNameById(Long id) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("id", id);
        return singerSelectorFullNameById.findObjectByNamedParam(namedParameters);
    }

    @Override
    public String findFirstNameById(Long id) {
        List<String> result = singerStoredProcedureFirstNameById.execute(id);
        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public String findLastNameById(Long id) {
        List<String> result = singerStoredFunctionLastNameById.execute(id);
        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public void insert(Singer singer) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        singerInserter.updateByNamedParam(
                SingerQuerySupport.buildInsertNamedParameters(singer), keyHolder);
        singer.setId(keyHolder.getKey().longValue());

        if (singer.getAlbums() != null && singer.getAlbums().size() > 0) {

            AlbumInserterBatch albumInserterBatch =
                    new AlbumInserterBatch(dataSource);

            for (Album album : singer.getAlbums()) {
                album.setSingerId(singer.getId());
                albumInserterBatch.updateByNamedParam(
                        AlbumQuerySupport.buildInsertNamedParameters(album));
            }

            albumInserterBatch.flush();
        }
    }

    @Override
    public void update(Singer singer) {
        singerUpdater.updateByNamedParam(
                SingerQuerySupport.buildInsertNamedParameters(
                        singer, true));
    }

    @Override
    public void delete(Long id) {
        Map<String, Object> albumNamedParameters = new HashMap<>();
        albumNamedParameters.put("singer_id", id);
        albumDeleter.updateByNamedParam(albumNamedParameters);
        Map<String, Object> singerNamedParameters = new HashMap<>();
        singerNamedParameters.put("id", id);
        singerDeleter.updateByNamedParam(singerNamedParameters);
    }
}
