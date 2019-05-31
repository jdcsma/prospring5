package jun.prospring5.ch8.service.implementation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractService {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
