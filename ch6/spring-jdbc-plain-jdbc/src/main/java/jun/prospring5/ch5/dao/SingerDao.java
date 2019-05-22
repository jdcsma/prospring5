package jun.prospring5.ch5.dao;

import jun.prospring5.ch5.entity.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();

    Singer findByFirstName(String firstName);

    String findFirstNameById(Long id);

    String findLastNameById(Long id);

    void insert(Singer singer);

    void update(Singer singer);

    void delete(Long id);

    List<Singer> findAllWithDetail();

    void insertWithDetail(Singer singer);

}
