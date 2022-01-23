package pl.tomaszosuch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.dto.CarBrandDto;
import pl.tomaszosuch.exception.CarBrandNotFoundException;
import pl.tomaszosuch.mapper.CarBrandMapper;
import pl.tomaszosuch.repository.CarBrandRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    private final CarBrandRepository carBrandRepository;
    private final CarBrandMapper carBrandMapper;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository, CarBrandMapper carBrandMapper) {
        this.carBrandRepository = carBrandRepository;
        this.carBrandMapper = carBrandMapper;
    }

    @Override
    public List<CarBrand> getAllCarBrand() {
        return carBrandRepository.findAll();
    }

    @Override
    public Optional<CarBrand> getCarBrandById(Long id) {
        return carBrandRepository.findById(id);
    }

    @Override
    public CarBrand saveCarBrand(CarBrand carBrand) {
        return carBrandRepository.save(carBrand);
    }

    @Override
    public CarBrand updateCarBrand(CarBrandDto carBrandDto) {
        carBrandRepository.findById(carBrandDto.getId()).orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found"));
        return carBrandRepository.save(carBrandMapper.mapToCarBrand(carBrandDto));
    }

    @Override
    public void deleteCarBranDById(Long id) {
        try {
            carBrandRepository.deleteById(id);
        } catch (CarBrandNotFoundException e) {
            throw new CarBrandNotFoundException("CarBrand with pointed ID does not exist");
        }
    }
}
