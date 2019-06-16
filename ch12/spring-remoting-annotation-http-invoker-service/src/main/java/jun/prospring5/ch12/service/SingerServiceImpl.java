package jun.prospring5.ch12.service;

import jun.prospring5.ch12.common.entity.Singer;
import jun.prospring5.ch12.repository.SingerRepository;
import jun.prospring5.ch12.common.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("singerService")
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Singer> findAll() {
        return newList(singerRepository.findAll());
    }

    @Override
    public List<Singer> findByFirstName(String firstName) {
        return newList(singerRepository.findByFirstName(firstName));
    }

    @Override
    public Singer findById(long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public Singer save(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public void delete(Singer singer) {
        singerRepository.delete(singer);
    }

    private static List<Singer> newList(Iterable<Singer> iterable) {
        List<Singer> singers = new ArrayList<>();
        iterable.forEach(singers::add);
        return singers;
    }
}
