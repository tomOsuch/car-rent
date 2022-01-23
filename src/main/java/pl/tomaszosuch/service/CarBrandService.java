package pl.tomaszosuch.service;

import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.dto.CarBrandDto;

import java.util.List;
import java.util.Optional;

public interface CarBrandService {

    List<CarBrand> getAllCarBrand();
    Optional<CarBrand> getCarBrandById(final Long id);
    CarBrand saveCarBrand(final CarBrand carBrand);
    CarBrand updateCarBrand(final CarBrandDto carBrandDto);
    void deleteCarBranDById(Long id);
}
