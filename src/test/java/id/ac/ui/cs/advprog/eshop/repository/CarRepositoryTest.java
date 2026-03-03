package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryTest {

    private CarRepositoryImpl carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepositoryImpl();
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarName("Toyota Avanza");
        car.setCarColor("Silver");
        car.setCarQuantity(10);

        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext()); // Cek apakah ada isinya
        Car savedCar = carIterator.next();

        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals("Toyota Avanza", savedCar.getCarName());
        assertEquals("Silver", savedCar.getCarColor());
        assertEquals(10, savedCar.getCarQuantity());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarName("Honda Civic");
        carRepository.create(car);

        Car foundCar = carRepository.findById(car.getCarId());
        assertNotNull(foundCar);
        assertEquals(car.getCarId(), foundCar.getCarId());

        Car notFoundCar = carRepository.findById("id-ngasal-bos");
        assertNull(notFoundCar);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarName("Suzuki Ertiga");
        car.setCarColor("Putih");
        car.setCarQuantity(5);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Suzuki Ertiga Sport");
        updatedCar.setCarColor("Hitam");
        updatedCar.setCarQuantity(10);

        Car result = carRepository.update(car.getCarId(), updatedCar);

        assertNotNull(result);
        assertEquals("Suzuki Ertiga Sport", result.getCarName());
        assertEquals("Hitam", result.getCarColor());
        assertEquals(10, result.getCarQuantity());
    }

    @Test
    void testUpdateCarNotFound() {
        Car updatedCar = new Car();
        Car result = carRepository.update("id-ghaib", updatedCar);
        assertNull(result);
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarName("Wuling Air EV");
        carRepository.create(car);

        carRepository.delete(car.getCarId());

        Car foundCar = carRepository.findById(car.getCarId());
        assertNull(foundCar);
    }
}