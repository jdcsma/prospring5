package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.entity.Singer_;
import jun.prospring5.ch8.service.SingerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Service("singerService")
public class SingerServiceImpl
        extends AbstractService
        implements SingerService {

    private static final String FIND_ALL_SINGER_NATIVE_QUERY =
            "select id,first_name,last_name,birth_date,version from singer";

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return getEntityManager()
                .createNamedQuery(Singer.FIND_ALL, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithDetails() {
        return getEntityManager().createNamedQuery(
                Singer.FIND_ALL_WITH_DETAILS, Singer.class)
                .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        return (List<Singer>) getEntityManager()
                .createNativeQuery(FIND_ALL_SINGER_NATIVE_QUERY,
                        "singerResultSetMapping")
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findByCriteriaQuery(
            String firstName, String lastName) {
        CriteriaBuilder criteriaBuilder =
                getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery =
                criteriaBuilder.createQuery(Singer.class);
        Root<Singer> singerRoot = criteriaQuery.from(Singer.class);
        singerRoot.fetch(Singer_.albums, JoinType.LEFT);
        singerRoot.fetch(Singer_.instruments, JoinType.LEFT);

        criteriaQuery.select(singerRoot).distinct(true);

        Predicate criteria = criteriaBuilder.conjunction();

        if (StringUtils.isNotEmpty(firstName)) {
            Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.firstName), firstName);
            criteria = criteriaBuilder.and(criteria, p);
        }

        if (StringUtils.isNotEmpty(lastName)) {
            Predicate p = criteriaBuilder.equal(singerRoot.get(Singer_.lastName), lastName);
            criteria = criteriaBuilder.and(criteria, p);
        }

        criteriaQuery.where(criteria);

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return getEntityManager().createNamedQuery(
                Singer.FIND_ONE_BY_ID, Singer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            getEntityManager().persist(singer);
        } else {
            getEntityManager().merge(singer);
        }
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        boolean contains = getEntityManager().contains(singer);
        if (!contains) {
            singer = getEntityManager().merge(singer);
        }
        getEntityManager().remove(singer);
    }
}
