package pl.tomaszosuch.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.enums.State;
import pl.tomaszosuch.mapper.CarMapper;
import pl.tomaszosuch.repository.CarBrandRepository;
import pl.tomaszosuch.repository.CarRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarBrandRepository carBrandRepository;

    @Mock
    private CarMapper carMapper;

    @Test
    public void testGetAllCar() {
        //Given
        List<Car> cars = List.of(new Car("TestNumber1234", LocalDate.of(1988, 02, 18), State.AVAILABLE));
        when(carRepository.findAll()).thenReturn(cars);
        //When
        List<Car> resultGetAllCar = carService.getAllCar();
        //Then
        assertEquals(1, resultGetAllCar.size());
    }

    @Test
    public void testGetCarById() {
        //Given
        List<Car> cars = List.of(new Car("TestNumber1234", LocalDate.of(1988, 02, 18), State.AVAILABLE));
        when(carRepository.findById(cars.get(0).getId())).thenReturn(Optional.ofNullable(cars.get(0)));
        //When
        boolean resultFindById = carService.getCarById(cars.get(0).getId()).isPresent();
        //Then
        assertTrue(resultFindById);
    }
}
