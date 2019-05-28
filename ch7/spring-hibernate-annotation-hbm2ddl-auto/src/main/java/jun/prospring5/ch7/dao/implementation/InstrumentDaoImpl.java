package jun.prospring5.ch7.dao.implementation;

import jun.prospring5.ch7.dao.InstrumentDao;
import jun.prospring5.ch7.entity.Instrument;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Instrument instrument) {
        sessionFactory.getCurrentSession().saveOrUpdate(instrument);
    }
}
