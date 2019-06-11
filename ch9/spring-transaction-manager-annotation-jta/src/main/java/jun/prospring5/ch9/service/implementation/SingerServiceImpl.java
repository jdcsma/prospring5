package jun.prospring5.ch9.service.implementation;

import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.entity.SingerBuilder;
import jun.prospring5.ch9.entity.Singer_;
import jun.prospring5.ch9.service.SingerService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sound.midi.Instrument;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
@Service("singerService")
public class SingerServiceImpl
        implements SingerService {

    @PersistenceContext(unitName = "entityManagerFactoryA")
    private EntityManager entityManagerA;

    @PersistenceContext(unitName = "entityManagerFactoryB")
    private EntityManager entityManagerB;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {

        List<Singer> singersA = findAll(entityManagerA);
        List<Singer> singersB = findAll(entityManagerB);

        List<Singer> result = new ArrayList<>();
        singersA.forEach(result::add);
        singersB.forEach(result::add);

        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findById(Long id) {

        Singer singerA = findById(entityManagerA, id);
        Singer singerB = findById(entityManagerB, id);

        List<Singer> result = new ArrayList<>();
        result.add(singerA);
        result.add(singerB);

        return result;
    }

    @Override
    public Singer save(Singer singer) {
        Singer singerB = new SingerBuilder()
                .setFirstName(singer.getFirstName())
                .setLastName(singer.getLastName())
                .setBirthDate(singer.getBirthDate())
                .build();

        if (singer.getId() == null) {
            entityManagerA.persist(singer);
            entityManagerB.persist(singerB);
        } else {
            entityManagerA.merge(singer);
            entityManagerB.merge(singer);
        }

        return singer;
    }

    @Override
    public void delete(Singer singer) {
        deleteById(entityManagerA, singer.getId());
        deleteById(entityManagerB, singer.getId());
    }

    @Override
    public void deleteAll() {
        deleteAll(entityManagerA);
        deleteAll(entityManagerB);
    }

    private List<Singer> findAll(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder =
                entityManager.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery =
                criteriaBuilder.createQuery(Singer.class);
        Root<Singer> root = criteriaQuery.from(Singer.class);

        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }

    private Singer findById(EntityManager entityManager, Long id) {
        CriteriaBuilder criteriaBuilder =
                entityManager.getCriteriaBuilder();
        CriteriaQuery<Singer> criteriaQuery =
                criteriaBuilder.createQuery(Singer.class);
        Root<Singer> root = criteriaQuery.from(Singer.class);

        criteriaQuery.select(root);

        Predicate criteria = criteriaBuilder.conjunction();
        Predicate p = criteriaBuilder.equal(root.get(Singer_.id), id);
        criteria = criteriaBuilder.and(criteria, p);

        criteriaQuery.where(criteria);

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    private void deleteById(EntityManager entityManager, Long id) {
        CriteriaBuilder criteriaBuilder =
                entityManager.getCriteriaBuilder();
        CriteriaDelete<Singer> criteriaDelete =
                criteriaBuilder.createCriteriaDelete(Singer.class);
        Root<Singer> root = criteriaDelete.from(Singer.class);

        criteriaDelete.where(criteriaBuilder.equal(
                root.get(Singer_.id), id));

        entityManager.createQuery(criteriaDelete).executeUpdate();
    }

    private void deleteAll(EntityManager entityManager) {
        CriteriaBuilder criteriaBuilder =
                entityManager.getCriteriaBuilder();
        CriteriaDelete<Singer> criteriaDelete =
                criteriaBuilder.createCriteriaDelete(Singer.class);

        criteriaDelete.from(Singer.class);

        entityManager.createQuery(criteriaDelete).executeUpdate();
    }
}
