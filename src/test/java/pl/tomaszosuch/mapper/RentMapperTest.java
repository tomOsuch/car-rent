package pl.tomaszosuch.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Car;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.domain.User;
import pl.tomaszosuch.dto.RentDto;
import pl.tomaszosuch.enums.State;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentMapperTest {

    @Autowired
    private RentMapper rentMapper;

    @Test
    public void testMapToRent() {
        //Given
        RentDto rentDto = new RentDto(1L, 1L, 1L, LocalDate.of(2021, 01, 01), LocalDate.now());
        //When
        Rent rent = rentMapper.mapToRent(rentDto);
        //Then
        assertEquals(rent.getRentDate(), rentDto.getRentDate());
        assertEquals(rent.getReturnDate(), rentDto.getReturnDate());
    }

    @Test
    public void testMapToRentList() {
        //Given
        List<RentDto> rentDtoList = List.of(new RentDto(1L, 1L, 1L, LocalDate.of(2021, 01, 01), LocalDate.now()));
        //When
        List<Rent> rents = rentMapper.mapToRentList(rentDtoList);
        //Then
        assertEquals(rents.get(0).getRentDate(), rentDtoList.get(0).getRentDate());
        assertEquals(rents.get(0).getReturnDate(), rentDtoList.get(0).getReturnDate());
    }

    @Test
    public void testMapToRentDto() {
        //Given
        User user = new User(1L, "firstName", "lastName", "cardNumber", "drivingLicenseNumber");
        Car car = new Car(1L, "numberReg", LocalDate.now(), State.AVAILABLE);
        Rent rent = new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now(), user, car);
        //When
        RentDto rentDto = rentMapper.mapToRentDto(rent);
        //Then
        assertEquals(rent.getRentDate(), rentDto.getRentDate());
        assertEquals(rent.getReturnDate(), rentDto.getReturnDate());
    }

    @Test
    public void testMapToRentDtoList() {
        //Given
        User user = new User(1L, "firstName", "lastName", "cardNumber", "drivingLicenseNumber");
        Car car = new Car(1L, "numberReg", LocalDate.now(), State.AVAILABLE);
        List<Rent> rents = List.of(new Rent(1L, LocalDate.of(2021, 01, 01), LocalDate.now(), user, car));
        //When
        List<RentDto> rentDtoList = rentMapper.mapToListRentDto(rents);
        //Then
        assertEquals(rents.get(0).getRentDate(), rentDtoList.get(0).getRentDate());
        assertEquals(rents.get(0).getReturnDate(), rentDtoList.get(0).getReturnDate());
    }
}
