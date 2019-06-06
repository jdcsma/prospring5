package jun.prospring5.ch8.service;

import jun.prospring5.ch8.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);

    Singer findAuditByRevision(Long id, Integer revision);
}
