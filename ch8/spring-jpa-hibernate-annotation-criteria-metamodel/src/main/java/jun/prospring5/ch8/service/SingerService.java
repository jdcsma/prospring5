package jun.prospring5.ch8.service;

import jun.prospring5.ch8.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    List<Singer> findAllWithDetails();

    List<Singer> findAllByNativeQuery();

    List<Singer> findByCriteriaQuery(String firstName, String lastName);

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
