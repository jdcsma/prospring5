package jun.prospring5.ch8.service;

import jun.prospring5.ch8.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findAllWithDetails();

    Singer findById(Long id);

    List<Singer> findByFirstName(String firstName);

    Singer save(Singer singer);

    void delete(Singer singer);
}
