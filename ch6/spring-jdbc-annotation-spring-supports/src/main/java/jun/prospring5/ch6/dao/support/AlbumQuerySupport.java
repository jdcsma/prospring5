package jun.prospring5.ch6.dao.support;

import jun.prospring5.ch6.entity.Album;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public final class AlbumQuerySupport {

    public static Map<String, Object> buildInsertNamedParameters(Album album) {
        return buildInsertNamedParameters(album, null);
    }

    public static Map<String, Object> buildInsertNamedParameters(
            Album album, Map<String, Object> namedParameters) {
        Map<String, Object> map = namedParameters;
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("singer_id", album.getSingerId());
        map.put("title", album.getTitle());
        map.put("release_date", album.getReleaseDate());
        return map;
    }

    public static void declareUpdateParameters(
            SqlUpdate update, boolean isReturnGeneratedKey) {
        update.declareParameter(new SqlParameter("singer_id", Types.INTEGER));
        update.declareParameter(new SqlParameter("title", Types.VARCHAR));
        update.declareParameter(new SqlParameter("release_date", Types.DATE));

        if (isReturnGeneratedKey) {
            update.setGeneratedKeysColumnNames("id");
            update.setReturnGeneratedKeys(true);
        }
    }
}
