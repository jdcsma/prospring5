package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Instrument;
import jun.prospring5.ch8.service.InstrumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Service("instrumentService")
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
