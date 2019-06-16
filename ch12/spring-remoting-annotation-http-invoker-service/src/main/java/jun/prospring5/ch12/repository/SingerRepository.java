package jun.prospring5.ch12.repository;

import jun.prospring5.ch12.common.entity.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SingerRepository extends CrudRepository<Singer, Long> {

    List<Singer> findByFirstName(String firstName);
}
