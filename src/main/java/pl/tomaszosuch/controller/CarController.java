package pl.tomaszosuch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.dto.CarDto;
import pl.tomaszosuch.exception.CarNotFoundException;
import pl.tomaszosuch.mapper.CarMapper;
import pl.tomaszosuch.service.CarServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/car")
public class CarController {

    private final CarServiceImpl carService;
    private final CarMapper carMapper;

    @Autowired
    public CarController(CarServiceImpl carService, CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @GetMapping("/getAllCar")
    public List<CarDto> getAllCar() {
        return carMapper.mapToCarDtoList(carService.getAllCar());
    }

    @GetMapping("/getcarById/{id}")
    public CarDto getCarById(@PathVariable Long id) {
        return carMapper.mapToCarDto(carService.getCarById(id).orElseThrow(() -> new CarNotFoundException("Car not found")));
    }

    @PostMapping(value = "/saveCar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCar(@RequestBody CarDto carDto) {
        carService.saveCar(carDto);
    }

    @PutMapping("/updateCar/{id}")
    public CarDto updateCarToStateDamage(@PathVariable Long id) {
        return carMapper.mapToCarDto(carService.updateCarStateDamaged(id));
    }

    @DeleteMapping("/deleteCar/{id}")
    public void deleteCarById(@PathVariable Long id) {
        carService.deleteCarById(id);
    }
}
