package pl.tomaszosuch.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.dto.CarDto;
import pl.tomaszosuch.enums.State;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarMapperTest {

    @Autowired
    private CarMapper carMapper;

    @Test
    public void testMapToCar() {
        //Given
        CarDto carDto = new CarDto(1L, 1L, "registration number", LocalDate.now(), State.AVAILABLE);
        //When
        Car car = carMapper.mapToCar(carDto);
        //Then
        assertEquals(carDto.getRegistrationNumber(), car.getRegistrationNumber());
        assertEquals(carDto.getProductionYear(), car.getProductionYear());
        assertEquals(carDto.getState(), car.getState());
    }

    @Test
    public void testMapToCarDto() {
        //Given
        Rent rent = new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now());
        CarBrand carBrand = new CarBrand(1L, "brandName", LocalDate.now());
        Car car = new Car(1L, "numberReg", LocalDate.now(), State.AVAILABLE, carBrand, rent);
        //When
        CarDto carDto = carMapper.mapToCarDto(car);
        //Then
        assertEquals(carDto.getRegistrationNumber(), car.getRegistrationNumber());
        assertEquals(carDto.getProductionYear(), car.getProductionYear());
        assertEquals(carDto.getState(), car.getState());
        assertEquals(carDto.getCarBrandId(), car.getCarBand().getId());
    }

    @Test
    public void testMapToCarList() {
        //Given
        List<CarDto> carDtoList = List.of(new CarDto(1L, 1L, "registration number", LocalDate.now(), State.AVAILABLE));
        //When
        List<Car> cars = carMapper.mapToCarList(carDtoList);
        //Then
        assertEquals(cars.get(0).getRegistrationNumber(), carDtoList.get(0).getRegistrationNumber());
        assertEquals(cars.get(0).getProductionYear(), carDtoList.get(0).getProductionYear());
        assertEquals(cars.get(0).getState(), carDtoList.get(0).getState());
    }

    @Test
    public void testMapToCarDtoList() {
        //Given
        Rent rent = new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now());
        CarBrand carBrand = new CarBrand(1L, "brandName", LocalDate.now());
        Car car = new Car(1L, "numberReg", LocalDate.now(), State.AVAILABLE, carBrand, rent);
        List<Car> cars = List.of(car);
        //When
        List<CarDto> carDtoList = carMapper.mapToCarDtoList(cars);
        //Then
        assertEquals(cars.get(0).getRegistrationNumber(), carDtoList.get(0).getRegistrationNumber());
        assertEquals(cars.get(0).getProductionYear(), carDtoList.get(0).getProductionYear());
        assertEquals(cars.get(0).getState(), carDtoList.get(0).getState());
        assertEquals(cars.get(0).getId(), carDtoList.get(0).getId());
        assertEquals(cars.get(0).getCarBand().getId(), carDtoList.get(0).getCarBrandId());
    }
}
