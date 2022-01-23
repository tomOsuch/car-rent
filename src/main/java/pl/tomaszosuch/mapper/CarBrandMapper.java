package pl.tomaszosuch.mapper;

import org.springframework.stereotype.Component;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.dto.CarBrandDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarBrandMapper {

    public CarBrand mapToCarBrand(final CarBrandDto carBrandDto) {
        return new CarBrand(
                carBrandDto.getBrandName(),
                carBrandDto.getConstructionYear()
        );
    }

    public CarBrandDto mapToCarBranDDto(final CarBrand carBrand) {
        return new CarBrandDto(
                carBrand.getId(),
                carBrand.getBrandName(),
                carBrand.getConstructionYear()
        );
    }

    public List<CarBrand> mapToCarBrandList(final List<CarBrandDto> carBrandDtoList) {
        return carBrandDtoList.stream()
                .map(this::mapToCarBrand)
                .collect(Collectors.toList());
    }

    public List<CarBrandDto> mapToCarBrandDtoList(final List<CarBrand> carBrandList) {
        return carBrandList.stream()
                .map(this::mapToCarBranDDto)
                .collect(Collectors.toList());
    }
}
