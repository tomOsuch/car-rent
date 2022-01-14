package pl.tomaszosuch.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.tomaszosuch.domain.Rent;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RentRepositoryTest {

    @Autowired
    private RentRepository rentRepository;

    @Test
    public void testRentFindAll() {
        //Given
        Rent rent = new Rent(LocalDate.of(2021, 12, 01), LocalDate.of(2021,12,24));
        rentRepository.save(rent);
        Long id = rent.getId();
        //When
        List<Rent> resultFindAllRent = rentRepository.findAll();
        //Then
        assertEquals(1, resultFindAllRent.size());
        //CleanUp
        rentRepository.deleteAll();
    }

    @Test
    public void testRentFindByIdAndSave() {
        //Given
        Rent rent = new Rent(LocalDate.of(2021, 12, 01), LocalDate.of(2021,12,24));
        rentRepository.save(rent);
        Long id = rent.getId();
        //When
        Optional<Rent> resultFindById = rentRepository.findById(id);
        //Then
        assertTrue(resultFindById.isPresent());
        //CleanUp
        rentRepository.deleteAll();
    }

    @Test
    public void testRentDeleteById() {
        //Given
        Rent rent = new Rent(LocalDate.of(2021, 12, 01), LocalDate.of(2021,12,24));
        rentRepository.save(rent);
        Long rentId = rent.getId();
        //When
        rentRepository.deleteById(rentId);
        Optional<Rent> resultDeleteById = rentRepository.findById(rentId);
        //Then
        assertFalse(resultDeleteById.isPresent());
    }
}
