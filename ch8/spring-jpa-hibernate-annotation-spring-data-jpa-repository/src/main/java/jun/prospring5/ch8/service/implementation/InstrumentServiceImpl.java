package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Instrument;
import jun.prospring5.ch8.entity.Instrument_;
import jun.prospring5.ch8.service.InstrumentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("instrumentService")
@Repository
@Transactional
public class InstrumentServiceImpl
        implements InstrumentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Instrument save(Instrument instrument) {
        entityManager.persist(instrument);
        return instrument;
    }
}
