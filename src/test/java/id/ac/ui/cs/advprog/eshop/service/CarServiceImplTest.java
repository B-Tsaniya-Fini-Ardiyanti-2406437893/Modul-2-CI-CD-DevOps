package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carServiceImpl;

    private Car car1;
    private Car car2;
    private List<Car> carList;

    @BeforeEach
    void setUp() {
        car1 = new Car();
        car1.setCarId("car-123");
        car1.setCarName("Toyota Avanza");
        car1.setCarColor("Silver");
        car1.setCarQuantity(10);

        car2 = new Car();
        car2.setCarId("car-456");
        car2.setCarName("Honda Civic");
        car2.setCarColor("Black");
        car2.setCarQuantity(5);

        carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car2);
    }

    @Test
    void testCreate() {
        // Given
        when(carRepository.create(car1)).thenReturn(car1);

        // When
        Car createdCar = carServiceImpl.create(car1);

        // Then
        verify(carRepository, times(1)).create(car1);
        assertEquals(car1, createdCar);
        assertEquals("car-123", createdCar.getCarId());
        assertEquals("Toyota Avanza", createdCar.getCarName());
    }

    @Test
    void testFindAll() {
        // Given
        Iterator<Car> carIterator = carList.iterator();
        when(carRepository.findAll()).thenReturn(carIterator);

        // When
        List<Car> result = carServiceImpl.findAll();

        // Then
        verify(carRepository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals("car-123", result.get(0).getCarId());
        assertEquals("car-456", result.get(1).getCarId());
    }

    @Test
    void testFindAllWhenEmpty() {
        // Given
        Iterator<Car> emptyIterator = new ArrayList<Car>().iterator();
        when(carRepository.findAll()).thenReturn(emptyIterator);

        // When
        List<Car> result = carServiceImpl.findAll();

        // Then
        verify(carRepository, times(1)).findAll();
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindById() {
        // Given
        when(carRepository.findById("car-123")).thenReturn(car1);

        // When
        Car foundCar = carServiceImpl.findById("car-123");

        // Then
        verify(carRepository, times(1)).findById("car-123");
        assertNotNull(foundCar);
        assertEquals("car-123", foundCar.getCarId());
        assertEquals("Toyota Avanza", foundCar.getCarName());
    }

    @Test
    void testFindByIdNotFound() {
        // Given
        when(carRepository.findById("car-999")).thenReturn(null);

        // When
        Car foundCar = carServiceImpl.findById("car-999");

        // Then
        verify(carRepository, times(1)).findById("car-999");
        assertNull(foundCar);
    }

    @Test
    void testDeleteCarById() {
        // Given
        doNothing().when(carRepository).delete("car-123");

        // When
        carServiceImpl.deleteCarById("car-123");

        // Then
        verify(carRepository, times(1)).delete("car-123");
    }

    @Test
    void testCreateWithNullCar() {
        // Given
        when(carRepository.create(null)).thenReturn(null);

        // When
        Car createdCar = carServiceImpl.create(null);

        // Then
        verify(carRepository, times(1)).create(null);
        assertNull(createdCar);
    }

    @Test
    void testDeleteNonExistentCar() {
        // Given
        doNothing().when(carRepository).delete("car-999");

        // When
        carServiceImpl.deleteCarById("car-999");

        // Then
        verify(carRepository, times(1)).delete("car-999");
    }
}