package jun.prospring5.ch11.repository;

import jun.prospring5.ch11.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository
        extends CrudRepository<Car, Long> {
}
