package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Instrument;
import jun.prospring5.ch8.service.InstrumentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("instrumentService")
public class InstrumentServiceImpl
        extends AbstractService
        implements InstrumentService {

    @Override
    public Instrument save(Instrument instrument) {
        getEntityManager().persist(instrument);
        return instrument;
    }
}
