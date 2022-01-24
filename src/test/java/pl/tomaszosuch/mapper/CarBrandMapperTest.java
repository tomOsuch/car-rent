package pl.tomaszosuch.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.dto.CarBrandDto;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CarBrandMapperTest {

    @Autowired
    private CarBrandMapper carBrandMapper;

    @Test
    public void testMapToCarBrand() {
        //given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Brand name", LocalDate.now());
        //when
        CarBrand carBrand = carBrandMapper.mapToCarBrand(carBrandDto);
        //then
        assertEquals(carBrand.getBrandName(), carBrandDto.getBrandName());
        assertEquals(carBrand.getConstructionYear(), carBrandDto.getConstructionYear());
    }

    @Test
    public void testMapToCarBrandDto() {
        //given
        CarBrand carBrand = new CarBrand(1L, "Brand name", LocalDate.now());
        //when
        CarBrandDto carBrandDto = carBrandMapper.mapToCarBranDDto(carBrand);
        //then
        assertEquals(carBrand.getId(), carBrandDto.getId());
        assertEquals(carBrand.getBrandName(), carBrandDto.getBrandName());
        assertEquals(carBrand.getConstructionYear(), carBrandDto.getConstructionYear());
    }

    @Test
    public void testMapToCarBrandList() {
        //given
        List<CarBrandDto> carBrandDtoList = List.of(new CarBrandDto(1L, "Brand name", LocalDate.now()));
        //when
        List<CarBrand> carBrandList = carBrandMapper.mapToCarBrandList(carBrandDtoList);
        //then
        assertEquals(carBrandList.get(0).getBrandName(), carBrandDtoList.get(0).getBrandName());
        assertEquals(carBrandList.get(0).getConstructionYear(), carBrandDtoList.get(0).getConstructionYear());
    }

    @Test
    public void testMapToCarBrandDtoList() {
        //given
        List<CarBrand> carBrandList = List.of(new CarBrand(1L, "Brand name", LocalDate.now()));
        //when
        List<CarBrandDto> carBrandDtoList = carBrandMapper.mapToCarBrandDtoList(carBrandList);
        //then
        assertEquals(carBrandList.get(0).getId(), carBrandDtoList.get(0).getId());
        assertEquals(carBrandList.get(0).getBrandName(), carBrandDtoList.get(0).getBrandName());
        assertEquals(carBrandList.get(0).getConstructionYear(), carBrandDtoList.get(0).getConstructionYear());
    }
}
