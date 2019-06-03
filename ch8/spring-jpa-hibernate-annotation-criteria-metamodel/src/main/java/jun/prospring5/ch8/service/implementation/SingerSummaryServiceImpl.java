package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Album;
import jun.prospring5.ch8.entity.Album_;
import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.entity.Singer_;
import jun.prospring5.ch8.service.SingerSummaryService;
import jun.prospring5.ch8.view.SingerSummary;
import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service("singerSummaryService")
@Repository
@Transactional
public class SingerSummaryServiceImpl
        extends AbstractService
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

    @Override
    public void displayAllSingerSummary() {
        List result = getEntityManager()
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

    @SuppressWarnings("unchecked")
    @Override
    public void displayAllSingerSummaryCriteriaQuery() {

        CriteriaBuilder criteriaBuilder =
                getEntityManager().getCriteriaBuilder();
        CriteriaQuery<?> criteriaQuery =
                criteriaBuilder.createQuery();

        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        Subquery<Date> subQuery = criteriaQuery.subquery(Date.class);
        Root albumRoot = subQuery.from(Album.class);

        subQuery.select(criteriaBuilder.max(
                albumRoot.get(Album_.releaseDate)));

        Predicate criteria = criteriaBuilder.conjunction();
        Predicate p = criteriaBuilder.equal(
                albumRoot.get(Album_.singer).get(Singer_.id),
                singerRoot.get(Singer_.id));
        criteria = criteriaBuilder.and(criteria, p);
        subQuery.where(criteria);

        criteria = criteriaBuilder.conjunction();
        SetJoin<Singer, Album> join =
                singerRoot.join(Singer_.albums, JoinType.LEFT);
        p = criteriaBuilder.equal(
                join.get(Album_.releaseDate),
                subQuery);
        criteria = criteriaBuilder.and(criteria, p);

        // Build projection with multiselect.

        criteriaQuery.where(criteria).multiselect(
                singerRoot.get(Singer_.firstName),
                singerRoot.get(Singer_.lastName),
                join.get(Album_.title));

        List<?> result = getEntityManager()
                .createQuery(criteriaQuery)
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
        return getEntityManager()
                .createQuery(FIND_ALL_SINGER_SUMMARY_POJO,
                        SingerSummary.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SingerSummary>
    findAllSingerSummeryCriteriaQuery() {

        CriteriaBuilder criteriaBuilder =
                getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SingerSummary> criteriaQuery =
                criteriaBuilder.createQuery(SingerSummary.class);

        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        Subquery<Date> subQuery = criteriaQuery.subquery(Date.class);
        Root albumRoot = subQuery.from(Album.class);

        subQuery.select(criteriaBuilder.max(
                albumRoot.get(Album_.releaseDate)));

        Predicate criteria = criteriaBuilder.conjunction();
        Predicate p = criteriaBuilder.equal(
                albumRoot.get(Album_.singer).get(Singer_.id),
                singerRoot.get(Singer_.id));
        criteria = criteriaBuilder.and(criteria, p);
        subQuery.where(criteria);

        criteria = criteriaBuilder.conjunction();
        SetJoin<Singer, Album> join =
                singerRoot.join(Singer_.albums, JoinType.LEFT);
        p = criteriaBuilder.equal(
                join.get(Album_.releaseDate),
                subQuery);
        criteria = criteriaBuilder.and(criteria, p);

        // Build projection with CriteriaBuilder.construct.

        CompoundSelection<SingerSummary> projection =
                criteriaBuilder.construct(SingerSummary.class,
                        singerRoot.get(Singer_.firstName),
                        singerRoot.get(Singer_.lastName),
                        join.get(Album_.title));
        criteriaQuery.where(criteria).select(projection);

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }
}
