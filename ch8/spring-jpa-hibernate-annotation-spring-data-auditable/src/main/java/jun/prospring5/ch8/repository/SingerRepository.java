package jun.prospring5.ch8.repository;

import jun.prospring5.ch8.entity.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository
        extends CrudRepository<Singer, Long> {
}
