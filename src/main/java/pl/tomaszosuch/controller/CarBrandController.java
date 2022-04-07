package pl.tomaszosuch.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.tomaszosuch.dto.CarBrandDto;
import pl.tomaszosuch.exception.CarBrandNotFoundException;
import pl.tomaszosuch.mapper.CarBrandMapper;
import pl.tomaszosuch.service.CarBrandServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/v1/carBrand")
@RequiredArgsConstructor
public class CarBrandController {

    @Autowired
    private final CarBrandServiceImpl carBrandService;

    @Autowired
    private final CarBrandMapper carBrandMapper;

    @GetMapping("/getAllCarBrand")
    public List<CarBrandDto> getAllCarBrand() {
        return carBrandMapper.mapToCarBrandDtoList(carBrandService.getAllCarBrand());
    }

    @GetMapping("/getCarBrandById/{id}")
    public CarBrandDto getCarBrandById(@PathVariable Long id) {
        return carBrandMapper.mapToCarBranDDto(carBrandService.getCarBrandById(id).orElseThrow(() -> new CarBrandNotFoundException("CarBrand not found")));
    }

    @PostMapping(value = "/saveCarBrand", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void saveCarBrand(@RequestBody CarBrandDto carBrandDto) {
        carBrandService.saveCarBrand(carBrandMapper.mapToCarBrand(carBrandDto));
    }

    @PutMapping("/updateCarBrand")
    public CarBrandDto updateCarBrand(@RequestBody CarBrandDto carBrandDto) {
        return carBrandMapper.mapToCarBranDDto(carBrandService.updateCarBrand(carBrandDto));
    }

    @DeleteMapping("/deleteCarBrand/{id}")
    public void deleteCarBrandById(@PathVariable Long id) {
        carBrandService.deleteCarBranDById(id);
    }
}
