package jun.prospring5.ch16.repository;


import jun.prospring5.ch16.entity.Singer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SingerRepository
        extends PagingAndSortingRepository<Singer, Long> {
}
