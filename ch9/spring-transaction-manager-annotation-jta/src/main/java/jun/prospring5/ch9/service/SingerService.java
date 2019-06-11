package jun.prospring5.ch9.service;

import jun.prospring5.ch9.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);

    void deleteAll();
}
