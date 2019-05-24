package jun.prospring5.ch6.dao.support;

import jun.prospring5.ch6.entity.Singer;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public final class SingerQuerySupport {

    public static Map<String, Object> buildInsertNamedParameters(Singer singer) {
        return buildInsertNamedParameters(singer, false, null);
    }

    public static Map<String, Object> buildInsertNamedParameters(
            Singer singer, boolean includeSingerId) {
        return buildInsertNamedParameters(singer, includeSingerId, null);
    }

    public static Map<String, Object> buildInsertNamedParameters(
            Singer singer, boolean includeSingerId, Map<String, Object> namedParameters) {
        Map<String, Object> map = namedParameters;
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("first_name", singer.getFirstName());
        map.put("last_name", singer.getLastName());
        map.put("birth_date", singer.getBirthDate());
        map.put("is_death", singer.isDeath());
        if (includeSingerId) {
            map.put("id", singer.getId());
        }
        return map;
    }

    public static void declareUpdateParameters(
            SqlUpdate update, boolean isSetGeneratedKey) {

        update.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        update.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        update.declareParameter(new SqlParameter("birth_date", Types.DATE));
        update.declareParameter(new SqlParameter("is_death", Types.TINYINT));

        if (isSetGeneratedKey) {
            update.setGeneratedKeysColumnNames("id");
            update.setReturnGeneratedKeys(true);
        }
    }
}
