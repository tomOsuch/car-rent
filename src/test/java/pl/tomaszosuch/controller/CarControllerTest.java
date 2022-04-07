package pl.tomaszosuch.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.tomaszosuch.config.LocalDateAdapter;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.dto.CarDto;
import pl.tomaszosuch.enums.State;
import pl.tomaszosuch.mapper.CarMapper;
import pl.tomaszosuch.service.CarServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CarController.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarServiceImpl carService;

    @MockBean
    private CarMapper carMapper;

    @Test
    public void testGetAllCar() throws Exception {
        //given
        List<CarDto> carDtoList = List.of(new CarDto(1L, 1L, "Test", LocalDate.of(2022, 01, 01), State.AVAILABLE));
        when(carMapper.mapToCarDtoList(anyList())).thenReturn(carDtoList);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/car/getAllCar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carBrandId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].registrationNumber", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productionYear", Matchers.is("2022-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].state", Matchers.is("AVAILABLE")));
    }

    @Test
    public void testGetCarById() throws Exception {
        //given
        Rent rent = new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now());
        CarBrand carBrand = new CarBrand(1L, "Brand name", LocalDate.of(2022, 01, 01));
        CarDto carDto = new CarDto(1L, 1L, "Test", LocalDate.of(2022, 01, 01), State.AVAILABLE);
        Car car = new Car(1L, "Test", LocalDate.of(2022, 01, 01),State.AVAILABLE, carBrand, rent);

        when(carService.getCarById(anyLong())).thenReturn(Optional.of(car));
        when(carMapper.mapToCarDto(any(Car.class))).thenReturn(carDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/car/getCarById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carBrandId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.registrationNumber", Matchers.is("Test")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionYear", Matchers.is("2022-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state", Matchers.is("AVAILABLE")));
    }

    @Test
    public void testSaveCar() throws Exception {
        //given
        Rent rent = new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now());
        CarBrand carBrand = new CarBrand(1L, "Brand name", LocalDate.of(2022, 01, 01));
        CarDto carDto = new CarDto(1L, 1L, "Test", LocalDate.of(2022, 01, 01), State.AVAILABLE);
        Car car = new Car(1L, "Test", LocalDate.of(2022, 01, 01),State.AVAILABLE, carBrand, rent);

        when(carMapper.mapToCar(carDto)).thenReturn(car);
        when(carService.saveCar(carDto)).thenReturn(car);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(carDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/car/saveCar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCar() throws Exception {
        //given
        Car car = new Car(1L, "Test", LocalDate.of(2022, 01, 01),State.AVAILABLE);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/car/deleteCar/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(carService, times(1)).deleteCarById(1L);
    }
}