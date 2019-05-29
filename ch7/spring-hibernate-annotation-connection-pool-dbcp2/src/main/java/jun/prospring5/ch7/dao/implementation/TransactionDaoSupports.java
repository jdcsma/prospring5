package jun.prospring5.ch7.dao.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.annotation.Resource;

public abstract class TransactionDaoSupports {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.openSession();
    }

    protected Object saveToDB(Object entity) {
        execute(entity, (e, s) -> s.saveOrUpdate(e));
        return entity;
    }

    protected void deleteFromDB(Object entity) {
        execute(entity, (e, s) -> s.delete(e));
    }

    @FunctionalInterface
    private interface Command {
        void execute(Object entity, Session session);
    }

    private void execute(Object entity, Command command) {

        try (Session session = getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                command.execute(entity, session);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw e;
            }
        }
    }
}
