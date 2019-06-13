package jun.prospring5.ch11.service.implementation;

import jun.prospring5.ch11.entity.Car;
import jun.prospring5.ch11.repository.CarRepository;
import jun.prospring5.ch11.service.CarService;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {

    private static Logger logger =
            LoggerFactory.getLogger(CarServiceImpl.class);

    private boolean done = false;

    @Autowired
    private CarRepository carRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(cars::add);
        return cars;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void prepareUpdateCarAgeJob() {
        done = false;
    }

    @Override
    public void updateCarAgeJob() {

        List<Car> cars = findAll();

        DateTime currDate = DateTime.now();
        logger.info("Car age update job started ...");

        cars.forEach(car -> {
            int age = Years.yearsBetween(
                    car.getManufactureDate(), currDate).getYears();

            car.setAge(age);

            save(car);

            logger.info("Car age update --> " + car);
        });

        done = true;

        logger.info("Car age update job completed successfully.");
    }

    @Override
    public boolean isDone() {
        return done;
    }
}
