package pl.tomaszosuch.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.CarBrand;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CarBrandRepositoryTest {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Test
    public void testCarBrandSave() {
        //Given
        CarBrand carBand = new CarBrand("Brand name test", LocalDate.of(2022, 01, 01), List.of(new Car()));
        Long id = carBand.getId();
        //When
        CarBrand resultSaveCarBrand = carBrandRepository.save(carBand);
        //Then
        assertEquals(carBand.getId(), resultSaveCarBrand.getId());
        assertEquals(carBand.getBrandName(), resultSaveCarBrand.getBrandName());
        assertEquals(carBand.getConstructionYear(), resultSaveCarBrand.getConstructionYear());
        //CleanUp
        carBrandRepository.deleteAll();
    }

    @Test
    public void testCarBrandFindAll() {
        //Given
        CarBrand carBand = new CarBrand("Brand name test", LocalDate.of(2022, 01, 01), List.of(new Car()));
        carBrandRepository.save(carBand);
        Long id = carBand.getId();
        //When
        List<CarBrand> resultFindAll = carBrandRepository.findAll();
        //Then
        assertEquals(1, resultFindAll.size());
        //CleanUp
        carBrandRepository.deleteAll();
    }

    @Test
    public void testCarBrandFindById() {
        //Given
        CarBrand carBand = new CarBrand("Brand name test", LocalDate.of(2022, 01, 01), List.of(new Car()));
        carBrandRepository.save(carBand);
        Long id = carBand.getId();
        //When
        Optional<CarBrand> resultFindById = carBrandRepository.findById(id);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        carBrandRepository.deleteAll();
    }

    @Test
    public void testCarBrandDeleteById() {
        //Given
        CarBrand carBand = new CarBrand("Brand name test", LocalDate.of(2022, 01, 01), List.of(new Car()));
        carBrandRepository.save(carBand);
        Long id = carBand.getId();
        //When
        carBrandRepository.deleteById(id);
        Optional<CarBrand> resultCarBrandDelete = carBrandRepository.findById(id);
        //Then
        assertTrue(resultCarBrandDelete.isEmpty());
    }
}
