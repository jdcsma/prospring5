package jun.prospring5.ch7.dao.implementation;

import jun.prospring5.ch7.dao.InstrumentDao;
import jun.prospring5.ch7.entity.Instrument;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl
        extends AbstractDaoImpl implements InstrumentDao {

    @Override
    public Instrument save(Instrument instrument) {
        getSession().saveOrUpdate(instrument);
        return instrument;
    }
}
