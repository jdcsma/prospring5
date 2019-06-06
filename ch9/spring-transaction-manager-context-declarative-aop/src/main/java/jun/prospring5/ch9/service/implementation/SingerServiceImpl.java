package jun.prospring5.ch9.service.implementation;

import jun.prospring5.ch9.entity.Singer;
import jun.prospring5.ch9.repository.SingerRepository;
import jun.prospring5.ch9.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("singerService")
public class SingerServiceImpl
        implements SingerService {

    private SingerRepository singerRepository;

    @Autowired
    public void setSingerRepository(SingerRepository singerRepository) {
        this.singerRepository = singerRepository;
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> singers = new ArrayList<>();
        singerRepository.findAll().forEach(singers::add);
        return singers;
    }

    @Override
    public Singer findById(Long id) {
        Optional<Singer> singer = singerRepository.findById(id);
        return singer.isPresent() ? singer.get() : null;
    }

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
