package jun.prospring5.ch7.dao;

import jun.prospring5.ch7.entity.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();

    List<Singer> findAllWithAlbum();

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
