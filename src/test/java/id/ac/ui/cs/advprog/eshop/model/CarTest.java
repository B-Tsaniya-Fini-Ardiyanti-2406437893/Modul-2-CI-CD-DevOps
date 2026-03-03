package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private Car car;
    private final String VALID_ID = "car-123-abc";
    private final String VALID_NAME = "Toyota Avanza";
    private final String VALID_COLOR = "Silver";
    private final int VALID_QUANTITY = 10;

    @BeforeEach
    void setUp() {
        car = new Car();
    }

    @Test
    void testSetAndGetCarId() {
        car.setCarId(VALID_ID);
        assertEquals(VALID_ID, car.getCarId());
    }

    @Test
    void testSetAndGetCarName() {
        car.setCarName(VALID_NAME);
        assertEquals(VALID_NAME, car.getCarName());
    }

    @Test
    void testSetAndGetCarColor() {
        car.setCarColor(VALID_COLOR);
        assertEquals(VALID_COLOR, car.getCarColor());
    }

    @Test
    void testSetAndGetCarQuantity() {
        car.setCarQuantity(VALID_QUANTITY);
        assertEquals(VALID_QUANTITY, car.getCarQuantity());
    }

    @Test
    void testCarObjectWithAllProperties() {
        // Given
        car.setCarId(VALID_ID);
        car.setCarName(VALID_NAME);
        car.setCarColor(VALID_COLOR);
        car.setCarQuantity(VALID_QUANTITY);

        // Then
        assertAll("Car properties",
                () -> assertEquals(VALID_ID, car.getCarId()),
                () -> assertEquals(VALID_NAME, car.getCarName()),
                () -> assertEquals(VALID_COLOR, car.getCarColor()),
                () -> assertEquals(VALID_QUANTITY, car.getCarQuantity())
        );
    }

    @Test
    void testCarObjectEquality() {
        // Given
        Car car1 = createCar(VALID_ID, VALID_NAME, VALID_COLOR, VALID_QUANTITY);
        Car car2 = createCar(VALID_ID, VALID_NAME, VALID_COLOR, VALID_QUANTITY);
        Car car3 = createCar("car-456", "Honda Civic", "Black", 5);

        // Then
        assertEquals(car1.getCarId(), car2.getCarId());
        assertEquals(car1.getCarName(), car2.getCarName());
        assertEquals(car1.getCarColor(), car2.getCarColor());
        assertEquals(car1.getCarQuantity(), car2.getCarQuantity());

        assertNotEquals(car1.getCarId(), car3.getCarId());
    }

    @Test
    void testDefaultValues() {
        assertAll("Default values",
                () -> assertNull(car.getCarId()),
                () -> assertNull(car.getCarName()),
                () -> assertNull(car.getCarColor()),
                () -> assertEquals(0, car.getCarQuantity())
        );
    }

    @Test
    void testUpdateCarProperties() {
        // Given
        car.setCarId(VALID_ID);
        car.setCarName(VALID_NAME);
        car.setCarColor(VALID_COLOR);
        car.setCarQuantity(VALID_QUANTITY);

        // When
        String newName = "Toyota Avanza Updated";
        int newQuantity = 15;
        car.setCarName(newName);
        car.setCarQuantity(newQuantity);

        // Then
        assertAll("Updated properties",
                () -> assertEquals(VALID_ID, car.getCarId(), "ID should not change"),
                () -> assertEquals(newName, car.getCarName(), "Name should be updated"),
                () -> assertEquals(VALID_COLOR, car.getCarColor(), "Color should not change"),
                () -> assertEquals(newQuantity, car.getCarQuantity(), "Quantity should be updated")
        );
    }

    private Car createCar(String id, String name, String color, int quantity) {
        Car car = new Car();
        car.setCarId(id);
        car.setCarName(name);
        car.setCarColor(color);
        car.setCarQuantity(quantity);
        return car;
    }
}