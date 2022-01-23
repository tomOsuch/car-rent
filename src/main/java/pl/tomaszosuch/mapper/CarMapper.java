package pl.tomaszosuch.mapper;

import org.springframework.stereotype.Component;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.dto.CarDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getRegistrationNumber(),
                carDto.getProductionYear(),
                carDto.getState()
        );
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getCarBand().getId(),
                car.getRegistrationNumber(),
                car.getProductionYear(),
                car.getState()
        );
    }

    public List<CarDto> mapToCarDtoList(final List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }

    public List<Car> mapToCarList(final List<CarDto> carDtoList) {
        return carDtoList.stream()
                .map(this::mapToCar)
                .collect(Collectors.toList());
    }
}
