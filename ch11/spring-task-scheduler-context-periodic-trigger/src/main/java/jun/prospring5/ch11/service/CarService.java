package jun.prospring5.ch11.service;

import jun.prospring5.ch11.entity.Car;

import java.util.List;

public interface CarService {

    List<Car> findAll();

    Car save(Car car);

    void prepareUpdateCarAgeJob();

    void updateCarAgeJob();

    boolean isDone();
}
