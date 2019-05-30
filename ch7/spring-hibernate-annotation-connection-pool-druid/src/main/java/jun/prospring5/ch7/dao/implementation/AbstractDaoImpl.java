package jun.prospring5.ch7.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

public abstract class AbstractDaoImpl {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
