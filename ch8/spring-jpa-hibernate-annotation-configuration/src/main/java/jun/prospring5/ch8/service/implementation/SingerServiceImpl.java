package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.service.SingerService;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl
        extends AbstractService
        implements SingerService {

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return getEntityManager()
                .createNamedQuery(Singer.FIND_ALL, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithDetails() {
        return getEntityManager().createNamedQuery(
                Singer.FIND_ALL_WITH_DETAILS, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        throw new NotImplementedException("findAllByNativeQuery");
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return getEntityManager().createNamedQuery(
                Singer.FIND_ONE_BY_ID, Singer.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            getEntityManager().persist(singer);
        } else {
            getEntityManager().merge(singer);
        }
        return singer;
    }

    @Override
    public void delete(Singer singer) {
        boolean contains = getEntityManager().contains(singer);
        if (!contains) {
            singer = getEntityManager().merge(singer);
        }
        getEntityManager().remove(singer);
    }
}
