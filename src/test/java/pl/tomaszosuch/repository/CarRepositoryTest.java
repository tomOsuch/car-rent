package pl.tomaszosuch.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.enums.State;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void testCarFindAll() {
        //Given
        Car car = new Car("Test", LocalDate.of(2022, 01, 01), State.AVAILABLE);
        carRepository.save(car);
        Long id = car.getId();
        //When
        List<Car> resultFindAll = carRepository.findAll();
        //Then
        assertEquals(1, resultFindAll.size());
        //CleanUp
        carRepository.deleteById(id);
        carRepository.deleteAll();
    }

    @Test
    public void testCarFindById() {
        //Given
        Car car = new Car("Test", LocalDate.of(2022, 01, 01), State.AVAILABLE);
        carRepository.save(car);
        Long id = car.getId();
        //When
        Optional<Car> resultCarFindById = carRepository.findById(id);
        //Then
        assertTrue(resultCarFindById.isPresent());
        //CleanUp
        carRepository.deleteById(id);
        carRepository.deleteAll();
    }

    @Test
    public void testCarDelete() {
        //Given
        Car car = new Car("Test", LocalDate.of(2022, 01, 01), State.AVAILABLE);
        carRepository.save(car);
        Long id = car.getId();
        //When
        carRepository.delete(car);
        Optional<Car> resultFind = carRepository.findById(id);
        //Then
        assertTrue(resultFind.isEmpty());
        //CleanUp
        carRepository.deleteAll();
    }

    @Test
    public void testSaveCar() {
        //Given
        Car car = new Car("Test", LocalDate.of(2022, 01, 01), State.AVAILABLE);
        carRepository.save(car);
        //When
        Car resultSaveCar = carRepository.save(car);
        //Then
        assertEquals(car.getId(), resultSaveCar.getId());
        assertEquals(car.getRegistrationNumber(), resultSaveCar.getRegistrationNumber());
        assertEquals(car.getProductionYear(), resultSaveCar.getProductionYear());
        assertEquals(car.getState(), resultSaveCar.getState());
        //CleanUp
        carRepository.deleteAll();
    }
}
