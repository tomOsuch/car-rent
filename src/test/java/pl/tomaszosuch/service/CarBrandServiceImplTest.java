package pl.tomaszosuch.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.repository.CarBrandRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarBrandServiceImplTest {

    @InjectMocks
    private CarBrandServiceImpl carBrandService;

    @Mock
    private CarBrandRepository carBrandRepository;

    @Test
    public void testGetAllCarBrand() {
        //Given
        List<CarBrand> carBrandList = List.of(new CarBrand("CarBrand_Name_Test", LocalDate.now()));
        when(carBrandRepository.findAll()).thenReturn(carBrandList);
        //When
        List<CarBrand> resultGetAllCarBrand = carBrandService.getAllCarBrand();
        //Then
        assertEquals(1, resultGetAllCarBrand.size());
    }

    @Test
    public void testGetCarBrandById() {
        //Given
        List<CarBrand> carBrandList = List.of(new CarBrand(1L, "CarBrand_Name_Test", LocalDate.now(), List.of(new Car())));
        when(carBrandRepository.findById(carBrandList.get(0).getId())).thenReturn(Optional.ofNullable(carBrandList.get(0)));
        //When
        boolean resultGetCarBrandById = carBrandService.getCarBrandById(1L).isPresent();
        //Then
        assertTrue(resultGetCarBrandById);
    }

    @Test
    public void testSaveCarBrand() {
        //Given
        CarBrand carBrand = new CarBrand("CarBrand_Name_Test", LocalDate.now());
        when(carBrandRepository.save(carBrand)).thenReturn(carBrand);
        //When
        CarBrand resultSaveCarBrand = carBrandService.saveCarBrand(carBrand);
        //Then
        assertEquals(carBrand.getBrandName(), resultSaveCarBrand.getBrandName());
        assertEquals(carBrand.getConstructionYear(), resultSaveCarBrand.getConstructionYear());
    }

    @Test
    public void testDeleteCarBrandById() {
        //Given
        CarBrand carBrand = new CarBrand(1L, "CarBrand_Name_Test", LocalDate.now(), List.of(new Car()));
        Long id = carBrand.getId();
        //When
        carBrandService.deleteCarBranDById(id);
        //Then
        verify(carBrandRepository, times(1)).deleteById(id);
    }
}
