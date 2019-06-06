package jun.prospring5.ch9.service.implementation;

import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.repository.SingerRepository;
import jun.prospring5.ch9.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    @Override
    public List<Singer> findAll() {
        List<Singer> singers = new ArrayList<>();
        singerRepository.findAll().forEach(singers::add);
        return singers;
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        Optional<Singer> singer = singerRepository.findById(id);
        return singer.isPresent() ? singer.get() : null;
    }

    @Transactional(propagation = Propagation.NEVER)
    @Override
    public long countAllSingers() {
        return singerRepository.countAllSingers();
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void delete(Singer singer) {
        singerRepository.delete(singer);
    }
}
