package jun.prospring5.ch6.dao;

import jun.prospring5.ch6.entity.Album;
import jun.prospring5.ch6.entity.Singer;

import java.util.List;

public interface SingerDao {

    List<Singer> findAll();

    List<Singer> findAllWithDetail();

    List<Album> findAlbums(Long singerId);

    Singer findById(Long id);

    String findFirstNameById(Long id);

    String findLastNameById(Long id);

    void insert(Singer singer);

    void update(Singer singer);

    void delete(Long id);
}
