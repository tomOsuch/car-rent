package pl.tomaszosuch.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Rent;
import pl.tomaszosuch.mapper.RentMapper;
import pl.tomaszosuch.repository.CarRepository;
import pl.tomaszosuch.repository.RentRepository;
import pl.tomaszosuch.repository.UserRepository;
import pl.tomaszosuch.validator.RentValidator;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RentServiceImplTest {

    @InjectMocks
    private RentServiceImpl rentService;

    @Mock
    private RentRepository rentRepository;

    @Mock
    private RentMapper rentMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CarRepository carRepository;

    @Mock
    private RentValidator rentValidator;

    @Test
    public void testGetAllRent() {
        //Given
        List<Rent> rentList = List.of(new Rent(LocalDate.now(), LocalDate.of(2022, 02,01)));

        when(rentRepository.findAll()).thenReturn(rentList);
        //When
        List<Rent> resultGetAllRent = rentService.getAllRent();
        //Then
        assertEquals(1, resultGetAllRent.size());
    }

    @Test
    public void testGetRentById() {
        //Given
        List<Rent> rentList = List.of(new Rent(LocalDate.now(), LocalDate.of(2022, 02,01)));

        when(rentRepository.findById(1L)).thenReturn(Optional.ofNullable(rentList.get(0)));
        //When
        boolean resultGetRentById = rentService.getRentById(1L).isPresent();
        //Then
        assertTrue(resultGetRentById);
    }

    /*
    @Test
    public void testSaveUser() {
        //Given
        Rent rent = new Rent(LocalDate.now(), LocalDate.of(2022, 02,01));
        RentDto rentDto = new RentDto(1L, 1L, 1L, LocalDate.now(), LocalDate.of(2022, 02,01));
        User user = new User(1L, "Jan", "Kowalski" , "1234ABC", "ABC1234", List.of());
        Car car = new Car(1L, "123Test",LocalDate.of(2021, 01, 01), State.AVAILABLE, new CarBand(), rent);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(carRepository.findById(1L)).thenReturn(Optional.of(car));
        when(rentValidator.rentValidator(rent.getRentDate(), rent.getReturnDate())).thenReturn(true);
        when(rentMapper.mapToRent(rentDto)).thenReturn(rent);
        when(rentRepository.save(rent)).thenReturn(rent);
        when(rentService.saveRent(rentDto)).thenReturn(rent);
        //When
        Rent resultSaveRent = rentService.saveRent(rentDto);
        //Then
        assertEquals(rent.getRentDate(), resultSaveRent.getRentDate());
        assertEquals(rent.getReturnDate(), resultSaveRent.getReturnDate());
    }
    */
}
