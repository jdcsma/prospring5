package jun.prospring5.ch9.repository;

import jun.prospring5.ch9.entity.Singer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository
        extends CrudRepository<Singer, Long> {

    @Query("select count(s) from Singer s")
    long countAllSingers();
}
