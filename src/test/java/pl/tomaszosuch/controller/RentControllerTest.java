package pl.tomaszosuch.controller;

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
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.RentDto;
import pl.tomaszosuch.enums.State;
import pl.tomaszosuch.mapper.RentMapper;
import pl.tomaszosuch.service.RentServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(RentController.class)
public class RentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentServiceImpl rentService;

    @MockBean
    private RentMapper rentMapper;

    @Test
    public void testGetAllRent() throws Exception {
        //given
        List<RentDto> rentDtoList = List.of(new RentDto(1L, 1L, 1L, LocalDate.of(2021, 01, 01), LocalDate.of(2021, 02, 01)));
        when(rentMapper.mapToListRentDto(anyList())).thenReturn(rentDtoList);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rent/getAllRent")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rentDate", Matchers.is("2021-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].returnDate", Matchers.is("2021-02-01")));
    }

    @Test
    public void testGetRentById() throws Exception {
        //given
        RentDto rentDto = new RentDto(1L, 1L, 1L, LocalDate.of(2021, 01, 01), LocalDate.of(2021, 02, 01));
        Car car = new Car(1L, "numberReg", LocalDate.now(), State.AVAILABLE);
        User user = new User(1L, "firstName", "lastName", "cardNumber", "drivingLicenseNumber");
        Rent rent = new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now(), user, car);

        when(rentService.getRentById(anyLong())).thenReturn(Optional.of(rent));
        when(rentMapper.mapToRentDto(any(Rent.class))).thenReturn(rentDto);
        //when & then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rent/getRentById/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rentDate", Matchers.is("2021-01-01")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.returnDate", Matchers.is("2021-02-01")));
    }
    
}
