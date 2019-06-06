package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Instrument;
import jun.prospring5.ch8.entity.Instrument_;
import jun.prospring5.ch8.service.InstrumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Transactional
@Service("instrumentService")
public class InstrumentServiceImpl
        extends AbstractService
        implements InstrumentService {

    @Override
    public List<Instrument> findAllByCriteriaQuery() {

        CriteriaBuilder criteriaBuilder =
                getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Instrument> criteriaQuery =
                criteriaBuilder.createQuery(Instrument.class);
        Root<Instrument> root = criteriaQuery.from(Instrument.class);
        criteriaQuery.select(root);

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public List<Instrument> findByCriteriaQuery(String instrumentId) {

        CriteriaBuilder criteriaBuilder =
                getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Instrument> criteriaQuery =
                criteriaBuilder.createQuery(Instrument.class);
        Root<Instrument> root = criteriaQuery.from(Instrument.class);
        criteriaQuery.select(root);

        Predicate criteria = criteriaBuilder.conjunction();
        Predicate p = criteriaBuilder.equal(
                root.get(Instrument_.INSTRUMENT_ID), instrumentId);
        criteria = criteriaBuilder.and(criteria, p);

        criteriaQuery.where(criteria);

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }

    @Override
    public Instrument save(Instrument instrument) {
        getEntityManager().persist(instrument);
        return instrument;
    }
}
