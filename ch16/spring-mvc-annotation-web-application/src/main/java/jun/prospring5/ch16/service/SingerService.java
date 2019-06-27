package jun.prospring5.ch16.service;

import jun.prospring5.ch16.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
