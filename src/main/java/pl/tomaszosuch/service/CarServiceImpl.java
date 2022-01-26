package pl.tomaszosuch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.dto.CarDto;
import pl.tomaszosuch.enums.State;
import pl.tomaszosuch.exception.CarBrandNotFoundException;
import pl.tomaszosuch.exception.CarNotFoundException;
import pl.tomaszosuch.mapper.CarMapper;
import pl.tomaszosuch.repository.CarBrandRepository;
import pl.tomaszosuch.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> getAllCar() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getCarById(final Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Car saveCar(CarDto carDto) {
        CarBrand carBand = carBrandRepository.findById(carDto.getCarBrandId()).orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found"));
        Car car = carMapper.mapToCar(carDto);
        carBand.getCars().add(car);
        return carRepository.save(car);
    }

    @Override
    public Car updateCarStateDamaged(final Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("Car not found"));
        car.setState(State.DAMAGED);
        return carRepository.save(car);
    }

    @Override
    public void deleteCarById(final Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("car not found"));
        carRepository.delete(car);
    }
}
