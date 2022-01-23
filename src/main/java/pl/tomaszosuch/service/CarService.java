package pl.tomaszosuch.service;

import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCar();
    Optional<Car> getCarById(Long id);
    Car saveCar(final CarDto carDto);
    Car updateCarStateDamaged(final Long id);
    void deleteCarById(final Long id);
}
