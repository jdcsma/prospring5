package jun.prospring5.ch16.service;

import java.util.List;

import jun.prospring5.ch16.entity.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SingerService {

    List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    Page<Singer> findAllByPage(Pageable pageable);
}
