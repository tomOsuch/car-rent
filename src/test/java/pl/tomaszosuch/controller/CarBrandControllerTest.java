package pl.tomaszosuch.controller;

import com.google.gson.*;
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
import pl.tomaszosuch.domain.CarBrand;
import pl.tomaszosuch.dto.CarBrandDto;
import pl.tomaszosuch.mapper.CarBrandMapper;
import pl.tomaszosuch.service.CarBrandServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(CarBrandController.class)
class CarBrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarBrandServiceImpl carBrandService;

    @MockBean
    private CarBrandMapper carBrandMapper;

    @Test
    public void getAllCarBrand() throws Exception {
        //given
        List<CarBrandDto> carBrandDtoList = List.of(new CarBrandDto(1L, "Brand name", LocalDate.of(2022, 01, 01)));
        when(carBrandMapper.mapToCarBrandDtoList(anyList())).thenReturn(carBrandDtoList);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/carBrand/getAllCarBrand")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brandName", Matchers.is("Brand name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].constructionYear", Matchers.is("2022-01-01")));
    }

    @Test
    public void testGetCarBrandById() throws Exception {
        //given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Brand name", LocalDate.of(2022, 01, 01));
        CarBrand carBrand = new CarBrand(1L, "Brand name", LocalDate.of(2022, 01, 01));

        when(carBrandService.getCarBrandById(anyLong())).thenReturn(Optional.of(carBrand));
        when(carBrandMapper.mapToCarBranDDto(any(CarBrand.class))).thenReturn(carBrandDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/carBrand/getCarBrandById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandName", Matchers.is("Brand name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.constructionYear", Matchers.is("2022-01-01")));
    }

    @Test
    public void testSaveCarBrand() throws Exception {
        //given
        CarBrandDto carBrandDto = new CarBrandDto(1L, "Brand name", LocalDate.now());
        CarBrand carBrand = new CarBrand(1L, "Brand name", LocalDate.now());

        when(carBrandMapper.mapToCarBrand(any(CarBrandDto.class))).thenReturn(carBrand);
        when(carBrandService.saveCarBrand(any(CarBrand.class))).thenReturn(carBrand);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
        String jsonContent = gson.toJson(carBrandDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/carBrand/saveCarBrand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().isOk());

        verify(carBrandService, times(1)).saveCarBrand(carBrand);
    }

    @Test
    public void testDeleteCarBrand() throws Exception {
        //given
        CarBrand carBrand = new CarBrand(1L, "Brand name", LocalDate.of(2022, 01, 01));
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/carBrand/deleteCarBrand/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(carBrandService, times(1)).deleteCarBranDById(1L);
    }
}
