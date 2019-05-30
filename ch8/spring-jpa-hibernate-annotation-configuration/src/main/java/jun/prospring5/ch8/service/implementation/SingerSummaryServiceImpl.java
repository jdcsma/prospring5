package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.service.SingerSummaryService;
import jun.prospring5.ch8.view.SingerSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl
        implements SingerSummaryService {

    private static Logger logger =
            LoggerFactory.getLogger(SingerSummaryServiceImpl.class);

    private static final String FIND_ALL_SINGER_SUMMARY_UNTYPE =
            "select s.firstName,s.lastName,a.title from Singer s " +
                    "left join s.albums a " +
                    "where a.releaseDate=(select max(a2.releaseDate) " +
                    "from Album a2 where a2.singer.id=s.id)";
    private static final String FIND_ALL_SINGER_SUMMARY_POJO =
            "select new jun.prospring5.ch8.view.SingerSummary(" +
                    "s.firstName,s.lastName,a.title) from Singer s " +
                    "left join s.albums a " +
                    "where a.releaseDate=(select max(a2.releaseDate) " +
                    "from Album a2 where a2.singer.id=s.id)";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void displayAllSingerSummary() {
        List result = entityManager
                .createQuery(FIND_ALL_SINGER_SUMMARY_UNTYPE)
                .getResultList();
        int count = 0;
        Iterator i = result.iterator();
        while (i.hasNext()) {
            Object[] values = (Object[]) i.next();
            logger.info(++count + ": " +
                    values[0] + ", " +
                    values[1] + ", " +
                    values[2]);
        }
    }

    @Override
    public List<SingerSummary> findAllSingerSummery() {
        return entityManager
                .createQuery(FIND_ALL_SINGER_SUMMARY_POJO,
                SingerSummary.class).getResultList();
    }
}
