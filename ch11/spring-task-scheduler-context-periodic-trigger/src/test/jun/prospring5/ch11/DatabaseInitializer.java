package jun.prospring5.ch11;

import jun.prospring5.ch11.entity.Car;
import jun.prospring5.ch11.repository.CarRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DatabaseInitializer {

    private static Logger logger =
            LoggerFactory.getLogger(DatabaseInitializer.class);

    @Autowired
    private CarRepository carRepository;

    private DateTimeFormatter formatter =
            DateTimeFormat.forPattern("yyyy-MM-dd");

    @PostConstruct
    public void initDB() {

        logger.info("Starting database initialization ...");

        addCarToRepository("GRAVITY-0405", "Ford", "2006-09-12");
        addCarToRepository("GLARITY-0432", "Toyota", "2003-09-09");
        addCarToRepository("ROSIE-0402", "Toyota", "2017-04-16");

        logger.info("Database initialization finished.");
    }

    private void addCarToRepository(String licensePlate, String manufacturer, String manufactureDate) {
        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setManufacturer(manufacturer);
        car.setManufactureDate(DateTime.parse(manufactureDate, formatter));
        carRepository.save(car);
    }
}
