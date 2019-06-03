package jun.prospring5.ch8.service.implementation;

import jun.prospring5.ch8.entity.Singer;
import jun.prospring5.ch8.repository.SingerRepository;
import jun.prospring5.ch8.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl
        implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        return newArrayList(singerRepository.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAllWithDetails() {
        return entityManager.createNamedQuery(
                Singer.FIND_ALL_WITH_DETAILS, Singer.class)
                .getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        Optional<Singer> singer = singerRepository.findById(id);
        return singer.isPresent() ? singer.get() : null;
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void delete(Singer singer) {
        singerRepository.delete(singer);
    }

    private static <T> List<T> newArrayList(Iterable<T> iterable) {
        List<T> singers = new ArrayList<>();
        iterable.forEach(singers::add);
        return singers;
    }
}
